package src;

public class LongestWellPerformingInterval {

    public static void main(String[] args) {
        LongestWellPerformingInterval test = new LongestWellPerformingInterval();
        System.out.println("Answer:" + test.longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9}));
        System.out.println();
        System.out.println("Answer:" + test.longestWPI(new int[]{6, 6, 6}));
        System.out.println();
        System.out.println("Answer:" + test.longestWPI(new int[]{9, 6, 0, 6, 6, 9, 9}));
        System.out.println();
        System.out.println("Answer:" + test.longestWPI(new int[]{6, 6, 9}));
        System.out.println();
        System.out.println("Answer:" + test.longestWPI(new int[]{6, 9, 6}));
        System.out.println();
    }

    public int longestWPI(int[] hours) {
        int result = 0;
        int start = 0;
        int interval = 0;
        for (int end = 0; end < hours.length; end++) {
            if (hours[end] > 8) {
                interval++;
            } else {
                interval--;
            }
            if (interval > 0) {
                result = Math.max(result, end - start + 1);
            } else if (interval < 0) {
                while (start < end && interval < 0) {
                    if (hours[start] > 8) {
                        interval--;
                    } else {
                        interval++;
                    }
                    start++;
                }
                if (interval > 0) {
                    result = Math.max(result, end - start + 1);
                }
            }
        }
        while (start < hours.length && hours[start] <= 8) {
            start++;
            interval++;
            result = Math.max(result, hours.length - start);
        }
        return result;
    }

}
