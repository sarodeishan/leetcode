package src;

import java.time.Instant;
import java.util.Arrays;

public class FindWordsThatCanBeFormedByCharacters implements TreeTemplate {

    record TestCase(String[] words, String chars, int output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(new String[]{"cat", "bt", "hat", "tree"}, "atach", 6),
                new TestCase(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr", 10)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        FindWordsThatCanBeFormedByCharacters obj = new FindWordsThatCanBeFormedByCharacters();
        Instant start = Instant.now();
        var result = obj.countCharacters(testCase.words(), testCase.chars());
        boolean success = (result == testCase.output());
        StringBuilder message = new StringBuilder();
        message.append("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns")
                .append(" ").append("Success:" + (success))
                .append(" ").append("Output:" + result);
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    public int countCharacters(String[] words, String chars) {
        int[] freq = new int[26];
        for (char c : chars.toCharArray()) {
            freq[c - 'a']++;
        }
        int result = 0;
        for (String word : words) {
            result += countWordLength(word, freq);
        }
        return result;
    }

    private int countWordLength(String word, int[] count) {
        int[] freq = new int[26];
        int length = word.length();
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
            if (freq[c - 'a'] > count[c - 'a']) {
                length = 0;
                break;
            }
        }
        return length;
    }

}
