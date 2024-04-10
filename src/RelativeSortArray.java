package src;

import java.time.Instant;
import java.util.*;

public class RelativeSortArray {

    public static void main(String[] args) {
        int[][] input = new int[][]{
                new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19},
                new int[]{28, 6, 22, 8, 44, 17}
        };
        int[][] input2 = new int[][]{
                new int[]{2, 1, 4, 3, 9, 6},
                new int[]{22, 28, 8, 6}
        };
        for (int i = 0; i < input.length; i++) {
            tester(i + 1, input[i], input2[i]);
        }
    }

    private static void tester(int testNumber, int[] input1, int[] input2) {
        RelativeSortArray obj = new RelativeSortArray();
        Instant start = Instant.now();
        System.out.println(Arrays.toString(obj.relativeSortArray(input1, input2)));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int sortIndex = 0;
        for (int num: arr2) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i]==num){
                    arr1[i] = arr1[sortIndex];
                    arr1[sortIndex] = num;
                    sortIndex++;
                }
            }
        }
        Arrays.sort(arr1, sortIndex, arr1.length);
        return arr1;
    }

    //using map
    /*public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        return Arrays.stream(arr1).boxed()
                .sorted((Integer o1, Integer o2) -> {
                    if (map.containsKey(o1) || map.containsKey(o2)) {
                        return map.getOrDefault(o1, Integer.MAX_VALUE) - map.getOrDefault(o2, Integer.MAX_VALUE);
                    }
                    return o1 - o2;
                })
                .mapToInt(Integer::intValue).toArray();
    }*/

}