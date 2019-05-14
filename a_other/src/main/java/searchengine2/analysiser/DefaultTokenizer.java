package searchengine2.analysiser;

import java.util.Arrays;
import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public class DefaultTokenizer implements Tokenizer {

    @Override
    public List<String> apply(String s) {
        String[] arr = s.split("\\s*");
        return Arrays.asList(arr);
    }
}
