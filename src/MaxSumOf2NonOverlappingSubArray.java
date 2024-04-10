package src;

import java.util.Arrays;

public class MaxSumOf2NonOverlappingSubArray {

    public static void main(String[] args) {
        MaxSumOf2NonOverlappingSubArray test = new MaxSumOf2NonOverlappingSubArray();
        System.out.println("Answer:"+test.maxSumTwoNoOverlap(new int[]{0,6,5,2,2,5,1,9,4}, 1, 2));
        System.out.println();
        System.out.println("Answer:"+test.maxSumTwoNoOverlap(new int[]{3,8,1,3,2,1,8,9,0}, 3, 2));
        System.out.println();
        System.out.println("Answer:"+test.maxSumTwoNoOverlap(new int[]{2,1,5,6,0,9,5,0,3,8}, 4, 3));
        System.out.println();
    }

    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        System.out.println("Nums:"+Arrays.toString(nums));
        int max = 0;
        if (firstLen + secondLen > nums.length) {
            return max;
        }

        //1. leftMax with firstLen range and rightMax with secondLen range
        //leftMax with firstLen range
        int[] leftMax = fillLeftMax(nums, firstLen);
        //rightMax with secondLen range
        int[] rightMax = fillRightMax(nums, secondLen);
        max = Math.max(max, findMax(leftMax, rightMax));

        //2. leftMax with firstLen range and rightMax with secondLen range
        //leftMax with secondLen range
        leftMax = fillLeftMax(nums, secondLen);
        //rightMax with firstLen range
        rightMax = fillRightMax(nums, firstLen);
        max = Math.max(max, findMax(leftMax, rightMax));

        return max;
    }

    private int findMax(int[] leftMax, int[] rightMax){
        int max = 0;
        for (int i = 0; i < leftMax.length - 1; i++) {
            if (leftMax[i] == -1 || rightMax[i + 1] == -1) {
                continue;
            }
            max = Math.max(max, leftMax[i] + rightMax[i + 1]);
        }
        System.out.println("Max:"+max);
        return max;
    }

    private int[] fillLeftMax(int[] nums, int range) {
        System.out.println("range:"+range);
        int[] leftMax = new int[nums.length];
        int leftSum = 0;
        for (int i = 0; range < nums.length && i < range; i++) {
            leftSum += nums[i];
        }
        int i = 0;
        for (; i < range - 1; i++) {
            leftMax[i] = -1;
        }
        for (; i < nums.length; i++) {
            if (i != (range - 1)) {
                leftSum = leftSum + nums[i] - nums[i - range];
            }
            if (i == 0) {
                leftMax[i] = leftSum;
            } else {
                leftMax[i] = Math.max(leftMax[i - 1], leftSum);
            }
        }
        System.out.println("LeftMax:"+ Arrays.toString(leftMax));
        return leftMax;
    }


    private int[] fillRightMax(int[] nums, int range) {
        System.out.println("range:"+range);
        int[] rightMax = new int[nums.length];
        int rightSum = 0;
        for (int i = nums.length - 1; i > (nums.length - 1 - range); i--) {
            rightSum += nums[i];
        }
        int i = nums.length - 1;
        for (; i > (nums.length - range); i--) {
            rightMax[i] = -1;
        }
        for (; i > -1; i--) {
            if (i != (nums.length - range)) {
                rightSum = rightSum + nums[i] - nums[i + range];
            }
            if (i == nums.length - 1) {
                rightMax[i] = rightSum;
            } else {
                rightMax[i] = Math.max(rightMax[i + 1], rightSum);
            }
        }
        System.out.println("RightMax:"+Arrays.toString(rightMax));
        return rightMax;
    }

}
