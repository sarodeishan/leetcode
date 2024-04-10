package src;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanMakePallindromeFromSubstring implements TreeTemplate {

    record TestCase(String s, int[][] queries, List<Boolean> output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
//                new TestCase("abcda", new int[][]{{3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}}, List.of(true, false, false, true, true)),
//                new TestCase("lyb", new int[][]{{0, 1, 0}, {2, 2, 1}}, List.of(false, true)),
                new TestCase("rkzavgdmdgt", new int[][]{{8, 10, 0}}, List.of(false))
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        CanMakePallindromeFromSubstring obj = new CanMakePallindromeFromSubstring();
        Instant start = Instant.now();
        var result = obj.canMakePaliQueries(testCase.s(), testCase.queries());
        boolean success = result.equals(testCase.output());
        StringBuilder message = new StringBuilder();
        message.append("Test:").append(testNumber).append(" took:").append(start.getNano() - Instant.now().getNano()).append("ns")
                .append(" ").append("Success:").append(success)
                .append(" ").append("Output:").append(result);
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        int[][] prefixSum = prefixSum(s);
        for (int i = 0; i < queries.length; i++) {
            int count = countNonPaliLetter(prefixSum, queries[i][0], queries[i][1]);
            result.add(count <= queries[i][2]);
        }
        return result;
    }

    private int[][] prefixSum(String input) {
        int[][] sum = new int[input.length() + 1][26];
        sum[1][input.charAt(0) - 'a']++;
        for (int i = 2; i < input.length()+1; i++) {
            System.arraycopy(sum[i - 1], 0, sum[i], 0, 26);
            sum[i][input.charAt(i - 1) - 'a']++;
        }
        return sum;
    }

    private int countNonPaliLetter(int[][] prefixSum, int start, int end) {
        int[] letterCount = new int[26];
        System.arraycopy(prefixSum[end + 1], 0, letterCount, 0, 26);
        for (int i = 0; i < letterCount.length; i++) {
            letterCount[i] = letterCount[i] - prefixSum[start][i];
        }
        int count = 0;
        for (int j : letterCount) {
            if (j % 2 == 1) {
                count++;
            }
        }
        System.out.println(Arrays.toString(prefixSum[end+1]));
        System.out.println(Arrays.toString(prefixSum[start]));
        System.out.println(Arrays.toString(letterCount));
        System.out.println(count / 2);
        return count / 2;
    }

}