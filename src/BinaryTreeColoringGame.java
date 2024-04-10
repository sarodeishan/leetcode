package src;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class BinaryTreeColoringGame implements TreeTemplate {

    record TestCase(TreeNode node, int n, int x, boolean expectedOutput) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}), 11, 3, true),
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{1, 2, 3}), 3, 1, false),
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{1, 2, 3}), 3, 2, true),
                new TestCase(TreeTemplate.createBinaryTreeFromArray(new Integer[]{1, 2, 3, 4, 5}), 5, 2, false)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        BinaryTreeColoringGame obj = new BinaryTreeColoringGame();
        Instant start = Instant.now();
        var result = obj.btreeGameWinningMove(testCase.node(), testCase.n(), testCase.x());
        boolean success = (result == testCase.expectedOutput());
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

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        TreeNode blue = searchNode(root, x);
        int[] nodeCount = new int[3];
        nodeCount[0] = calculateTotalNode(blue.left);
        nodeCount[1] = calculateTotalNode(blue.right);
        nodeCount[2] = n - 1 - nodeCount[0] - nodeCount[1];
        Arrays.sort(nodeCount);
        return nodeCount[2] > n - nodeCount[2];
    }

    private TreeNode searchNode(TreeNode root, int x) {
        TreeNode node = null;
        if (root != null) {
            if (root.val == x) {
                node = root;
            }
            if (node == null && root.left != null) {
                node = searchNode(root.left, x);
            }
            if (node == null && root.right != null) {
                node = searchNode(root.right, x);
            }
        }
        return node;
    }

    private int calculateTotalNode(TreeNode root) {
        if (root != null) {
            return 1 + calculateTotalNode(root.left) + calculateTotalNode(root.right);
        }
        return 0;
    }
}
