package searchengine2.analysiser;

import searchengine2.domain.node.IntNode;
import searchengine2.domain.node.InternalNode;
import searchengine2.domain.node.Node;
import searchengine2.domain.node.TextNode;
import searchengine2.utils.JsonUtils;

import java.util.Map;

/**
 * @author guya on 2019/4/8
 */
public class DefaultParser implements Parser {

    @Override
    public InternalNode parse(String s) {
        Map map = JsonUtils.fromJson(s, Map.class);
        return mapToNode(map);
    }

    private InternalNode mapToNode(Map map) {
        InternalNode internalNode = new InternalNode();
        for (Object k : map.keySet()) {
            String key = (String) k;
            Object value = map.get(key);
            Node node = null;
            if (value instanceof String) {
                node = new TextNode(key, (String)value);
            }
            if (value instanceof Integer) {
                node = new IntNode(key, (Integer)value);
            }
            if (value instanceof Map) {
                node = mapToNode((Map) value);
                node.setName(key);
            }
            internalNode.add(node);
        }
        return internalNode;
    }
}
