package searchengine2.analysiser;

import searchengine2.domain.document.Document;

import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public interface Analysiser {

    void analysis(Document document);

    List<String> processText(String s);
}
