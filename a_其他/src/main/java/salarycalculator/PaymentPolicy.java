package salarycalculator;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * @author guya
 * @date 2018/8/17
 */

@Data
public class PaymentPolicy {

    private List<Payment> payments;

    // TODO: 添加XML配置
    static public PaymentPolicy hangzhouTaxPolicy() {
        PaymentPolicy paymentPolicy = new PaymentPolicy();
        final double socialLowerBound = 3054.95;
        final double socialUpperBound = 15274.74;
        final double fundLowerBound = 4862.2;
        final double fundUpperBound = 24311;
        paymentPolicy.setPayments(Arrays.asList(
                new Payment("house", fundLowerBound, fundUpperBound, 0.12, 0.12),
                new Payment("pension", socialLowerBound, socialUpperBound, 0.08, 0.14),
                new Payment("medical", socialLowerBound, socialUpperBound, 0.02, 0.115, 4),
                new Payment("unemploy", socialLowerBound, socialUpperBound, 0, 0.015),
                new Payment("birth", socialLowerBound, socialUpperBound, 0, 0.006),
                new Payment("injury", socialLowerBound, socialUpperBound, 0, 0.008)
        ));
        return paymentPolicy;
    }

    static public PaymentPolicy shanghaiTaxPolicy() {
        PaymentPolicy paymentPolicy = new PaymentPolicy();
        final double socialLowerBound = 4279;
        final double socialUpperBound = 21396;
        final double fundLowerBound = 4279;
        final double fundUpperBound = 21396;
        paymentPolicy.setPayments(Arrays.asList(
                new Payment("house", fundLowerBound, fundUpperBound, 0.07, 0.07),
                new Payment("pension", socialLowerBound, socialUpperBound, 0.08, 0.21),
                new Payment("medical", socialLowerBound, socialUpperBound, 0.02, 0.11),
                new Payment("unemploy", socialLowerBound, socialUpperBound, 0.005, 0.015),
                new Payment("birth", socialLowerBound, socialUpperBound, 0, 0.01),
                new Payment("injury", socialLowerBound, socialUpperBound, 0, 0.005)
        ));
        return paymentPolicy;
    }

    @Data
    static class Payment {

        private String type;
        private double lowerBound;
        private double upperBound;
        private double personalRate;
        private double companyRate;
        private int constDelta;

        public Payment(String type, double lowerBound, double upperBound, double personalRate, double companyRate) {
            this(type, lowerBound, upperBound, personalRate, companyRate, 0);
        }

        public Payment(String type, double lowerBound, double upperBound, double personalRate, double companyRate, int constDelta) {
            this.type = type;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
            this.personalRate = personalRate;
            this.companyRate = companyRate;
            this.constDelta = constDelta;
        }
    }
}