package utils;

/**
 * @author guya on 2019/6/25
 */
public class PrintUtils {

    public static void printArr(int[] arr) {
        System.out.println(toString(arr));
    }

    public static String toString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i : arr) {
            sb.append(i).append(' ');
        }
        sb.append(']');
        return sb.toString();
    }
}
