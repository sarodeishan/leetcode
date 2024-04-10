package src;

import java.util.*;

class AddingTwoNegaBinaryNumber {

    public static void main(String[] args) {
        AddingTwoNegaBinaryNumber obj = new AddingTwoNegaBinaryNumber();
        System.out.println(Arrays.toString(obj.addNegabinary(new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 1})));
        System.out.println();
        System.out.println(Arrays.toString(obj.addNegabinary(new int[]{0}, new int[]{0})));
        System.out.println();
        System.out.println(Arrays.toString(obj.addNegabinary(new int[]{0}, new int[]{1})));
        System.out.println();
        System.out.println(Arrays.toString(obj.addNegabinary(new int[]{1, 0}, new int[]{1})));
        System.out.println();
    }

    public int[] addNegabinary(int[] arr1, int[] arr2) {
        LinkedList<Integer> list = new LinkedList<>();
        int m = arr1.length - 1;
        int n = arr2.length - 1;
        int carry = 0;
        while (m >= 0 || n >= 0 || carry != 0) {
            int sum = carry;
            if (m >= 0) {
                sum += arr1[m--];
            }
            if (n >= 0) {
                sum += arr2[n--];
            }
            list.addFirst(sum & 1);
            carry = -(sum >> 1);
        }
        while (list.size() > 1 && list.getFirst() == 0) {
            list.removeFirst();
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}