package searchengine2.analysiser;

import searchengine2.domain.document.Document;
import searchengine2.domain.node.IntNode;
import searchengine2.domain.node.InternalNode;
import searchengine2.domain.node.Node;
import searchengine2.domain.node.TextNode;

import java.util.List;

/**
 * @author guya on 2019/4/8
 */
public class DefaultAnalysiser implements Analysiser {

    private Tokenizer tokenizer = new DefaultTokenizer();


    @Override
    public void analysis(Document document) {
        InternalNode rootNode = document.getNode();
        processNode(rootNode);
    }

    @Override
    public List<String> processText(String s) {
        List<String> tokenList = tokenizer.apply(s);
        return tokenList;
    }

    private void processNode(Node node) {
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
                processNode(n);
            }
        }
    }
}
