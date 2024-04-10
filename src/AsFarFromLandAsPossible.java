package src;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Queue;

public class AsFarFromLandAsPossible implements TreeTemplate {

    record TestCase(int[][] grid, int output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(new int[][]{
                        new int[]{1, 0, 1},
                        new int[]{0, 0, 0},
                        new int[]{0, 0, 1},
                }, 2),
                new TestCase(new int[][]{
                        new int[]{1, 0, 0},
                        new int[]{0, 0, 0},
                        new int[]{0, 0, 0},
                }, 4)
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        AsFarFromLandAsPossible obj = new AsFarFromLandAsPossible();
        Instant start = Instant.now();
        var result = obj.maxDistance(testCase.grid());
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

    record Cell(int x, int y) {
    }

    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<Cell> cells = new ArrayDeque<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 1) {
                    cells.offer(new Cell(row, col));
                }
            }
        }
        int level = 0;
        if (cells.isEmpty() || cells.size() == n * n) {
            return -1;
        }
        while (!cells.isEmpty()) {
            int m = cells.size();
            for (int i = 0; i < m; i++) {
                Cell cell = cells.poll();
                if (cell.y() + 1 < n && grid[cell.x()][cell.y() + 1] == 0) {
                    grid[cell.x()][cell.y() + 1] = 1;
                    cells.offer(new Cell(cell.x(), cell.y() + 1));
                }
                if (cell.y() - 1 >= 0 && grid[cell.x()][cell.y() - 1] == 0) {
                    grid[cell.x()][cell.y() - 1] = 1;
                    cells.offer(new Cell(cell.x(), cell.y() - 1));
                }
                if (cell.x() + 1 < n && grid[cell.x() + 1][cell.y()] == 0) {
                    grid[cell.x() + 1][cell.y()] = 1;
                    cells.offer(new Cell(cell.x() + 1, cell.y()));
                }
                if (cell.x() - 1 >= 0 && grid[cell.x() - 1][cell.y()] == 0) {
                    grid[cell.x() - 1][cell.y()] = 1;
                    cells.offer(new Cell(cell.x() - 1, cell.y()));
                }
            }
            level++;
        }
        return level-1;
    }

}