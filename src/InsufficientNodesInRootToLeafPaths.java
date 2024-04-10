package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InsufficientNodesInRootToLeafPaths {

    public static void main(String[] args) {
        InsufficientNodesInRootToLeafPaths test = new InsufficientNodesInRootToLeafPaths();
        System.out.println("Answer:" + printTree(test.sufficientSubset(
                createBinaryTreeFromArray(new Integer[]{1, 2, 3, 4, -99, -99, 7, 8, 9, -99, -99, 12, 13, -99, 14}), 1))
        );
        System.out.println();
        System.out.println("Answer:" + printTree(test.sufficientSubset(
                createBinaryTreeFromArray(new Integer[]{5, 4, 8, 11, null, 17, 4, 7, 1, null, null, 5, 3}), 22))
        );
        System.out.println();
        System.out.println("Answer:" + printTree(test.sufficientSubset(
                createBinaryTreeFromArray(new Integer[]{1, 2, -3, -5, null, 4, null}), -1))
        );
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

    public TreeNode sufficientSubset(TreeNode root, int limit) {
        boolean remove = removeNode(root, limit, 0);
        if (remove) {
            root = null;
        }
        return root;
    }

    private boolean removeNode(TreeNode root, final int limit, int sum) {
        if (root == null) {
            return true;
        } else if (root.left == null && root.right == null) {
            return sum + root.val < limit;
        }
        boolean removeLeft = removeNode(root.left, limit, sum + root.val);
        if (removeLeft) {
            root.left = null;
        }
        boolean removeRight = removeNode(root.right, limit, sum + root.val);
        if (removeRight) {
            root.right = null;
        }
        //leaf
        return removeLeft && removeRight;
    }

}
