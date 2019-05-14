package searchengine2.domain.node;

import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public class TextNode extends LeafNode<String> {

    /** 分析后的文本token */
    private List<String> tokenList;

    public TextNode(String name, String value) {
        super(name, value);
        this.setType(NodeType.TEXT);
    }

    public void setTokenList(List<String> tokenList) {
        this.tokenList = tokenList;
    }
}
