package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));
    }

    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int[] product = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            int n1 = num1.charAt(i)-'0';
            for (int j = 0; j < num2.length(); j++) {
                int n2 = num2.charAt(j)-'0';
                product[i+j+1] += n1*n2;
            }
        }
        System.out.println(Arrays.toString(product));
        for (int i = num1.length() + num2.length() - 1; i > 0 ; i--) {
            int curr = product[i];
            product[i] = curr%10;
            product[i-1] += curr/10;
        }
        System.out.println(Arrays.toString(product));
        StringBuilder sb = new StringBuilder();
        if (product[0] != 0) {
            sb.append(product[0]);
        }
        for (int i = 1; i < num1.length() + num2.length(); i++) {
            sb.append(product[i]);
            System.out.println(product[i]);
        }
        return sb.toString();
    }

}