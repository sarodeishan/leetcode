package src;

import java.util.*;

public class DistantBarcodes {

    public static void main(String[] args) {
        DistantBarcodes test = new DistantBarcodes();
        System.out.println("Answer:" + Arrays.toString(test.rearrangeBarcodes(new int[]{1, 1, 1, 2, 2, 2})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.rearrangeBarcodes(new int[]{1, 1, 1, 1, 2, 2, 3, 3})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.rearrangeBarcodes(new int[]{1, 1, 2, 3, 3})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.rearrangeBarcodes(new int[]{1, 1, 3, 3, 3})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.rearrangeBarcodes(new int[]{1})));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.rearrangeBarcodes(new int[]{3, 7, 3, 7, 7, 7, 7, 2, 2, 2})));
        System.out.println();
    }

    class Barcode implements Comparable<Barcode> {

        int number;
        int count;

        @Override
        public int compareTo(Barcode o) {
            int diff = o.count - this.count;
            if (diff == 0) {
                return this.number - o.number;
            }
            return diff;
        }

        public int getCount() {
            return count;
        }

        public int decrementCount() {
            count--;
            return number;
        }

        public Barcode(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        if (barcodes.length == 0 || barcodes.length == 1) {
            return barcodes;
        }
        int[] result = new int[barcodes.length];
        Map<Integer, Integer> barcodeCounts = new HashMap<>();
        for (int num : barcodes) {
            barcodeCounts.put(num, barcodeCounts.getOrDefault(num, 0) + 1);
        }

        Queue<Barcode> queue = new PriorityQueue<>();
        for (Map.Entry<Integer, Integer> entry : barcodeCounts.entrySet()) {
            queue.offer(new Barcode(entry.getKey(), entry.getValue()));
        }

        int i = 0;
        while (queue.size() > 1) {
            Barcode first = queue.poll();
            result[i++] = first.decrementCount();
            Barcode second = queue.poll();
            result[i++] = second.decrementCount();
            if (second.getCount() != 0) {
                queue.offer(second);
            }
            if (first.getCount() != 0) {
                queue.offer(first);
            }
        }
        if (!queue.isEmpty()) {
            result[i] = queue.poll().decrementCount();
        }
        return result;
    }
}
