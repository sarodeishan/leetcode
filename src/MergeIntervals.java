package src;

import java.util.*;

public class MergeIntervals {

    public static void main(String[] args) {
        MergeIntervals test = new MergeIntervals();
        System.out.println("Answer:" + Arrays.deepToString(test.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println();
        System.out.println("Answer:" + Arrays.deepToString(test.merge(new int[][]{{1, 4}, {4, 5}})));
        System.out.println();
        System.out.println("Answer:" + Arrays.deepToString(test.merge(new int[][]{{1, 4}, {2, 3}})));
        System.out.println();
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (int[] o1, int[] o2) -> {
            int delta = o1[0] - o2[0];
            if (delta == 0) {
                return o1[1] - o2[1];
            }
            return delta;
        });
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= merged.get(merged.size() - 1)[1]) {
                if (intervals[i][1] > merged.get(merged.size() - 1)[1]){
                    merged.get(merged.size() - 1)[1] = intervals[i][1];
                }
            } else {
                merged.add(intervals[i]);
            }
        }
        int[][] result = new int[merged.size()][2];
        for (int i = 0; i < result.length; i++) {
            result[i] = merged.get(i);
        }
        return result;
    }

}
