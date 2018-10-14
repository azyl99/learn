package salarycalculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * @author guya on 2018/9/19
 */
public class GenerateTable {

    public static double[] serial(double start, double end, double interval) {
        int len = (int) ((end - start) / interval);
        double[] list = new double[len + 1];
        for (int i = 0; i < len; i++) {
            list[i] = start + i * interval;
        }
        list[len] = end;
        return list;
    }

    public static double[] calculate(double[] xs, Function<Double, Double> function) {
        double[] ys = new double[xs.length];
        for (int i = 0; i < xs.length; i++) {
            ys[i] = function.apply(xs[i]);
        }
        return ys;
    }
    public static double[] concat(double[] first, double[] second) {
        double[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void main(String[] args) {
        TaxUtil oldTaxUtil = new TaxUtil(TaxPolicy.oldTaxPolicy);
        TaxUtil newTaxUtil = new TaxUtil();
        double[] salaries = GenerateTable.concat(GenerateTable.serial(4000, 25000, 1000), GenerateTable.serial(30000, 85000, 5000));
        double[] oldTaxes = GenerateTable.calculate(salaries, new Function<Double, Double>() {
            @Override
            public Double apply(Double d) {
                return oldTaxUtil.tax(d).getTax();
            }
        });
        double[] newTaxes = GenerateTable.calculate(salaries, new Function<Double, Double>() {
            @Override
            public Double apply(Double d) {
                return newTaxUtil.tax(d).getTax();
            }
        });

        for (int i = 0; i < salaries.length; i++) {
            System.out.printf("%6.0f%6.0f%6.0f%6.0f%7.2f%%%6.2f%%\n", salaries[i], oldTaxes[i], newTaxes[i], oldTaxes[i] - newTaxes[i], (oldTaxes[i] - newTaxes[i]) / oldTaxes[i] * 100, (oldTaxes[i] - newTaxes[i]) / salaries[i] * 100);
        }
    }
}
