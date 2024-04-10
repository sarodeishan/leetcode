package src;

import java.util.*;

public class LastStoneWeight {

    public static void main(String[] args) {
        LastStoneWeight test = new LastStoneWeight();
        System.out.println("Answer:" + test.lastStoneWeight(new int[]{2, 7, 4, 1, 8, 1}));
        System.out.println();
        System.out.println("Answer:" + test.lastStoneWeight(new int[]{1}));
        System.out.println();
        System.out.println("Answer:" + test.lastStoneWeight(new int[]{8,8}));
        System.out.println();
    }

    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        Arrays.stream(stones).forEach(queue::offer);
        while (queue.size() > 1) {
            int y = queue.poll();
            int x = queue.poll();
            if (x != y) {
                queue.offer(y - x);
            }
        }
        int lastStoneWeight = 0;
        if (!queue.isEmpty()) {
            lastStoneWeight = queue.poll();
        }
        return lastStoneWeight;
    }

}
