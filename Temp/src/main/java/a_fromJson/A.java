package a_fromJson;

import lombok.Data;

import java.util.Set;

/**
 * @author guya on 2018/9/27
 */

@Data
public class A {

    Set<Font> fonts;
    Wrap wrap;
    Set<Content> contents;

    @Data
    static class Font {
        String name;
        Integer size;
        String color;
    }

    @Data
    static class Wrap {
        String prefix;
        String suffix;
    }

    @Data
    static class Content {
        String name;
        String text;
        Font font;
        Boolean wrap;
    }

    public static void main(String[] args) {
        String json = "{\n" +
                "\t\"fonts\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"MAIN\",\n" +
                "\t\t\t\"size\": 15,\n" +
                "\t\t\t\"color\": \"#151516\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"SUB\",\n" +
                "\t\t\t\"size\": 14,\n" +
                "\t\t\t\"color\": \"#58595B\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"STRESS\",\n" +
                "\t\t\t\"size\": 14,\n" +
                "\t\t\t\"color\": \"#E02E24\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"wrap\":{\n" +
                "\t\t\"prefix\": \" (\",\n" +
                "\t\t\"suffix\": \")\"\n" +
                "\t},\n" +
                "\t\"contents\":[\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"NONE\",\n" +
                "\t\t\t\"text\": \"\",\n" +
                "\t\t\t\"font\": \"SUB\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"RECOMMEND\",\n" +
                "\t\t\t\"text\": \"推荐\",\n" +
                "\t\t\t\"font\": \"SUB\",\n" +
                "\t\t\t\"wrap\": true\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"SIGNED_SMALL\",\n" +
                "\t\t\t\"text\": \"50元内可免密支付\",\n" +
                "\t\t\t\"font\": \"SUB\",\n" +
                "\t\t\t\"wrap\": true\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"ALIPAY_BIG\",\n" +
                "\t\t\t\"text\": \"大额支付推荐使用\",\n" +
                "\t\t\t\"font\": \"STRESS\",\n" +
                "\t\t\t\"wrap\": true\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
        A a = JsonUtils.fromJson(json, A.class);
        System.out.println(a.fonts);
    }
}
