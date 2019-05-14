package searchengine2;

import searchengine2.analysiser.DefaultParser;
import searchengine2.analysiser.Parser;
import searchengine2.command.PutParams;
import searchengine2.datastructure.DefaultEngineStorage;
import searchengine2.datastructure.EngineStorage;
import searchengine2.domain.document.Document;
import searchengine2.domain.SearchResult;
import searchengine2.domain.node.InternalNode;
import searchengine2.domain.node.RootNode;

/**
 * @author guya on 2019/4/8
 */
public class StandardSearchEngine implements SearchEngine {

    /** todo: 配置化 */
    private Parser parser = new DefaultParser();

    private EngineStorage defaultEngineStorage = new DefaultEngineStorage();

    @Override
    public void put(PutParams params) {
        // todo: 文档已存在
        InternalNode internalNode = parser.parse(params.getJsonContent());
        RootNode rootNode = new RootNode(params.getType(), internalNode);
        Document document = new Document(params.getIndex(), params.getType(), params.getId(), rootNode);
        defaultEngineStorage.put(document);
        return;
    }

    @Override
    public SearchResult<?> search(String sentence) {
        return null;
    }
}
