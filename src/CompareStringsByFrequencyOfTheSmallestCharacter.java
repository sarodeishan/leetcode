package src;

import java.time.Instant;
import java.util.Arrays;
import java.util.Objects;

public class CompareStringsByFrequencyOfTheSmallestCharacter implements TreeTemplate {

    record TestCase(String[] queries, String[] words, int[] output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(new String[]{"cbd"}, new String[]{"zaaaz"}, new int[]{1}),
                new TestCase(new String[]{"bbb", "cc"}, new String[]{"a", "aa", "aaa", "aaaa"}, new int[]{1, 2}),
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        CompareStringsByFrequencyOfTheSmallestCharacter obj = new CompareStringsByFrequencyOfTheSmallestCharacter();
        Instant start = Instant.now();
        var result = obj.numSmallerByFrequency(testCase.queries(), testCase.words());
        boolean success = (Arrays.equals(result, testCase.output()));
        StringBuilder message = new StringBuilder();
        message.append("Test:").append(testNumber).append(" took:").append(start.getNano() - Instant.now().getNano()).append("ns")
                .append(" ").append("Success:" + (success))
                .append(" ").append("Output:").append(Arrays.toString(result));
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] result = new int[queries.length];

        int[] freqWords = new int[11];
        for (String word : words) {
            freqWords[frequencyOfLexicographicallySmallestCharacter(word)]++;
        }
        for (int i = freqWords.length - 1; i > 0; i--) {
            freqWords[i - 1] += freqWords[i];
        }

        for (int i = 0; i < queries.length; i++) {
            int queryFreq = frequencyOfLexicographicallySmallestCharacter(queries[i]);
            if (queryFreq != 10) {
                result[i] = freqWords[queryFreq + 1];
            }
        }
        return result;
    }

    private int frequencyOfLexicographicallySmallestCharacter(String input) {
        int freq = 0;
        char smallest = input.charAt(0);
        for (char letter : input.toCharArray()) {
            if (letter == smallest) {
                freq++;
            } else if (letter < smallest) {
                smallest = letter;
                freq = 1;
            }
        }
        return freq;
    }

}