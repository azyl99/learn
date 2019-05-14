package searchengine2.domain.document;

import lombok.Data;
import searchengine2.domain.node.RootNode;

/**
 * @author guya on 2019/4/8
 */
@Data
public class Document {

    private String _index;
    private String _type;
    private String _id;
    private Integer _version;

    private RootNode node;

    public Document(String _index, String _type, String _id, RootNode node) {
        this(_index, _type, _id, 1, node);
    }

    public Document(String _index, String _type, String _id, Integer _version, RootNode node) {
        this._index = _index;
        this._type = _type;
        this._id = _id;
        this._version = _version;
        this.node = node;
    }
}
