package searchengine1;

import searchengine1.analysise.handler.AnalysisHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public class StandardAnalysiser implements Analysiser {

    private List<AnalysisHandler> analysisHandlers = new ArrayList<>();


    public void addHandler(AnalysisHandler handler) {
        analysisHandlers.add(handler);
    }

    @Override
    public List<String> analysis(List<String> source) {
        return null;
    }
}
