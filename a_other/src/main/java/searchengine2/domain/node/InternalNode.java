package searchengine2.domain.node;

import java.util.HashSet;
import java.util.Set;

/**
 * @author guya on 2019/4/8
 */
public class InternalNode extends Node {

    protected Set<Node> properties = new HashSet<>();

    public InternalNode() {
    }

    public InternalNode(String name) {
        super(name);
    }

    public void add(Node node) {
        properties.add(node);
    }

    public int size() {
        return properties.size();
    }

    public Set<Node> getProperties() {
        return properties;
    }
}
