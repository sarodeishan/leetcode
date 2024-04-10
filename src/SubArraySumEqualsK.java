package src;

public class SubArraySumEqualsK {

    public static void main(String[] args) {
        SubArraySumEqualsK test = new SubArraySumEqualsK();
        System.out.println("Answer:" + test.subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println();
        System.out.println("Answer:" + test.subarraySum(new int[]{1, 2, 3}, 3));
        System.out.println();
    }

    public int subarraySum(int[] nums, int k) {
        int[] prefSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefSum[i + 1] = prefSum[i] + nums[i];
        }
        int answer = 0;
        for (int start = 0; start < prefSum.length; start++) {
            for (int end = start + 1; end < prefSum.length; end++) {
                if (prefSum[end] - prefSum[start] == k) {
                    answer++;
                }
            }
        }
        return answer;
    }

}
