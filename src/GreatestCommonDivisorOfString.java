package src;

public class GreatestCommonDivisorOfString {

    public static void main(String[] args) {
        GreatestCommonDivisorOfString test = new GreatestCommonDivisorOfString();
        System.out.println("Answer:" + test.gcdOfStrings("ABCABC", "ABC"));
        System.out.println();
        System.out.println("Answer:" + test.gcdOfStrings("ABABAB", "ABAB"));
        System.out.println();
        System.out.println("Answer:" + test.gcdOfStrings("LEET", "CODE"));
        System.out.println();
    }

    public String gcdOfStrings(String str1, String str2) {
        String small, large, result = "";
        if (str1.length() > str2.length()) {
            large = str1;
            small = str2;
        } else {
            large = str2;
            small = str1;
        }
        for (int i = small.length(); i >= 1; i--) {
            if (small.length() % i != 0) {
                continue;
            } else if (large.length() % i != 0) {
                continue;
            } else {
                boolean gcd = true;
                String divider = small.substring(0, i);
                for (int j = 0; j + i <= small.length(); j += i) {
                    if (!divider.equals(small.substring(j, j + i))) {
                        gcd = false;
                        break;
                    }
                }
                for (int j = 0; j + i <= large.length(); j += i) {
                    if (!divider.equals(large.substring(j, j + i))) {
                        gcd = false;
                        break;
                    }
                }
                if (gcd) {
                    result = divider;
                    break;
                }
            }
        }

        return result;
    }
}
