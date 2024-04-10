package src;

import java.time.Instant;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PathInZigZagLabelledBinaryTree {

    public static void main(String[] args) {
        int[] input1 = new int[]{
               16, 14, 26
        };
        for (int i = 0; i < input1.length; i++) {
            tester(i + 1, input1[i]);
        }
    }

    private static void tester(int testNumber, int input) {
        PathInZigZagLabelledBinaryTree obj = new PathInZigZagLabelledBinaryTree();
        Instant start = Instant.now();
        System.out.println(obj.pathInZigZagTree(input));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public List<Integer> pathInZigZagTree(int label) {
        List<Integer> result = new LinkedList<>();
        LinkedList<Integer> binaryTree = new LinkedList<>();
        int element = 1;
        binaryTree.add(element++);
        boolean reverse = true;
        int i = 1;
        while (element <= label) {
            int num = (int) Math.pow(2, i++);
            Stream<Integer> stream = IntStream.range(element, element + num).boxed();
            if (reverse) {
                stream = stream.sorted(Comparator.reverseOrder());
            } else {
                stream = stream.sorted();
            }
            stream.forEach(binaryTree::add);
            reverse = !reverse;
            element += num;
        }

        for (i = 0;  i < binaryTree.size(); i++) {
            if (binaryTree.get(i)==label){
                break;
            }
        }
        while (i >= 0) {
            result.add(0, binaryTree.get(i));
            i = i / 2 - (i + 1) % 2;
        }
        return result;
    }
}