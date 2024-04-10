package src;

import java.util.HashMap;
import java.util.Map;


public class RecoverATreeFromPreorderTraversal {

    /**
     * Definition for a binary tree node.
     */
    static class TreeNode {
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
    }

    public static void main(String[] args) {
        System.out.println(recoverFromPreorder("1-2--3--4-5--6--7"));
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        Map<Integer, TreeNode> depth = new HashMap<>();
        StringBuilder val = new StringBuilder();
        int i = 0;
        while (i < traversal.length() && Character.isDigit(traversal.charAt(i))) {
            val.append(traversal.charAt(i));
            i++;
        }
        TreeNode root = new TreeNode(Integer.valueOf(val.toString()));
        depth.put(0, root);

        for (; i < traversal.length(); ) {
            int dashCount = 0;
            while (traversal.charAt(i) == '-') {
                dashCount++;
                i++;
            }
            val = new StringBuilder();
            while (i < traversal.length() && Character.isDigit(traversal.charAt(i))) {
                val.append(traversal.charAt(i));
                i++;
            }
            System.out.println("DashCount:" + dashCount + " val:" + val.toString());
            TreeNode child = new TreeNode(Integer.valueOf(val.toString()));
            TreeNode parent = depth.get(dashCount - 1);
            if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            depth.put(dashCount, child);

        }
        return root;
    }
}