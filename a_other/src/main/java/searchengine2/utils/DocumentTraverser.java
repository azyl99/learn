package searchengine2.utils;

import searchengine2.domain.node.IntNode;
import searchengine2.domain.node.InternalNode;
import searchengine2.domain.node.Node;
import searchengine2.domain.node.TextNode;

import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public abstract class DocumentTraverser {

    public void process(Node node) {
        if (node instanceof TextNode) {
            TextNode textNode = (TextNode) node;
            textNode.setTokenList(processText(textNode.getValue()));
        }
        if (node instanceof IntNode) {
            Integer integer = ((IntNode) node).getValue();
            // omit
        }

        if (node instanceof InternalNode) {
            InternalNode internalNode = (InternalNode) node;
            for (Node n: internalNode.getProperties()) {
                process(n);
            }
        }
    }

    protected abstract List<String> processText(String value);
}
