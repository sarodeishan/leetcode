package src;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class DuplicateZeros {

    public static void main(String[] args) {
        DuplicateZeros test = new DuplicateZeros();

        int[] input = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        test.duplicateZeros(input);
        System.out.println("Answer:" + Arrays.toString(input));
        System.out.println();

        input = new int[]{1, 2, 3};
        test.duplicateZeros(input);
        System.out.println("Answer:" + Arrays.toString(input));
        System.out.println();

        input = new int[]{0, 0, 0, 0, 0, 0, 0};
        test.duplicateZeros(input);
        System.out.println("Answer:" + Arrays.toString(input));
        System.out.println();

        input = new int[]{8, 4, 5, 0, 0, 0, 0, 7};
        test.duplicateZeros(input);
        System.out.println("Answer:" + Arrays.toString(input));
        System.out.println();
    }

    public void duplicateZeros(int[] arr) {
        int duplicateZero = 0;
        for (int i = 0; i < arr.length - duplicateZero - 1; i++) {
            if (arr[i] == 0) {
                if (i == arr.length - duplicateZero - 1) {
                    break;
                }
                duplicateZero++;
            }
        }
        for (int i = arr.length - 1; i >= 0 && duplicateZero > 0; i--) {
            arr[i] = arr[i - duplicateZero];
            if (arr[i - duplicateZero] == 0 && i - 1 >= 0) {
                arr[i - 1] = 0;
                i--;
                duplicateZero--;
            }
        }
    }

}
