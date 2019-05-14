package searchengine1.analysise.handler;

import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public interface AnalysisHandler {

    String name();

    void process(List<String> tokenList);
}
