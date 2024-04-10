package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LongestStringChain {

    public static void main(String[] args) {
        LongestStringChain test = new LongestStringChain();
        System.out.println("Answer:" + test.longestStrChain(new String[]{"a", "b", "ba", "bca", "bda", "bdca"}));
        System.out.println();
        System.out.println("Answer:" + test.longestStrChain(new String[]{"xbc", "pcxbcf", "xb", "cxbc", "pcxbc"}));
        System.out.println();
        System.out.println("Answer:" + test.longestStrChain(new String[]{"abcd", "dbqca"}));
        System.out.println();
        System.out.println("Answer:" + test.longestStrChain(new String[]{"czgxmxrpx", "lgh", "bj", "cheheex", "jnzlxgh", "nzlgh", "ltxdoxc", "bju", "srxoatl", "bbadhiju", "cmpx", "xi", "ntxbzdr", "cheheevx", "bdju", "sra", "getqgxi", "geqxi", "hheex", "ltxdc", "nzlxgh", "pjnzlxgh", "e", "bbadhju", "cmxrpx", "gh", "pjnzlxghe", "oqlt", "sarxoatl", "ee", "bbadju", "lxdc", "geqgxi", "oqltu", "heex", "oql", "eex", "bbdju", "ntxubzdr", "sroa", "cxmxrpx", "cmrpx", "ltxdoc", "cgxmxrpx", "nlgh", "sroat", "sroatl", "fcheheevx", "gxi", "gqxi", "heheex"}));
        System.out.println();
        System.out.println("Answer:" + test.longestStrChain(new String[]{"a", "ab", "ac", "bd", "abc", "abd", "abdd"}));
        System.out.println();
    }

    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()));
        int[] dp = new int[words.length];
        Arrays.fill(dp, -1);
        int maxChain = 0;
        for (int i = 0; i < words.length; i++) {
            maxChain = Math.max(maxChain, recurseLongestStrChain(words, i, 1, dp));
        }
        return maxChain == 0 ? 1 : maxChain;
    }

    private boolean isPredecessor(String a, String b) {
        if (b.length() != a.length() + 1) {
            return false;
        }
        int diff = 0;
        for (int i = 0; i < b.length() && i - diff < a.length(); i++) {
            if (b.charAt(i) != a.charAt(i - diff)) {
                diff++;
            }
            if (diff > 1) {
                return false;
            }
        }
        return true;
    }

    private int recurseLongestStrChain(String[] words, int index, int chainLength, int[] dp) {
        if (dp[index] != -1) {
            return dp[index];
        }
        String a = words[index];
        List<Integer> nextWords = new ArrayList<>();
        for (int j = index + 1; j < words.length && words[j].length() <= a.length() + 1; j++) {
            if ((words[j].length() == a.length() + 1) && isPredecessor(a, words[j])) {
                nextWords.add(j);
            }
        }
        int maxChainLength = chainLength;
        for (int nextWord : nextWords) {
            maxChainLength = Math.max(maxChainLength, recurseLongestStrChain(words, nextWord, chainLength + 1, dp));
        }
        dp[index] = maxChainLength;
        return maxChainLength;
    }
}