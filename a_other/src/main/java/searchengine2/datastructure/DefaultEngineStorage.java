package searchengine2.datastructure;

import searchengine2.domain.document.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guya on 2019/4/8
 */
public class DefaultEngineStorage implements EngineStorage {

    /** 所有的索引存储 */
    private Map<String, InvertIndex> indexMap = new HashMap<>();

    @Override
    public void put(Document document) {
        String indexName = document.get_index();
        if (!indexMap.containsValue(indexName)) {
            indexMap.put(indexName, new InvertIndex(indexName));
        }
        indexMap.get(indexName).put(document);
    }
}
