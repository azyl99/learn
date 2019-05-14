package searchengine2.datastructure;

import searchengine2.analysiser.Analysiser;
import searchengine2.analysiser.DefaultAnalysiser;
import searchengine2.domain.document.Document;

import java.util.Map;
import java.util.Set;

/**
 * @author guya on 2019/4/8
 */
public class InvertIndex {

    private String indexName;

    public InvertIndex(String indexName) {
        this.indexName = indexName;
    }

    private Map<String, Set<Document>> tokenMap;

    /** 每个索引有自己的分析器，这样查找的时候才不会有问题 */
    private Analysiser analysiser = new DefaultAnalysiser();

    public void put(Document document) {
        analysiser.analysis(document);
//        for (String token : tokenList) {
//            if (!tokenMap.containsValue(token)) {
//                tokenMap.put(token, new HashSet<>());
//            }
//            tokenMap.get(token).add(document);
//        }
    }
}
