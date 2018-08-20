package 简单状态机;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author guya
 * @date 2018/8/20
 */
public class A {

    public static String deleteComment(String s) throws Exception {
        StringBuffer sb = new StringBuffer();
        int cursor = 0;
        int state = States.NORMAL;
        char c;
        while (cursor < s.length()) {
            c = s.charAt(cursor);
            switch (state) {
                case States.NORMAL:
                    if (c == '/') {
                        state = States.COMMENT_HALF_START;
                    } else if (c == '"'){
                        state = States.STRING;
                        sb.append(c);
                    } else {
                        sb.append(c);
                    }
                    break;
                case States.COMMENT_HALF_START:
                    if (c == '/') {
                        state = States.COMMENT_SINGLE_LINE;
                    } else if (c == '*'){
                        state = States.COMMENT_MULTI_LINE;
                    } else {// 正常的反斜号 5/3 = 1
                        state = States.NORMAL;
                        sb.append('/').append(c);
                    }
                    break;
                case States.COMMENT_SINGLE_LINE:
                    if (c == '\n') {
                        state = States.NORMAL;
                        sb.append(c);
                    }
                    break;
                case States.COMMENT_MULTI_LINE:
                    if (c == '*') {
                        state = States.COMMENT_MULTI_LINE_HALF_CLOSE;
                    }
                    break;
                case States.COMMENT_MULTI_LINE_HALF_CLOSE:
                    if (c == '/') {
                        state = States.NORMAL;
                    } else {
                        state = States.COMMENT_MULTI_LINE; // " /* *husdfh*saf */"
                    }
                    break;
                case States.STRING:
                    if (c == '"') {
                        state = States.NORMAL;
                    } else if (c == '\\') {
                        state = States.STRING_ESCAPE;
                    }
                    sb.append(c);
                    break;
                case States.STRING_ESCAPE:
                    if (c == '\n') {
                        throw new Exception("can't end a line with'\\'");
                    }
                    state = States.STRING;// 无论是什么，都会结束转义的状态
                    sb.append(c);
                    break;
                default:
                    throw new Exception("xxx");
            }
            cursor++;
        }
        return sb.toString();
    }

    static class States {
        public static final int NORMAL = 0;
        public static final int COMMENT_HALF_START = 1;               //  /
        public static final int COMMENT_SINGLE_LINE = 2;              //  //
        public static final int COMMENT_MULTI_LINE = 3;               //  /*
        public static final int COMMENT_MULTI_LINE_HALF_CLOSE = 4;    //  /**
        public static final int STRING = 5;                           //  "
        public static final int STRING_ESCAPE = 6;                    //  "... \" ..."
    }

    public static void main(String[] args) {
        try {
            File file = new File("D:\\IdeaProjects\\learn\\a_状态机\\src\\main\\java\\简单状态机\\A.java");
            FileReader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
                sb.append(s + "\n");//将读取的字符串添加换行符后累加存放在缓存中
            }
            bReader.close();
            String str = sb.toString();
            System.out.println(/* 测试行内注释  */deleteComment(str));
            System.out.println("\"//-----------\"");// 测试字符串内的"//"和转义引号
            System.out.println(6/2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
