package src;

import java.util.*;
import java.util.stream.Collectors;

public class LowestCommonAncestorOfDeepestLeaves implements TreeTemplate {

    public static void main(String[] args) {
        LowestCommonAncestorOfDeepestLeaves test = new LowestCommonAncestorOfDeepestLeaves();
        System.out.println("Answer:" + TreeTemplate.printTree(test.lcaDeepestLeaves(
                TreeTemplate.createBinaryTreeFromArray(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4})))
        );
        System.out.println();

        System.out.println("Answer:" + TreeTemplate.printTree(test.lcaDeepestLeaves(
                TreeTemplate.createBinaryTreeFromArray(new Integer[]{1})))
        );
        System.out.println();

        System.out.println("Answer:" + TreeTemplate.printTree(test.lcaDeepestLeaves(
                TreeTemplate.createBinaryTreeFromArray(new Integer[]{0, 1, 3, null, 2})))
        );
        System.out.println();
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        Map<Integer, List<TreeNode>> depthMap = new HashMap<>();
        calculateDepthAndParent(parentMap, depthMap, null, root, 0);
        List<TreeNode> deepestLeaves = depthMap.entrySet().stream().max(Map.Entry.comparingByKey()).map(Map.Entry::getValue).orElse(null);
        if (Objects.isNull(deepestLeaves)) {
            return root;
        } else if (deepestLeaves.size() == 1) {
            return deepestLeaves.get(0);
        } else {
            List<LinkedList<TreeNode>> lineages = deepestLeaves.stream().map(node -> findLineage(parentMap, node)).collect(Collectors.toList());
            TreeNode lca = root;
            for (int i = 0; i < lineages.get(0).size(); i++) {
                final int depth = i;
                List<TreeNode> ancestor = lineages.stream().map(treeNodes -> treeNodes.get(depth)).distinct().collect(Collectors.toList());
                if (ancestor.size() == 1) {
                    lca = ancestor.get(0);
                }
            }
            return lca;
        }

    }

    private LinkedList<TreeNode> findLineage(Map<TreeNode, TreeNode> parentMap, TreeNode node) {
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(node);
        while (Objects.nonNull(parentMap.get(node))) {
            TreeNode parent = parentMap.get(node);
            list.addFirst(parent);
            node = parent;
        }
        return list;
    }

    private void calculateDepthAndParent(Map<TreeNode, TreeNode> parentMap, Map<Integer, List<TreeNode>> depthMap, TreeNode parent, TreeNode root, int depth) {
        if (Objects.nonNull(root)) {
            //add depth of node
            depthMap.merge(depth, new ArrayList<>(Collections.singletonList(root)), (treeNodes, treeNodes2) -> {
                treeNodes.addAll(treeNodes2);
                return treeNodes;
            });
            parentMap.put(root, parent);
            calculateDepthAndParent(parentMap, depthMap, root, root.left, depth + 1);
            calculateDepthAndParent(parentMap, depthMap, root, root.right, depth + 1);
        }
    }

}
