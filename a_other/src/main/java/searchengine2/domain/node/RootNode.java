package searchengine2.domain.node;

/**
 * @author guya on 2019/4/8
 */
public class RootNode extends InternalNode {
    public RootNode(String name, InternalNode node) {
        super(name);
        this.properties = node.properties;
    }
}
