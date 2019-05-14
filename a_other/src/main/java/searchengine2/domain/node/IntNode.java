package searchengine2.domain.node;

/**
 * @author guya on 2019/4/8
 */
public class IntNode extends LeafNode<Integer> {
    public IntNode(String name, Integer value) {
        super(name, value);
        this.setType(NodeType.INT);
    }
}