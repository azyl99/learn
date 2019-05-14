package searchengine2;

import org.junit.Test;
import searchengine2.command.PutParams;

/**
 * @author guya on 2019/4/8
 */
public class StandardSearchEngineTest extends TestCommon {

    private StandardSearchEngine engine = new StandardSearchEngine();

    @Test
    public void putTest() {
        PutParams params1 = new PutParams("test", "test", "1", json);
        engine.put(params1);
    }
}
