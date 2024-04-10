package src;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class LongestChunkedPallindromeDecompoisition implements TreeTemplate {

    record TestCase(String input, int output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase("ghiabcdefhelloadamhelloabcdefghi", 7),
                new TestCase("merchant", 1),
                new TestCase("antaprezatepzapreanta", 11),
                new TestCase("vwsuvmbwknmnvwsuvmbwk", 5)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        LongestChunkedPallindromeDecompoisition obj = new LongestChunkedPallindromeDecompoisition();
        Instant start = Instant.now();
        int result = obj.longestDecomposition(testCase.input());
        System.out.println(result);
        System.out.println(result == testCase.output());
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public int longestDecomposition(String text) {
        String input = text;
        int result = 0;
        while (!input.isEmpty()) {
            int start = 0, end = input.length() - 1;
            for (; end > start; end--) {
                if (input.charAt(start) == input.charAt(end)) {
                    if (input.substring(end).equals(input.substring(start, start + input.length() - end))) {
                        break;
                    }
                }
            }
            int len = input.length() - end;
            if (start != end) {
                if (input.substring(end).equals(input.substring(start, start + len))) {
                    result += 2;
                    input = input.substring(0, end);
                    input = input.substring(start + len, end);
                } else {
                    result++;
                    input = "";
                }
            } else {
                result++;
                input = "";
            }
        }
        return result;
    }

}
