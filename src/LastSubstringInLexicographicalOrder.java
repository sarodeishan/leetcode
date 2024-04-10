package src;

import java.time.Instant;
import java.util.Objects;

public class LastSubstringInLexicographicalOrder implements TreeTemplate {

    record TestCase(String input, String output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase("abab", "bab"),
                new TestCase("leetcode", "tcode"),
                new TestCase("zzokjuhlnhcbpauxkcydzpjfhqncdqejryelozadcnqipgqbcklwpbgzdwlamvdixjzjaboyupgchwjwkvjgprbkuywufxarlifgytbslqrpbquykukzodkhjkgwzmvqxmvaehpcbbvioxdoawsbvmfbceyzurlhxawdicnsdhflwoaioeehoghixiwsbxbqvctmpdfauvzduxrqxesmgmnnddotyynucsdalgrxzddndqmmealzaxlvhnkimvmgsiiitpmfhgcmobpqnnlezewxyzmouiyftaeceqrxjrakjeydobatmydbbgjxpwizkafsjrphqcntwzyjmuryojfcogrx", "zzokjuhlnhcbpauxkcydzpjfhqncdqejryelozadcnqipgqbcklwpbgzdwlamvdixjzjaboyupgchwjwkvjgprbkuywufxarlifgytbslqrpbquykukzodkhjkgwzmvqxmvaehpcbbvioxdoawsbvmfbceyzurlhxawdicnsdhflwoaioeehoghixiwsbxbqvctmpdfauvzduxrqxesmgmnnddotyynucsdalgrxzddndqmmealzaxlvhnkimvmgsiiitpmfhgcmobpqnnlezewxyzmouiyftaeceqrxjrakjeydobatmydbbgjxpwizkafsjrphqcntwzyjmuryojfcogrx")
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        LastSubstringInLexicographicalOrder obj = new LastSubstringInLexicographicalOrder();
        Instant start = Instant.now();
        var result = obj.lastSubstring(testCase.input());
        boolean success = (Objects.equals(result, testCase.output()));
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

    public String lastSubstring(String s) {
        int maxIndex = s.length() - 1;
        for (int currIndex = s.length() - 1; currIndex >= 0; currIndex--) {
            if (s.charAt(currIndex) > s.charAt(maxIndex)) {
                maxIndex = currIndex;
            } else if (s.charAt(currIndex) == s.charAt(maxIndex)) {
                int i = currIndex + 1;
                int j = maxIndex + 1;
                while (i < maxIndex && j < s.length() && s.charAt(i) == s.charAt(j)) {
                    i++;
                    j++;
                }
                if (i == maxIndex || j == s.length() || s.charAt(i) > s.charAt(j)) {
                    maxIndex = currIndex;
                }
            }
        }
        return s.substring(maxIndex);
    }

}