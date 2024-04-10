package src;

import java.time.Instant;
import java.util.Arrays;

public class DistributeCandiesToPeople {

    public static void main(String[] args) {
        int[] input1 = new int[]{
                7, 10
        };
        int[] input2 = new int[]{
                4, 3
        };
        for (int i = 0; i < input1.length; i++) {
            tester(i + 1, input1[i], input2[i]);
        }
    }

    private static void tester(int testNumber, int input1, int input2) {
        DistributeCandiesToPeople obj = new DistributeCandiesToPeople();
        Instant start = Instant.now();
        System.out.println(Arrays.toString(obj.distributeCandies(input1, input2)));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];
        int candy = 1;
        int person = 0;
        while (candies >= candy) {
            result[person % num_people] += candy;
            candies -= candy;
            person++;
            candy++;
        }
        result[person % num_people] += candies;
        return result;
    }

}