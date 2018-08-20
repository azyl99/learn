package salarycalculator;

import lombok.Data;


/**
 * @author guya
 * @date 2018/8/16
 */
@Data
public class TaxUtil {

    private TaxPolicy taxPolicy;

    public TaxUtil() {
        this.taxPolicy = TaxPolicy.defaultTaxPolicy();
    }

    public TaxUtil(TaxPolicy taxPolicy) {
        this.taxPolicy = taxPolicy;
    }

    public Result tax(double salary) {
        salary -= taxPolicy.getTaxLayer(0).critical;
        Result result = new Result();
        if (salary < 0) {
            return result;
        }
        int i;
        for (i = 1; i < taxPolicy.getTaxLayers().size(); i++) {
            if (salary <= taxPolicy.getTaxLayer(i).critical) {
                break;
            }
        }
        TaxPolicy.TaxLayer taxLayer = taxPolicy.getTaxLayer(i - 1);
        result.setTax(salary * taxLayer.taxRate - taxLayer.fastConstant);
        result.setNetTaxRate(result.tax / salary);
        result.setAfterPayment(salary - result.tax);
        return result;
    }

    @Data
    static class Result {
        double tax;
        double afterPayment;
        double netTaxRate;
        double totalTaxRate;

        public String toString() {
            return String.format(ResultFormat.SHORT_CONTENT_FORMAT, "tax", tax, (totalTaxRate == 0 ? totalTaxRate : netTaxRate) * 100);
        }
    }

}