package searchengine2.analysiser;

import searchengine2.domain.node.InternalNode;

/**
 * @author guya on 2019/4/8
 */
public interface Parser {

    InternalNode parse(String json);
}
