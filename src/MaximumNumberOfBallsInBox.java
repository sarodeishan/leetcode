package src;

import java.util.*;

public class MaximumNumberOfBallsInBox {

    private record Input(int lowLimit, int highLimit) {
    }

    ;

    public static void main(String[] args) {
        List<Input> inputs = new ArrayList<>();
        inputs.add(new Input(1, 10));
        inputs.add(new Input(5, 15));
        inputs.add(new Input(19, 28));
        for (int i = 0; i < inputs.size(); i++) {
            tester(i + 1, inputs.get(i));
        }
    }

    private static void tester(int num, Input input) {
        MaximumNumberOfBallsInBox test = new MaximumNumberOfBallsInBox();
        System.out.println("TestNum:" + num);
        System.out.println("Answer:" + test.countBalls(input.lowLimit(), input.highLimit()));
        System.out.println();
    }

    public int countBalls(int lowLimit, int highLimit) {
        int[] count = new int[46];
        Integer sum = null;
        for (; lowLimit <= highLimit; lowLimit++) {
            if (lowLimit % 10 == 0) {
                sum = null;
            }
            if (sum == null) {
                sum = 0;
                int i = lowLimit;
                while (i != 0) {
                    sum += i % 10;
                    i = i / 10;
                }
            }
            count[sum]++;
            lowLimit++;
            for (; lowLimit <= highLimit && lowLimit % 10 != 0; lowLimit++) {
                sum++;
                count[sum]++;
            }
            lowLimit--;
        }
        return Arrays.stream(count).max().orElse(0);
    }

}
