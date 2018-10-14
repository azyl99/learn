
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author guya
 * @date 2018/9/6
 */
public class A_Locale {

    public static void main(String[] args) {

        Locale locale = new Locale("zh", "CN");
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        System.out.println(numberFormat.format(123543.34));
        numberFormat = NumberFormat.getCurrencyInstance(locale);
        System.out.println(numberFormat.format(123543.34));
    }
}
