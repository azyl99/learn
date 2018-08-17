import com.baidu.aip.ocr.AipOcr;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文档地址https://ai.baidu.com/docs#/OCR-Java-SDK/top
 */
public class Sample {

    // 设置APPID/AK/SK
    public static final String APP_ID = "11635459";
    public static final String API_KEY = "EhwYeu7Utu5K5fhOHUtX6Do3";
    public static final String SECRET_KEY = "PeFSucdUD3dlj3Cw7gm3izw2m2nwFE5I";
    AipOcr client;

    public Sample() {
        // 初始化一个AipOcr
        client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        System.setProperty("aip.log4j.conf", "log4j.properties");
    }

    public List<String> recognize(String path) {
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        JSONArray results = (JSONArray) res.get("words_result");
        List<String> words = new ArrayList<>();
        for (Object result: results) {
            words.add((String) ((JSONObject)result).get("words"));;
        }
        return words;
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        String path = "D:\\Python\\test2.jpg";
        List<String> words = sample.recognize(path);
        for (String word: words) {
            System.out.println(word);
        }
    }
}