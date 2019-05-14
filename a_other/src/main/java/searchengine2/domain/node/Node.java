package searchengine2.domain.node;

/**
 * @author guya on 2019/4/8
 */
public abstract class Node {

    private String name;

    public Node() {
    }

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
