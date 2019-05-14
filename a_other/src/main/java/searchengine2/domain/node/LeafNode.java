package searchengine2.domain.node;

/**
 * @author guya on 2019/4/8
 */
public abstract class LeafNode<T> extends Node {

    private String type;
    private T value;

    public LeafNode(String name, T value) {
        super(name);
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }
}
