package src;

import java.time.Instant;
import java.util.*;

public class ShortestPathInBinaryMatrix {

    public static void main(String[] args) {
        int[][][] input = new int[][][]{
                new int[][]{new int[]{0, 1}, new int[]{1, 0}},
                new int[][]{new int[]{0, 0, 0}, new int[]{1, 1, 0}, new int[]{1, 1, 0}},
                new int[][]{new int[]{1, 0, 0}, new int[]{1, 1, 0}, new int[]{1, 1, 0}},
                new int[][]{new int[]{0, 0, 1, 0}, new int[]{1, 0, 1, 0}, new int[]{1, 1, 0, 1}, new int[]{0, 0, 0, 0}}
        };
        for (int i = 0; i < input.length; i++) {
            tester(i + 1, input[i]);
        }
    }

    private static void tester(int testNumber, int[][] input) {
        ShortestPathInBinaryMatrix obj = new ShortestPathInBinaryMatrix();
        Instant start = Instant.now();
        System.out.println(obj.shortestPathBinaryMatrix(input));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    enum Directions {
        UP(-1, 0), DOWN(1, 0), RIGHT(0, 1), LEFT(0, -1),
        UP_RIGHT(-1, 1), DOWN_RIGHT(1, 1), UP_LEFT(-1, -1), DOWN_LEFT(1, -1);

        int row;
        int col;

        Directions(int row, int col) {
            this.row = row;
            this.col = col;
        }

    }

    class Cell {

        int row;
        int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return row == cell.row && col == cell.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0] == 1) {
            return -1;
        }
        for (int[] row : grid) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] == 1) {
                    row[i] = -1;
                } else {
                    row[i] = Integer.MAX_VALUE;
                }
            }
        }
        grid[0][0] = 1;
        Queue<Cell> cells = new ArrayDeque<>();
        cells.offer(new Cell(0, 0));
        while (!cells.isEmpty()) {
            Cell cell = cells.poll();
            for (Directions direction : Directions.values()) {
                int x = cell.row + direction.row;
                int y = cell.col + direction.col;
                if (x >= 0 && x < grid.length && y >= 0 && y < grid.length && grid[x][y] != -1 && grid[x][y] > grid[cell.row][cell.col] + 1) {
                    grid[x][y] = grid[cell.row][cell.col] + 1;
                    cells.offer(new Cell(x, y));
                }
            }
        }
        return grid[grid.length - 1][grid.length - 1] == Integer.MAX_VALUE ? -1 : grid[grid.length - 1][grid.length - 1];
    }

}