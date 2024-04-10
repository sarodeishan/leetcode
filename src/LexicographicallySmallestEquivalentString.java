package src;

import java.util.*;

public class LexicographicallySmallestEquivalentString {

    public static void main(String[] args) {
        LexicographicallySmallestEquivalentString test = new LexicographicallySmallestEquivalentString();
        System.out.println("Answer:" + test.smallestEquivalentString("cgokcgerolkgksgbhgmaaealacnsshofjinidiigbjerdnkolc", "rjjlkbmnprkslilqmbnlasardrossiogrcboomrbcmgmglsrsj", "bxbwjlbdazfejdsaacsjgrlxqhiddwaeguxhqoupicyzfeupcn"));
        System.out.println();
        System.out.println("Answer:" + test.smallestEquivalentString("abc", "cde", "eed"));
        System.out.println();
        System.out.println("Answer:" + test.smallestEquivalentString("parker", "morris", "parser"));
        System.out.println();
        System.out.println("Answer:" + test.smallestEquivalentString("hello", "world", "hold"));
        System.out.println();
        System.out.println("Answer:" + test.smallestEquivalentString("leetcode", "programs", "sourcecode"));
        System.out.println();
        System.out.println("Answer:" + test.smallestEquivalentString("dfeffdfafbbebbebacbbdfcfdbcacdcbeeffdfebbdebbdafff", "adcdfabadbeeafeabbadcefcaabdecabfecffbabbfcdfcaaae", "myickvflcpfyqievitqtwvfpsrxigauvlqdtqhpfugguwfcpqv"));
        System.out.println();
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        Map<Character, Character> equivalence = findEquivalence(s1, s2);
        StringBuilder result = new StringBuilder();
        for (Character c : baseStr.toCharArray()) {
            result.append(equivalence.getOrDefault(c, c));
        }
        return result.toString();
    }

    private Map<Character, Character> findEquivalence(String s1, String s2) {
        List<HashSet<Character>> allSets = new LinkedList<>();
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            HashSet<Character> set = allSets.stream().filter(characters -> characters.contains(c1)).findAny().orElse(new HashSet<>());
            HashSet<Character> set2 = allSets.stream().filter(characters -> characters.contains(c2)).findAny().orElse(new HashSet<>());
            if (!set.isEmpty() && !set2.isEmpty()) {
                set.addAll(set2);
                allSets.remove(set2);
                allSets.add(set);
            } else if (set.isEmpty() && !set2.isEmpty()) {
                set = set2;
            } else if (!set.isEmpty()) {
            } else {
                allSets.add(set);
            }
            set.add(c1);
            set.add(c2);
        }
        Map<Character, Character> equivalance = new HashMap<>();
        for (HashSet<Character> set : allSets) {
            char smallestChar = set.stream().min(Comparator.naturalOrder()).orElse('z');
            set.stream().forEach(character -> {
                Character relation = equivalance.get(character);
                if (relation == null || relation > smallestChar) {
                    equivalance.put(character, smallestChar);
                }
            });
        }
        return equivalance;
    }

}
