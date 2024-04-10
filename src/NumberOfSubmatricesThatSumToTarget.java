package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesThatSumToTarget {

    public static void main(String[] args) {
        NumberOfSubmatricesThatSumToTarget test = new NumberOfSubmatricesThatSumToTarget();
        System.out.println("Answer:" + test.numSubmatrixSumTarget(new int[][]{new int[]{0, 1, 0}, new int[]{1, 1, 1}, new int[]{0, 1, 0}}, 0));
        System.out.println();
        System.out.println("Answer:" + test.numSubmatrixSumTarget(new int[][]{new int[]{1, -1}, new int[]{-1, 1}}, 0));
        System.out.println();
        System.out.println("Answer:" + test.numSubmatrixSumTarget(new int[][]{new int[]{904}}, 0));
        System.out.println();
    }

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        Map<String, Integer> map = new HashMap<>();
        int answer = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                map.put(i + "_" + j + "_" + i + "_" + j, matrix[i][j]);
            }
        }
        return answer;
    }

}
