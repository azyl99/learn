package searchengine2;

import searchengine2.command.PutParams;
import searchengine2.domain.SearchResult;

/**
 * @author guya on 2019/4/8
 */
public interface SearchEngine {

    void put(PutParams params);

    SearchResult<?> search(String sentence);

}
