package src;

import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CorpFlightBooking {

    public static void main(String[] args) {
        int[][][] input1 = new int[][][]{
                new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}},
                new int[][]{{1, 2, 10}, {2, 2, 15}}
        };
        int[] input2 = new int[]{
                5, 2
        };
        for (int i = 0; i < input1.length; i++) {
            tester(i + 1, input1[i], input2[i]);
        }
    }

    private static void tester(int testNumber, int[][] bookings, int n) {
        CorpFlightBooking obj = new CorpFlightBooking();
        Instant start = Instant.now();
        System.out.println(Arrays.toString(obj.corpFlightBookings(bookings, n)));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] output = new int[n];

        for (int[] booking : bookings) {
            int start = booking[0] - 1;
            int end = booking[1] - 1;
            int seats = booking[2];

            output[start] += seats;

            if (end + 1 < n) {
                output[end + 1] -= seats;
            }
        }

        // Adjust the values in the output array
        for (int i = 1; i < n; i++) {
            output[i] += output[i - 1];
        }

        return output;
    }

}