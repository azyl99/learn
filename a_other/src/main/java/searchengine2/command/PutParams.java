package searchengine2.command;

import lombok.Data;

/**
 * @author guya on 2019/4/8
 */
@Data
public class PutParams {

    private String index;
    private String type;
    private String id;
    private String jsonContent;

    public PutParams(String index, String type, String id, String jsonContent) {
        this.index = index;
        this.type = type;
        this.id = id;
        this.jsonContent = jsonContent;
    }
}
