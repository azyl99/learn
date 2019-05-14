package searchengine2.datastructure;

import searchengine2.domain.document.Document;

/**
 * @author guya on 2019/4/8
 */
public interface EngineStorage {
    void put(Document document);
}
