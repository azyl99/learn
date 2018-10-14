package salarycalculator;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author guya
 * @date 2018/8/17
 */


@Data
public class TaxPolicy {

    private List<TaxLayer> taxLayers;

    public TaxLayer getTaxLayer(int i) {
        return taxLayers.get(i);
    }

    static TaxPolicy oldTaxPolicy= new TaxPolicy();
    static TaxPolicy defaultTaxPolicy= new TaxPolicy();
    static {
        oldTaxPolicy.setTaxLayers(Arrays.asList (
                new TaxPolicy.TaxLayer(3500, 0.03, 0),
                new TaxPolicy.TaxLayer(1500, 0.1, 105),
                new TaxPolicy.TaxLayer(4500, 0.2, 555),
                new TaxPolicy.TaxLayer(9000, 0.25, 1005),
                new TaxPolicy.TaxLayer(35000, 0.3, 2755),
                new TaxPolicy.TaxLayer(55000, 0.35, 5505),
                new TaxPolicy.TaxLayer(80000, 0.45, 13505)
        ));
        defaultTaxPolicy.setTaxLayers(Arrays.asList (
                new TaxPolicy.TaxLayer(5000, 0.03, 0),
                new TaxPolicy.TaxLayer(3000, 0.1, 210),
                new TaxPolicy.TaxLayer(12000, 0.2, 1410),
                new TaxPolicy.TaxLayer(25000, 0.25, 2660),
                new TaxPolicy.TaxLayer(35000, 0.3, 4410),
                new TaxPolicy.TaxLayer(55000, 0.35, 7160),
                new TaxPolicy.TaxLayer(80000, 0.45, 15160)
        ));
    }

    @Data
    static class TaxLayer {

        double critical; // 临界点
        double taxRate;
        int fastConstant;

        public TaxLayer(double critical, double taxRate, int fastConstant) {
            this.critical = critical;
            this.taxRate = taxRate;
            this.fastConstant = fastConstant;
        }
    }
}
