import java.util.*;

public class WordFrequencyGame {
    public String getResult(String sentence) {
        if (sentence.split("\\s+").length == 1) {
            return sentence + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                //get the wordFrequencyMap for the next step of sizing the same word
                Map<String, List<WordFrequency>> wordFrequencyMap = computeWordFrequency(words);

                List<WordFrequency> wordFrequencyList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : wordFrequencyMap.entrySet()) {
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordFrequencyList.add(wordFrequency);
                }

                wordFrequencyList.sort((w1, w2) -> w2.getCount() - w1.getCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordFrequency w : wordFrequencyList) {
                    String s = w.getWord() + " " + w.getCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private Map<String, List<WordFrequency>> computeWordFrequency(String[] words) {
        Map<String, List<WordFrequency>> wordFrequencyMap = new HashMap<>();
        for (String word : words) {
//       wordFrequencyMap.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!wordFrequencyMap.containsKey(word)) {
                ArrayList arr = new ArrayList<>();
                arr.add(new WordFrequency(word, 1));
                wordFrequencyMap.put(word, arr);
            } else {
                wordFrequencyMap.get(word).add(new WordFrequency(word, 1));
            }
        }
        return wordFrequencyMap;
    }

}
