import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String SPACE_REGEX = "\\s+";
    public static final String LINE_BREAK = "\n";
    public static final String CALCULATE_ERROR = "Calculate Error: ";
    public static final String SPACE = " ";

    public String getResult(String sentence) {
        try {
            return Arrays.stream(sentence.split(SPACE_REGEX))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted((current, next) -> next.getValue().compareTo(current.getValue()))
                    .map(wordFrequency -> wordFrequency.getKey() + SPACE +wordFrequency.getValue())
                    .collect(Collectors.joining(LINE_BREAK));
        } catch (Exception e) {
            return CALCULATE_ERROR + e;
        }
    }
}
