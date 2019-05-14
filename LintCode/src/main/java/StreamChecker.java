import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guya on 2019/4/26
 */
class StreamChecker {

    private Map<Character, List<CharInWord>> charMap = new HashMap<>();
    private Set<CharInWord> queryRecord = new HashSet<>();
    private int queryCount = 0;

    private void addToMap(char ch, CharInWord charInWord) {
        charMap.computeIfAbsent(ch, k -> new ArrayList<>());
        charMap.get(ch).add(charInWord);
    }

    public StreamChecker(String[] words) {
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                addToMap(word.charAt(i), new CharInWord(word, i, 0));
            }
        }
    }

    public boolean query(char letter) {
        queryCount++;
        List<CharInWord> list = charMap.get(letter);
        for (CharInWord charInWord : list) {
            charInWord.queryId = queryCount;
        }

        return false;
    }

    static class CharInWord {
        String word;
        int charIndex;
        int queryId;

        public CharInWord(String word, int charIndex, int queryId) {
            this.word = word;
            this.charIndex = charIndex;
            this.queryId = queryId;
        }
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
