package src;

import java.util.Arrays;

public class StatisticsFromALargeSample {

    public static void main(String[] args) {
        StatisticsFromALargeSample test = new StatisticsFromALargeSample();

        int[] input = new int[]{0, 1, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        double[] output = test.sampleStats(input);
        System.out.println("Answer:" + Arrays.toString(output));
        System.out.println();

        input = new int[]{0, 4, 3, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        output = test.sampleStats(input);
        System.out.println("Answer:" + Arrays.toString(output));
        System.out.println();

    }

    public double[] sampleStats(int[] count) {
        double[] stats = new double[5];
        Arrays.fill(stats, -1);
        stats[0] = -1;
        stats[1] = -1;
        stats[2] = 0;
        stats[3] = 0;
        stats[4] = 0;
        int maxOccurrenceIndex = 0;
        long sum = 0;
        long totalElement = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                //mean
                totalElement += count[i];
                sum += ((long) i * count[i]);
                //min
                if (stats[0] == -1) {
                    stats[0] = (double) i;
                }
                //max
                stats[1] = (double) i;
                //mode
                if (count[maxOccurrenceIndex] < count[i]) {
                    maxOccurrenceIndex = i;
                    stats[4] = (double) i;
                }
            }
        }
        //mean
        if (totalElement > 0) {
            stats[2] = (double) sum / (double) totalElement;
        }
        //median
        if (totalElement % 2 == 0) {
            long medianIndex = (totalElement / 2);
            int total = 0;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    if (count[i] >= medianIndex + 1) {
                        total = i * 2;
                        break;
                    } else if (count[i] >= medianIndex) {
                        for (int j = i + 1; j < count.length; j++) {
                            if (count[j] > 0) {
                                total = i + j;
                                break;
                            }
                        }
                        break;
                    } else {
                        medianIndex = medianIndex - count[i];
                    }
                }
            }
            stats[3] = (double) total / 2;
        } else {
            long medianIndex = (totalElement / 2) + 1;
            for (int i = 0; i < count.length; i++) {
                if (count[i] > 0) {
                    if (count[i] >= medianIndex) {
                        stats[3] = i;
                        break;
                    } else {
                        medianIndex = medianIndex - count[i];
                    }
                }
            }
        }
        return stats;
    }

}
