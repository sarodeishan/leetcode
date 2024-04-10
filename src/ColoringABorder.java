package src;

import java.util.Arrays;

public class ColoringABorder {

    public static void main(String[] args) {
        ColoringABorder test = new ColoringABorder();
        System.out.println("Answer:");
        Arrays.stream(test.colorBorder(new int[][]{{1, 1}, {1, 2}}, 0, 0, 3)).map(Arrays::toString).forEach(System.out::println);
        System.out.println();

        System.out.println("Answer:");
        Arrays.stream(test.colorBorder(new int[][]{{1, 2, 2}, {2, 3, 2}}, 0, 1, 3)).map(Arrays::toString).forEach(System.out::println);
        System.out.println();

        System.out.println("Answer:");
        Arrays.stream(test.colorBorder(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 1, 1, 2)).map(Arrays::toString).forEach(System.out::println);
        System.out.println();

        System.out.println("Answer:");
        Arrays.stream(test.colorBorder(new int[][]{{1, 2, 1, 2, 1, 2}, {2, 2, 2, 2, 1, 2}, {1, 2, 2, 2, 1, 2}}, 1, 3, 1)).map(Arrays::toString).forEach(System.out::println);
        System.out.println();
    }

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int originalColor = grid[row][col];
        boolean[][] result = new boolean[grid.length][grid[0].length];
        dfsColorBorder(grid, row, col, color, originalColor, result);
        return grid;
    }

    private void dfsColorBorder(int[][] grid, int row, int col, int newColor, int originalColor, boolean[][] visited) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
        } else if (grid[row][col] != originalColor) {
        } else if (visited[row][col]) {
        } else {
            visited[row][col] = true;
            //1. check if color the square
            boolean color = gridOrComponentBorder(grid, row, col, originalColor);
            //2. dfs on next adjacent
            dfsColorBorder(grid, row - 1, col, newColor, originalColor, visited);
            dfsColorBorder(grid, row + 1, col, newColor, originalColor, visited);
            dfsColorBorder(grid, row, col - 1, newColor, originalColor, visited);
            dfsColorBorder(grid, row, col + 1, newColor, originalColor, visited);
            //3. color
            if (color) {
                grid[row][col] = newColor;
            }
        }
    }

    private boolean gridOrComponentBorder(int[][] grid, int row, int col, int color) {
        return gridBorder(grid, row, col) || componentBorder(grid, row, col, color);
    }

    private boolean componentBorder(int[][] grid, int row, int col, int color) {
        return grid[row][col + 1] != color || grid[row][col - 1] != color || grid[row + 1][col] != color || grid[row - 1][col] != color;
    }

    private boolean gridBorder(int[][] grid, int row, int col) {
        return row == 0 || col == 0 || row == grid.length - 1 || col == grid[0].length - 1;
    }

}
