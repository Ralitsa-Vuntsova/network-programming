import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCounterImpl implements WordCounter {
    public Map<String, Integer> countWords(String line) {
       String[] words = line.replaceAll("[\"',.?!]", "").split(" ");
        Map<String, Integer> wordCounter = new LinkedHashMap<>();
        Map<Integer, Integer> counters = new HashMap<>();

        for (String word : words) {
            if(counters.containsKey(word.length())) {
                int counter = counters.get(word.length());

                counters.put(word.length(), counter + 1);
                wordCounter.put(word, counter + 1);
            } else {
                wordCounter.put(word, 1);
                counters.put(word.length(), 1);
            }
        }

        return wordCounter;
    }
}
