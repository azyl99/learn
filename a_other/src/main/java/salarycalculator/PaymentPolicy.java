package salarycalculator;

import com.google.common.io.Resources;
import lombok.Data;
import lombok.Getter;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.security.provider.PolicyParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        paymentPolicyParser("payment_policy.xml");
    }

    private static void paymentPolicyParser(String resource) {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        FileInputStream xmlInputStream = null;
        try {
            System.out.println("Reading Resource " + resource + "...");
            xmlInputStream = new FileInputStream(new File(Resources.getResource(resource).getFile()));
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
//                System.out.println(provinceName);

                NodeList cities = province.getChildNodes();
                for (int j = 0; j < cities.getLength(); j++) {
                    double socialLowerBound = 0;
                    double socialUpperBound = 0;
                    double fundLowerBound = 0;
                    double fundUpperBound = 0;
                    Node city = cities.item(j);
                    // 还有COMMENT_NODE等，所以下面的代码不可以用
                    // if (city.getNodeType() == Node.TEXT_NODE) {
                    if (city.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    NamedNodeMap cityAttributes = city.getAttributes();
                    String cityName = cityAttributes.getNamedItem("name").getNodeValue();
                    String version = cityAttributes.getNamedItem("version").getNodeValue();
//                    System.out.println(" " + cityName + " " + version);

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
//                                    System.out.println(bound.getNodeName());
                                    String boundType = bound.getAttributes().getNamedItem("type").getNodeValue();

                                    for (int m = 0; m < bound.getChildNodes().getLength(); m++) {
                                        Node temp = bound.getChildNodes().item(m);
                                        if (temp.getNodeType() != Node.ELEMENT_NODE) {
                                            continue;
                                        }
                                        Double lower = Double.valueOf(temp.getTextContent());
                                        Double upper = Double.valueOf(temp.getTextContent());
                                        lowerBoundMap.put(boundType, lower);
                                        upperBoundMap.put(boundType, upper);
                                    }
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
                                    double personalRate = Double.valueOf(paymentNode.getChildNodes().item(1).getTextContent());
                                    double companyRate = Double.valueOf(paymentNode.getChildNodes().item(3).getTextContent());
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