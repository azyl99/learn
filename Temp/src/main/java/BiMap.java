import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author guya on 2018/10/6
 */

// -enableassertions
public class BiMap<K1, K2> {

    public static void main(String[] args) {
        BiMap<Integer, String> map = new BiMap<>();
        map.put(1, "a1");
        map.put(2, "b2");
        map.put(3, "c3");
        map.put(4, "d4");
        System.out.println(map.containsKey1(1));
        System.out.println(map.containsKey2("a1"));
        System.out.println(map.remove1(1));
        System.out.println(map.remove2("b2"));
        System.out.println(map.get2(3));
        System.out.println(map.get1("d4"));
        System.out.println(map.size());
        System.out.println(map.remove1(5));
        map.clear();
        System.out.println(map.size());
        System.out.println(map.get1(null));
    }

    private Map<K1, K2> m1 = new HashMap<>();
    private Map<K2, K1> m2 = new HashMap<>();

    public int size() {
        assert m1.size() == m2.size();
        return m1.size();
    }

    public boolean isEmpty() {
        assert m1.size() == m2.size();
        return m1.size() == 0;
    }

    public boolean containsKey1(K1 key1) {
        return m1.containsKey(key1);
    }

    public boolean containsKey2(K2 key2) {
        return m2.containsKey(key2);
    }

    public K2 get2(K1 key1) {
        return m1.get(key1);
    }

    public K1 get1(K2 key2) {
        return m2.get(key2);
    }

    public void put(K1 key1, K2 key2) {
        m1.put(key1, key2);
        m2.put(key2, key1);
    }

    public K2 remove1(K1 key1) {
        K2 key2 = m1.remove(key1);
        if (key2 == null) {
            return key2;
        }
        K1 temp = m2.remove(key2);
        assert temp == key1;
        return key2;
    }

    public K1 remove2(K2 key2) {
        K1 key1 = m2.remove(key2);
        if (key1 == null) {
            return key1;
        }
        K2 temp = m1.remove(key1);
        assert temp == key2;
        return key1;
    }

    public void clear() {
        m1.clear();
        m2.clear();
    }
}
