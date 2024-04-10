package src;

import java.time.Instant;
import java.util.LinkedList;

public class MaximumLevelSumOfABinaryTree implements TreeTemplate {

    record TestCase(TreeNode root, int output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{1, 7, 0, 7, -8, null, null}), 2),
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{989, null, 10250, 98693, -89388, null, null, null, -32127}), 2),
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{-100, -200, -300, -20, -5, -10, null}), 3)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        MaximumLevelSumOfABinaryTree obj = new MaximumLevelSumOfABinaryTree();
        Instant start = Instant.now();
        var result = obj.maxLevelSum(testCase.root());
        boolean success = (result == testCase.output());
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

    public int maxLevelSum(TreeNode root) {
        LinkedList<TreeNode> currLevel = new LinkedList<>();
        currLevel.add(root);
        int level = 1, maxLevel = 1, maxSum = Integer.MIN_VALUE;
        while (!currLevel.isEmpty()) {
            LinkedList<TreeNode> nextLevel = new LinkedList<>();
            int sum = 0;

            for (TreeNode node : currLevel) {
                sum += node.val;
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }

            if (sum > maxSum) {
                maxSum = sum;
                maxLevel = level;
            }

            currLevel = nextLevel;
            level++;
        }
        return maxLevel;
    }

}
