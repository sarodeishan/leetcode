package src;

import java.util.*;

public class EscapeALargeMaze {

    public static void main(String[] args) {
        EscapeALargeMaze test = new EscapeALargeMaze();
        System.out.println("Answer:" + test.isEscapePossible(new int[][]{{0, 1}, {1, 0}}, new int[]{0, 0}, new int[]{0, 2}));
        System.out.println();
        System.out.println("Answer:" + test.isEscapePossible(new int[][]{}, new int[]{0, 0}, new int[]{999999, 999999}));
        System.out.println();
    }

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        if (blocked.length == 0) {
            return true;
        }
        Set<String> blockedCells = new HashSet<>();
        Arrays.stream(blocked).map(this::key).forEach(blockedCells::add);
        return isEscapePossibleDFS(blockedCells, source, target);
    }

    private boolean isEscapePossibleDFS(Set<String> blockedCells, int[] source, int[] target) {
        if (source[0] < 0 || source[0] > 999999 || source[1] < 0 || source[1] > 999999) {
            return false;
        } else if (blockedCells.contains(key(source))) {
            return false;
        }
        if (source[0] == target[0] && source[1] == target[1]) {
            return true;
        }
        blockedCells.add(source[0] + "_" + source[1]);
        int[][] newPaths = new int[][]{{source[0] - 1, source[1]}, {source[0] + 1, source[1]}, {source[0], source[1] - 1}, {source[0], source[1] + 1}};
        Arrays.sort(newPaths, (o1, o2) -> {
            int x = Math.abs(o1[0] - source[0]) - Math.abs(o2[0] - source[0]);
            if (x == 0) {
                return Math.abs(o1[1] - source[1]) - Math.abs(o2[1] - source[1]);
            } else return x;
        });
        return Arrays.stream(newPaths).anyMatch(newPath -> isEscapePossibleDFS(blockedCells, newPath, target));
    }

    private boolean isEscapePossibleBFS(Set<String> blockedCells, int[] source, int[] target) {
        boolean result = false;
        Queue<int[]> cells = new ArrayDeque<>();
        cells.offer(source);
        while (!cells.isEmpty()) {
            int[] cell = cells.poll();
            if (cell[0] == target[0] && cell[1] == target[1]) {
                result = true;
                break;
            }
            blockedCells.add(key(cell));
            //up
            Optional.of(cell).filter(ints -> ints[0] > 0).map(ints -> new int[]{ints[0] - 1, ints[1]})
                    .filter(ints -> !blockedCells.contains(key(ints))).ifPresent(cells::offer);
            //down
            Optional.of(cell).filter(ints -> ints[0] < 999998).map(ints -> new int[]{ints[0] + 1, ints[1]})
                    .filter(ints -> !blockedCells.contains(key(ints))).ifPresent(cells::offer);
            //left
            Optional.of(cell).filter(ints -> ints[1] > 0).map(ints -> new int[]{ints[0], ints[1] - 1})
                    .filter(ints -> !blockedCells.contains(key(ints))).ifPresent(cells::offer);
            //right
            Optional.of(cell).filter(ints -> ints[1] < 999998).map(ints -> new int[]{ints[0], ints[1] + 1})
                    .filter(ints -> !blockedCells.contains(key(ints))).ifPresent(cells::offer);
        }
        blockedCells.clear();
        cells.clear();
        return result;
    }

    private String key(int[] arr) {
        return arr[0] + "_" + arr[1];
    }
}
