package src;

import java.util.*;

class LargestValuesFromLabels {

    public static void main(String[] args) {
        LargestValuesFromLabels obj = new LargestValuesFromLabels();
        System.out.println(obj.largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 1, 2, 2, 3}, 3, 1));
        System.out.println();
        System.out.println(obj.largestValsFromLabels(new int[]{5, 4, 3, 2, 1}, new int[]{1, 3, 3, 3, 2}, 3, 2));
        System.out.println();
        System.out.println(obj.largestValsFromLabels(new int[]{9, 8, 8, 7, 6}, new int[]{0, 0, 0, 1, 1}, 3, 1));
        System.out.println();
    }

    class Item {
        int label;
        int value;

        public Item(int label, int value) {
            this.label = label;
            this.value = value;
        }

        @Override
        public String toString() {
            return "L" + label + "=" + value;
        }

    }

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        PriorityQueue<Item> maxHeap = new PriorityQueue<>((a, b) -> b.value - a.value);
        for (int i = 0; i < values.length; i++) {
            maxHeap.offer(new Item(labels[i], values[i]));
        }
        Map<Integer, Integer> itemCount = new HashMap<>();
        int score = 0;
        while (!maxHeap.isEmpty() && numWanted > 0) {
            Item item = maxHeap.poll();
            if (itemCount.getOrDefault(item.label, 0) < useLimit) {
                score += item.value;
                itemCount.merge(item.label, 1, Integer::sum);
                numWanted--;
            }
        }
        return score;
    }

}