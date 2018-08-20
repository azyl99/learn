package 正则表达式;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author guya
 * @date 2018/8/15
 */
public class A {

    public static void test1() {
        String content = "I am noob from runoob.com.";

        String pattern = ".*runoob.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println(isMatch);
    }

    public static void test2() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";// \D: 非数字字符匹配; \d: 数字字符匹配

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    public static void test3() {
        Pattern p = Pattern.compile("\\bcat\\b");
        Matcher m = p.matcher("cat cat cat cattie cat"); // 获取 matcher 对象
        int count = 0;

        while (m.find()) {
            count++;
            System.out.println("Match number " + count);
            System.out.println("start(): " + m.start());
            System.out.println("end(): " + m.end());
        }
    }

    public static void test_label() {
        int i = 0;
        loop:                   // 只能写在循环块前面，中间不能有任何代码
        while (true) {
            if (i < 3) {
                i++;
            } else {
                if (i < 5) {
                    break loop; // 直接跳出循环块，而不是调到最前面
                } else {
                    break;      // 永远不会执行到这里
                }
            }
        }
        System.out.println(i);
    }

    public static void main(String args[]) {
//        test1();
//        test2();
//        test3();
        test_label();
    }
}
