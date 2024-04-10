package src;

import java.util.Arrays;
import java.util.Comparator;

class VideoStitching {

    public static void main(String[] args) {
        VideoStitching test = new VideoStitching();
        System.out.println(test.videoStitching(new int[][]{
                new int[]{0, 2},
                new int[]{4, 6},
                new int[]{8, 10},
                new int[]{1, 9},
                new int[]{1, 5},
                new int[]{5, 9}
        }, 10));
        System.out.println(test.videoStitching(new int[][]{
                new int[]{0, 1},
                new int[]{1, 2}
        }, 5));
        System.out.println(test.videoStitching(new int[][]{
                new int[]{0, 2},
                new int[]{4, 8}
        }, 5));
    }

    public int videoStitching(int[][] clips, int time) {
        int result = 0;
        sortArr(clips);
        int prevStart = clips[0][0];
        int prevEnd = clips[0][1];
        if (prevStart != 0) {
            return -1;
        }
        result++;
        for (int i = 1; i < clips.length; i++) {
            if (clips[i][0] == prevStart) {
                continue;
            }
            if (clips[i][0] > prevEnd){
                return -1;
            }
            int maxRangeIndex = i;
            i++;
            while (i < clips.length && clips[i][0] <= prevEnd) {
                if (clips[maxRangeIndex][1] < clips[i][1]) {
                    maxRangeIndex = i;
                }
                i++;
            }
            i--;
            if (prevEnd > clips[maxRangeIndex][1]) {
                return -1;
            }
            prevStart = clips[maxRangeIndex][0];
            prevEnd = clips[maxRangeIndex][1];
            result++;
        }
        if (prevEnd < time) {
            return -1;
        }
        return result;
    }

    private void sortArr(int[][] clips) {
        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int res = o1[0] - o2[0];
                if (res == 0) {
                    return o2[1] - o1[1];
                }
                return res;
            }
        };
        Arrays.sort(clips, comp);
    }
}