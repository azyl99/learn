package salarycalculator;

import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
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

    public Calculator(PaymentPolicy paymentPolicy) {
        this(paymentPolicy, TaxPolicy.defaultTaxPolicy);
    }

    public Calculator(PaymentPolicy paymentPolicy, TaxPolicy taxPolicy) {
        this.paymentUtil = new PaymentUtil(paymentPolicy);
        this.taxUtil = new TaxUtil(taxPolicy);
        result = new Result();
    }

    public Result calculate(double salary) {
        result.setPaymentResults(paymentUtil.totalPayment(salary));
        result.setTaxResult(taxUtil.tax(salary - result.paymentResults.getTotalPersonalPayment()));
        result.taxResult.setTotalTaxRate(result.taxResult.getTax() / salary);
        result.totalOut = result.paymentResults.getTotalPersonalPayment() + result.taxResult.getTax();
        result.salary = salary;
        return result;
    }

    @Data
    static class Result {
        TaxUtil.Result taxResult;
        PaymentUtil.Results paymentResults;
        double totalOut;
        double salary;

        private double percentage(double money) {
            return 100 * money / salary;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(paymentResults).append('\n');
            sb.append(taxResult.toString(true)).append('\n');
            sb.append(ResultFormat.SPLIT);
            sb.append(String.format(ResultFormat.LONG_CONTENT_FORMAT, "total", totalOut, percentage(totalOut), paymentResults.getTotalCompanyPayment(), percentage(paymentResults.getTotalCompanyPayment()))).append('\n');
            sb.append(String.format(ResultFormat.LONG_CONTENT_FORMAT, "self", salary - totalOut, percentage(salary - totalOut), salary + paymentResults.getTotalCompanyPayment(), percentage(salary + paymentResults.getTotalCompanyPayment()))).append('\n');
            return sb.toString();
        }
    }

    public static void main(String[] args) {

//        try {
//            Enumeration<URL> urls =  ClassLoader.getSystemResources("");
//
//            while(urls.hasMoreElements()) {
//                URL url = urls.nextElement();
//                System.out.println(url);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        test();
    }








    public static void test() {
        int a = 25000;
//        System.out.println(new Calculator(new PaymentPolicy("Hangzhou")).calculate(a));
//        System.out.println(new Calculator(new PaymentPolicy("Shanghai")).calculate(a));
//        System.out.println(new Calculator(new PaymentPolicy("Ningbo")).calculate(a));
        a = 25000;
        System.out.println(new Calculator(new PaymentPolicy("Hangzhou"), TaxPolicy.oldTaxPolicy).calculate(a));
        System.out.println(new Calculator(new PaymentPolicy("Hangzhou"), TaxPolicy.defaultTaxPolicy).calculate(a));
//        System.out.println(new Calculator(new PaymentPolicy("Shanghai")).calculate(a));
//        System.out.println(new Calculator(new PaymentPolicy("Ningbo")).calculate(a));
    }
}
