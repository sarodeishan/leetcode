package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class PreviousPermutationWithOneSwap {

    public static void main(String[] args) {
        PreviousPermutationWithOneSwap test = new PreviousPermutationWithOneSwap();
        System.out.println("Answer:" + Arrays.toString(test.prevPermOpt1(new int[]{3, 2, 1})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.prevPermOpt1(new int[]{1, 1, 5})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.prevPermOpt1(new int[]{1, 9, 4, 6, 7})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.prevPermOpt1(new int[]{3, 1, 1, 3})));
        System.out.println();
    }

    public int[] prevPermOpt1(int[] arr) {
        boolean possible = false;
        int swapIndex1 = arr.length - 1;
        for (int i = arr.length - 1; i > 0; i--) {
            if (arr[i] < arr[i - 1]) {
                swapIndex1 = i - 1;
                possible = true;
                break;
            }
        }
        if (possible) {
            int swapIndex2 = arr.length - 1;
            for (int i = swapIndex1 + 1; i < arr.length; i++) {
                if (arr[i] == arr[i-1]){
                    continue;
                } else if (arr[swapIndex1] > arr[i]) {
                    swapIndex2 = i;
                } else {
                    break;
                }
            }
            int temp = arr[swapIndex1];
            arr[swapIndex1] = arr[swapIndex2];
            arr[swapIndex2] = temp;
        }
        return arr;
    }

}
