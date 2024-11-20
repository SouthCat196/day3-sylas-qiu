import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";

    public String getResult(String sentence) {
        if (sentence.split(SPACE_REGEX).length == 1) {
            return sentence + " 1";
        } else {
            try {
                List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);

                return getResult(wordFrequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String getResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + " " + wordFrequency.getCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getInitialWordFrequencies(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(SPACE_REGEX);

        //get the wordToWordFrequencies for the next step of sizing the same word
        Map<String, List<WordFrequency>> wordToWordFrequencies = computeWordFrequency(words);

        List<WordFrequency> wordFrequencies = wordToWordFrequencies.entrySet()
                .stream()
                .map(wordFrequencyEntry ->
                        new WordFrequency(wordFrequencyEntry.getKey(), wordFrequencyEntry.getValue().size()))
                .sorted(((o1, o2) -> o2.getCount() - o1.getCount()))
                .toList();
        return wordFrequencies;
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
