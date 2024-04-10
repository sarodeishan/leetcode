package src;

import java.util.Arrays;

public class GrumpyBookStoreOwner {

    public static void main(String[] args) {
        GrumpyBookStoreOwner test = new GrumpyBookStoreOwner();
        System.out.println("Answer:" + test.maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
        System.out.println();
        System.out.println("Answer:" + test.maxSatisfied(new int[]{1}, new int[]{0}, 1));
        System.out.println();
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int satisfied = 0;
        int maxNonSatisfied = 0;
        int total = grumpy.length;

        if (minutes >= grumpy.length) {
            return Arrays.stream(customers).sum();
        }

        boolean applySecret = false;
        for (int i = 0; i < total; i++) {
            if (grumpy[i] == 0) {
                satisfied += customers[i];
            } else {
                applySecret = true;
            }
        }
        int nonSatisfied = 0;
        for (int i = 0; i < minutes; i++) {
            if (grumpy[i] == 1) {
                nonSatisfied += customers[i];
            }
        }
        maxNonSatisfied = nonSatisfied;
        for (int i = 1; i <= total - minutes && applySecret; i++) {
            nonSatisfied = nonSatisfied - (grumpy[i - 1] == 0 ? 0 : customers[i - 1]) + (grumpy[i + minutes - 1] == 0 ? 0 : customers[i + minutes - 1]);
            maxNonSatisfied = Math.max(maxNonSatisfied, nonSatisfied);
        }
        return satisfied + maxNonSatisfied;
    }

}