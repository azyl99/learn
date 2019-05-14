package searchengine2.analysiser;

import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public interface Tokenizer {

    List<String> apply(String s);
}
