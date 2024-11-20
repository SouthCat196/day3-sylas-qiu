import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error: ";
    public static final String SPACE = " ";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencies = getInitialWordFrequencies(sentence);
            return getResult(wordFrequencies);
        } catch (Exception e) {
            return CALCULATE_ERROR + e;
        }
    }

    private static String getResult(List<WordFrequency> wordFrequencies) {
        return wordFrequencies.stream()
                .map(wordFrequency -> wordFrequency.getWord() + SPACE + wordFrequency.getCount())
                .collect(Collectors.joining(LINE_BREAK));
    }

    private List<WordFrequency> getInitialWordFrequencies(String sentence) {
        String[] words = sentence.split(SPACE_REGEX);

        Map<String, List<WordFrequency>> wordToWordFrequencies = computeWordFrequency(words);

        return wordToWordFrequencies.entrySet()
                .stream()
                .map(wordFrequencyEntry ->
                        new WordFrequency(wordFrequencyEntry.getKey(), wordFrequencyEntry.getValue().size()))
                .sorted(((o1, o2) -> o2.getCount() - o1.getCount()))
                .toList();
    }

    private Map<String, List<WordFrequency>> computeWordFrequency(String[] words) {
        return Arrays.stream(words)
                .map(word -> new WordFrequency(word, 1))
                .collect(Collectors.groupingBy(WordFrequency::getWord));
    }

}
