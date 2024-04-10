package src;

import java.time.Instant;

public class DecreaseElementsToMakeArrayZigZag {

    record TestCase(int[] input, int output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(new int[]{1, 2, 3}, 2),
                new TestCase(new int[]{9, 6, 1, 6, 2}, 4),
                new TestCase(new int[]{2, 7, 10, 9, 8, 9}, 4),
                new TestCase(new int[]{7, 4, 8, 9, 7, 7, 5}, 5)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        DecreaseElementsToMakeArrayZigZag obj = new DecreaseElementsToMakeArrayZigZag();
        Instant start = Instant.now();
        int result = obj.movesToMakeZigzag(testCase.input());
        boolean success = result == testCase.output();
        StringBuilder message = new StringBuilder();
        message.append("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns")
                .append(" ").append("Success:" + (success))
                .append(" ").append("Output:" + result);
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    public int movesToMakeZigzag(int[] nums) {
        int oddMoves = 0, evenMoves = 0;
        int prevMoves = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            oddMoves += i - 1 >= 0 ? nums[i - 1] - prevMoves >= nums[i] ? nums[i - 1] - prevMoves - nums[i] + 1 : 0 : 0;
            prevMoves = i + 1 < nums.length ? nums[i + 1] >= nums[i] ? nums[i + 1] - nums[i] + 1 : 0 : 0;
            oddMoves += prevMoves;
        }
        prevMoves = 0;
        for (int i = 1; i < nums.length; i = i + 2) {
            evenMoves += i - 1 >= 0 ? nums[i - 1] - prevMoves >= nums[i] ? nums[i - 1] - prevMoves - nums[i] + 1 : 0 : 0;
            prevMoves = i + 1 < nums.length ? nums[i + 1] >= nums[i] ? nums[i + 1] - nums[i] + 1 : 0 : 0;
            evenMoves += prevMoves;
        }
        return Math.min(oddMoves, evenMoves);
    }

}