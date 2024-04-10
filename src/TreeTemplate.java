package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public interface TreeTemplate {

    public static String printTree(TreeNode root) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return val == treeNode.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }

    public static TreeNode createBinaryTreeFromArray(Integer[] arr) {
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

}
