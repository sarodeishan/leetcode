package src;

import java.time.Instant;

public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String[] input1 = new String[]{"abcde", "abc", "abc", "pmjghexybyrgzczy", "bsbininm"};
        String[] input2 = new String[]{"ace", "abc", "def", "hafcdqbgncrcbihkd", "jmjkbkjkv"};
        int[] output = new int[]{3, 3, 0, 5, 1};
        for (int i = 0; i < input1.length; i++) {
            tester(i + 1, input1[i], input2[i], output[i]);
        }
    }

    private static void tester(int testNumber, String input1, String input2, int output) {
        LongestCommonSubsequence obj = new LongestCommonSubsequence();
        Instant start = Instant.now();
        int result = obj.longestCommonSubsequence(input1, input2);
        System.out.println(result);
        System.out.println(result == output);
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public int longestCommonSubsequence(String text1, String text2) {
//        return longestCommonSubsequenceRec(text1, text2, 0, 0);
        return longestCommonSubsequenceDP(text1, text2);
    }

    private int longestCommonSubsequenceRec(String text1, String text2, int ptr1, int ptr2) {
        if (ptr1 >= text1.length() || ptr2 >= text2.length()) {
            return 0;
        } else if (text1.charAt(ptr1) == text2.charAt(ptr2)) {
            return 1 + longestCommonSubsequenceRec(text1, text2, ptr1 + 1, ptr2 + 1);
        } else {
            return Math.max(longestCommonSubsequenceRec(text1, text2, ptr1, ptr2 + 1), longestCommonSubsequenceRec(text1, text2, ptr1 + 1, ptr2));
        }
    }

    private int longestCommonSubsequenceDP(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = 1 + dp[i][j];
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

}