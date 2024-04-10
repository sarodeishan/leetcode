package src;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class DeleteNodesAndReturnForests {

    public static void main(String[] args) {
        Integer[][] input1 = new Integer[][]{
                new Integer[]{1, 2, 3, 4, 5, 6, 7},
                new Integer[]{1, 2, 4, null, 3},
                new Integer[]{1, 2, 3, null, null, null, 4}
        };
        int[][] input2 = new int[][]{
                new int[]{3, 5},
                new int[]{3},
                new int[]{2, 1}
        };
        for (int i = 0; i < input1.length; i++) {
            tester(i + 1, input1[i], input2[i]);
        }
    }

    private static void tester(int testNumber, Integer[] input1, int[] input2) {
        DeleteNodesAndReturnForests obj = new DeleteNodesAndReturnForests();
        Instant start = Instant.now();
        obj.delNodes(createBinaryTreeFromArray(input1), input2).stream().map(DeleteNodesAndReturnForests::printTree).forEach(System.out::println);
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    private static String printTree(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        List<Integer> result = new ArrayList<>();
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.removeFirst();
            if (node != null) {
                result.add(node.val);
                if (node.left != null || node.right != null) {
                    nodes.addLast(node.left);
                    nodes.addLast(node.right);
                }
            } else {
                result.add(null);
            }
        }
        return result.toString();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" + val + "}";
        }
    }

    private static TreeNode createBinaryTreeFromArray(Integer[] arr) {
        TreeNode root = new TreeNode(arr[0]);
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == null) {
                if (i % 2 == 0) {
                    nodes.removeFirst();
                }
                continue;
            }
            TreeNode node = new TreeNode(arr[i]);
            if (i % 2 == 1) {
                nodes.getFirst().left = node;
            } else {
                nodes.getFirst().right = node;
                nodes.removeFirst();
            }
            nodes.addLast(node);
        }
        return root;
    }

    List<TreeNode> result = new LinkedList<>();

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        int[] toDelete = new int[1001];
        for (int n : to_delete) {
            toDelete[n] = 1;
        }
        postOrder(root, toDelete);
        if (toDelete[root.val] == 1) {
            addToResult(root);
        } else {
            result.add(root);
        }
        return result;
    }

    private void postOrder(TreeNode root, int[] toDelete) {
        if (Objects.nonNull(root)) {
            postOrder(root.left, toDelete);
            if (Objects.nonNull(root.left) && toDelete[root.left.val] == 1) {
                addToResult(root.left);
                root.left = null;
            }
            postOrder(root.right, toDelete);
            if (Objects.nonNull(root.right) && toDelete[root.right.val] == 1) {
                addToResult(root.right);
                root.right = null;
            }
        }
    }

    private void addToResult(TreeNode node) {
        if (Objects.nonNull(node)) {
            if (Objects.nonNull(node.left)) {
                result.add(node.left);
            }
            if (Objects.nonNull(node.right)) {
                result.add(node.right);
            }
        }
    }

}