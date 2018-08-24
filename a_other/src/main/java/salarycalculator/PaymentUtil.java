package salarycalculator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author guya
 * @date 2018/8/16
 */

@Data
public class PaymentUtil {

    private PaymentPolicy paymentPolicy;

    public PaymentUtil(PaymentPolicy paymentPolicy) {
        this.paymentPolicy = paymentPolicy;
    }

    public Results totalPayment(double salary) {
        Results results = new Results(salary);
        for (PaymentPolicy.Payment payment : paymentPolicy.getPayments()) {
            Results.Result result = new Results.Result();
            double lowerBound = payment.getLowerBound();
            double upperBound = payment.getUpperBound();
            double personalRate = payment.getPersonalRate();
            double companyRate = payment.getCompanyRate();
            result.personalPayment = payment.getConstDelta();
            if (salary < lowerBound) {
                result.personalPayment += lowerBound * personalRate;
                result.companyPayment = lowerBound * companyRate;
            } else if (salary < upperBound) {
                result.personalPayment += salary * personalRate;
                result.companyPayment = salary * companyRate;
            } else {
                result.personalPayment += upperBound * personalRate;
                result.companyPayment = upperBound * companyRate;
            }
            result.type = payment.getType();
            result.salary = salary;
            results.addResult(result);
            results.refresh(result.personalPayment, result.companyPayment);
        }
        return results;
    }


    @Data
    static class Results {

        private List<Result> results = new ArrayList<>();
        private double totalPersonalPayment;
        private double totalCompanyPayment;
        private double salary;

        public Results(double salary) {
            this.salary = salary;
        }

        public void refresh(double personalPayment, double companyPayment) {
            totalPersonalPayment += personalPayment;
            totalCompanyPayment += companyPayment;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(String.format(ResultFormat.TITLE_FORMAT, "", "personal   ", "company   "));
            for (Result result : results) {
                sb.append(result).append('\n');
            }
            sb.append(ResultFormat.SPLIT);
            sb.append(String.format(ResultFormat.LONG_CONTENT_FORMAT, "payment",
                            totalPersonalPayment, 100 * totalPersonalPayment / salary,
                            totalCompanyPayment, 100 * totalCompanyPayment / salary));
            return sb.toString();
        }

        @Data
        static class Result {
            private String type;
            private double personalPayment;
            private double companyPayment;
            private double salary;

            public String toString() {
                return String.format(ResultFormat.LONG_CONTENT_FORMAT, type,
                        personalPayment, 100 * personalPayment / salary,
                        companyPayment, 100 * companyPayment / salary);
            }
        }
    }
}
