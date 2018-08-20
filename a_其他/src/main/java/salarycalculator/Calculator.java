package salarycalculator;

import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author guya
 * @date 2018/8/16
 */
@Getter
public class Calculator {

    PaymentUtil paymentUtil;
    TaxUtil taxUtil;
    Result result;

    public Calculator(PaymentPolicy paymentPolicy, TaxPolicy taxPolicy) {
        this.paymentUtil = new PaymentUtil(paymentPolicy);
        this.taxUtil = new TaxUtil(taxPolicy);
        result = new Result();
    }

    public void setPaymentPolicy(PaymentPolicy paymentPolicy) {
        this.paymentUtil = new PaymentUtil(paymentPolicy);
    }

    public void calculate(double salary) {
        result.setPaymentResults(paymentUtil.totalPayment(salary));
        result.setTaxResult(taxUtil.tax(salary - result.paymentResults.getTotalPersonalPayment()));
        result.taxResult.setTotalTaxRate(result.taxResult.getTax() / salary);
        result.out = result.paymentResults.getTotalPersonalPayment() + result.taxResult.getTax();
        result.salary = salary;
    }

    @Data
    static class Result {
        TaxUtil.Result taxResult;
        PaymentUtil.Results paymentResults;
        double out;
        double salary;

        private double percentage(double money) {
            return 100 * money / salary;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(paymentResults).append('\n');
            sb.append(taxResult).append('\n');
            sb.append(ResultFormat.SPLIT);
            sb.append(String.format(ResultFormat.LONG_CONTENT_FORMAT, "total", out, percentage(out), paymentResults.getTotalCompanyPayment(), percentage(paymentResults.getTotalCompanyPayment()))).append('\n');
            sb.append(String.format(ResultFormat.LONG_CONTENT_FORMAT, "self", salary - out, percentage(salary - out), salary + paymentResults.getTotalCompanyPayment(), percentage(salary + paymentResults.getTotalCompanyPayment()))).append('\n');
            return sb.toString();
        }
    }

    public static void main(String[] args) {

        int a = 25000;
//        PaymentUtil paymentUtil = new PaymentUtil(PaymentPolicy.hangzhouTaxPolicy());
//        System.out.println(paymentUtil.totalPayment(4000));
//        TaxUtil taxUtil = new TaxUtil();
//        System.out.println(taxUtil.tax(4000));

        Calculator calculator = new Calculator(PaymentPolicy.hangzhouTaxPolicy(), TaxPolicy.defaultTaxPolicy());
        calculator.calculate(a);
        System.out.println(calculator.getResult());
//        calculator.setPaymentPolicy(PaymentPolicy.shanghaiTaxPolicy());
        Calculator calculator2 = new Calculator(PaymentPolicy.shanghaiTaxPolicy(), TaxPolicy.defaultTaxPolicy());
        calculator2.calculate(a);
        System.out.println(calculator2.getResult());
    }
}
