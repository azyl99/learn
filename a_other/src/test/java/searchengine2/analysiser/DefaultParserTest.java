package searchengine2.analysiser;

import org.junit.Test;
import searchengine2.TestCommon;
import searchengine2.domain.node.InternalNode;

/**
 * @author guya on 2019/4/8
 */
public class DefaultParserTest extends TestCommon {

    private DefaultParser parser = new DefaultParser();

    @Test
    public void parseSuccess() {
        InternalNode node = parser.parse(json);
        assert node.size() == 4;
    }

}
