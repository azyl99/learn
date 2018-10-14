package salarycalculator;

import lombok.Data;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author guya
 * @date 2018/8/17
 */
@Getter
public class PaymentPolicy {

    private List<Payment> payments = new ArrayList<>();

    private PaymentPolicy() {
    }

    public PaymentPolicy(String city) {
        PaymentPolicy paymentPolicy = cityPolicyMap.get(city);
        if (paymentPolicy == null) {
            throw new RuntimeException("xxx");
        }
        payments = paymentPolicy.getPayments();
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

    /**
     * （静态）变量和（静态）代码块的也是有执行顺序的，与代码书写的顺序一致。
     * 在（静态）代码块中可以使用（静态）变量，但是被使用的（静态）变量必须在（静态）代码块前面声明。
     */
    static Map<String, String> provinceCityMap = new HashMap<>();
    static Map<String, PaymentPolicy> cityPolicyMap = new HashMap<>();
    static {
        paymentPolicyParser("salarycalculator/payment_policy.xml");
    }

    private static void paymentPolicyParser(String resourcePath) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        InputStream xmlInputStream;
        try {
            System.out.println("Reading Resource " + resourcePath + "...");
//            xmlInputStream = new FileInputStream(new File(Resources.getResource(resourcePath).getFile()));// 也可以
            xmlInputStream = new ClassPathResource(resourcePath).getInputStream();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(xmlInputStream);
            Node policy = document.getChildNodes().item(0);// 根节点 <policy>
            NodeList provinces = policy.getChildNodes();
            for (int i = 0; i < provinces.getLength(); i++) {
                Node province = provinces.item(i);
                if (province.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                String provinceName = province.getAttributes().getNamedItem("name").getNodeValue();

                NodeList cities = province.getChildNodes();
                for (int j = 0; j < cities.getLength(); j++) {
                    Node city = cities.item(j);
                    // 还有COMMENT_NODE等，所以下面的代码不可以用
                    // if (city.getNodeType() == Node.TEXT_NODE) {
                    if (city.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    NamedNodeMap cityAttributes = city.getAttributes();
                    String cityName = cityAttributes.getNamedItem("name").getNodeValue();
                    String version = cityAttributes.getNamedItem("version").getNodeValue();

                    PaymentPolicy cityPaymentPolicy = new PaymentPolicy();
                    provinceCityMap.put(provinceName, cityName);
                    cityPolicyMap.put(cityName, cityPaymentPolicy);
                    Map<String, Double> lowerBoundMap = new HashMap<>();
                    Map<String, Double> upperBoundMap = new HashMap<>();

                    NodeList citySons = city.getChildNodes();
                    for (int k = 0; k < citySons.getLength(); k++) {
                        Node node = citySons.item(k);
                        switch (node.getNodeName()) {
                            case "bounds":
                                NodeList bounds = node.getChildNodes();
                                for (int l = 0; l < bounds.getLength(); l++) {
                                    Node bound = bounds.item(l);
                                    if (bound.getNodeType() != Node.ELEMENT_NODE) {
                                        continue;
                                    }
                                    String boundType = bound.getAttributes().getNamedItem("type").getNodeValue();

                                    Double lower = null;
                                    Double upper = null;
                                    for (int m = 0; m < bound.getChildNodes().getLength(); m++) {
                                        Node temp = bound.getChildNodes().item(m);
                                        if (temp.getNodeType() != Node.ELEMENT_NODE) {
                                            continue;
                                        }
                                        switch (temp.getNodeName()) {
                                            case "lower":
                                                lower = Double.valueOf(temp.getTextContent());
                                                break;
                                            case "upper":
                                                upper = Double.valueOf(temp.getTextContent());
                                                break;
                                        }
                                    }
                                    lowerBoundMap.put(boundType, lower);
                                    upperBoundMap.put(boundType, upper);
                                }
                                break;
                            case "payments":
                                NodeList payments = node.getChildNodes();
                                for (int l = 0; l < payments.getLength(); l++) {
                                    Node paymentNode = payments.item(l);
                                    if (paymentNode.getNodeType() != Node.ELEMENT_NODE) {
                                        continue;
                                    }
                                    String paymentName = paymentNode.getAttributes().getNamedItem("name").getNodeValue();
                                    String paymentType = paymentNode.getAttributes().getNamedItem("type").getNodeValue();
                                    Double personalRate = null;
                                    Double companyRate = null;
                                    for (int m = 0; m < paymentNode.getChildNodes().getLength(); m++) {
                                        Node temp = paymentNode.getChildNodes().item(m);
                                        if (temp.getNodeType() != Node.ELEMENT_NODE) {
                                            continue;
                                        }
                                        switch (temp.getNodeName()) {
                                            case "personal":
                                                personalRate = Double.valueOf(temp.getTextContent());
                                                break;
                                            case "company":
                                                companyRate = Double.valueOf(temp.getTextContent());
                                                break;
                                        }
                                    }
                                    Double lower = lowerBoundMap.get(paymentType);
                                    Double upper = upperBoundMap.get(paymentType);
                                    Payment payment = new Payment(paymentName, lower, upper, personalRate, companyRate);
                                    cityPaymentPolicy.getPayments().add(payment);
                                }
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(provinceCityMap);
        System.out.println(cityPolicyMap);
    }

}