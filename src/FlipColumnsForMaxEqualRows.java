package src;

import java.util.Arrays;

public class FlipColumnsForMaxEqualRows {

    public static void main(String[] args) {
        FlipColumnsForMaxEqualRows test = new FlipColumnsForMaxEqualRows();
        System.out.println("Answer:" + test.maxEqualRowsAfterFlips(new int[][]{new int[]{0, 1}, new int[]{1, 1}}));
        System.out.println();
        System.out.println("Answer:" + test.maxEqualRowsAfterFlips(new int[][]{new int[]{0, 1}, new int[]{1, 0}}));
        System.out.println();
        System.out.println("Answer:" + test.maxEqualRowsAfterFlips(new int[][]{new int[]{0, 0, 0}, new int[]{0, 0, 1}, new int[]{1, 1, 0}}));
        System.out.println();
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] equalRow = new int[m];
        for (int col = 0; col < n - 1; col++) {
            long threshold = calculateThreshold(equalRow);
            int delta = 0;
            for (int row = 0; row < m; row++) {
                if (equalRow[row] == 0 && matrix[row][col] != matrix[row][col + 1]) {
                    delta++;
                }
            }
            if (delta > threshold) {
                flipColumn(matrix, col + 1);
            }
            for (int row = 0; row < m; row++) {
                if (equalRow[row] == 0) {
                    equalRow[row] = matrix[row][col] ^ matrix[row][col + 1];
                }
            }
        }
        return (int) Arrays.stream(equalRow).filter(value -> value == 0).count();
    }

    private long calculateThreshold(int[] arr) {
        return Arrays.stream(arr).filter(value -> value == 0).count() / 2;
    }

    private void flipColumn(int[][] matrix, int col) {
        for (int row = 0; row < matrix.length; row++) {
            matrix[row][col]++;
            matrix[row][col] = matrix[row][col] % 2;
        }
    }

}
