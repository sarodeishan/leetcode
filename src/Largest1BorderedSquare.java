package src;

import java.time.Instant;

public class Largest1BorderedSquare {

    public static void main(String[] args) {
        int[][][] input = new int[][][]{
                new int[][]{
                        new int[]{1, 1, 1},
                        new int[]{1, 0, 1},
                        new int[]{1, 1, 1}
                },
                new int[][]{
                        new int[]{1, 1, 0, 0}
                },
                new int[][]{
                        new int[]{0}
                }
        };
        int[] output = new int[]{9, 1, 0};
        for (int i = 0; i < input.length; i++) {
            tester(i + 1, input[i], output[i]);
        }
    }

    private static void tester(int testNumber, int[][] input, int output) {
        Largest1BorderedSquare obj = new Largest1BorderedSquare();
        Instant start = Instant.now();
        int result = obj.largest1BorderedSquare(input);
        System.out.println(result);
        System.out.println(result == output);
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public int largest1BorderedSquare(int[][] grid) {
        int result = 0;
        for (int n = Math.min(grid.length, grid[0].length); n > 0; n--) {
            boolean found = false;
            for (int row = 0; row < grid.length - n + 1 && !found; row++) {
                for (int col = 0; col < grid[0].length - n + 1 && !found; col++) {
                    boolean validBorder = true;
                    //upper border check
                    for (int i = col; validBorder && i < col + n; i++) {
                        if (grid[row][i] == 0) {
                            validBorder = false;
                        }
                    }
                    for (int i = col; validBorder && i < col + n; i++) {
                        if (grid[row + n - 1][i] == 0) {
                            validBorder = false;
                        }
                    }
                    for (int i = row; validBorder && i < row + n; i++) {
                        if (grid[i][col] == 0) {
                            validBorder = false;
                        }
                    }
                    for (int i = row; validBorder && i < row + n; i++) {
                        if (grid[i][col + n - 1] == 0) {
                            validBorder = false;
                        }
                    }
                    if (validBorder) {
                        found = true;
                        break;
                    }
                }
            }
            if (found) {
                result = n * n;
                break;
            }
        }
        return result;
    }

}