package src;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

public class CarPooling {

    public static void main(String[] args) {
        int[][][] input1 = new int[][][]{
                new int[][]{{2, 1, 5}, {3, 3, 7}},
                new int[][]{{2, 1, 5}, {3, 3, 7}},
                new int[][]{{9, 3, 4}, {9, 1, 7}, {4, 2, 4}, {7, 4, 5}}
        };
        int[] input2 = new int[]{
                4, 5, 23
        };
        for (int i = 0; i < input1.length; i++) {
            tester(i + 1, input1[i], input2[i]);
        }
    }

    private static void tester(int testNumber, int[][] input1, int input2) {
        CarPooling obj = new CarPooling();
        Instant start = Instant.now();
        System.out.println(obj.carPooling(input1, input2));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public boolean carPooling(int[][] trips, int capacity) {
        boolean result = true;
        int[] locations = new int[1001];
        for (int[] trip : trips) {
            if (trip[0] > capacity) {
                return false;
            }
            //add customer from to location
            locations[trip[1]] += trip[0];
            //deduct customer from to location
            locations[trip[2]] -= trip[0];
        }
        int carCapacity = 0;
        for (int i = 0; i < locations.length; i++) {
            carCapacity += locations[i];
            if (carCapacity > capacity) {
                result = false;
                break;
            }
        }
        return result;
    }
}