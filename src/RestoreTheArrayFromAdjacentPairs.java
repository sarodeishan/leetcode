package src;

import java.util.*;

public class RestoreTheArrayFromAdjacentPairs {

    private record Input(int[][] adjacentPairs) {
    }

    public static void main(String[] args) {
        List<Input> inputs = new ArrayList<>();
        inputs.add(new Input(new int[][]{{2, 1}, {3, 4}, {3, 2}}));
        inputs.add(new Input(new int[][]{{4, -2}, {1, 4}, {-3, 1}}));
        inputs.add(new Input(new int[][]{{100000, -100000}}));
        for (int i = 0; i < inputs.size(); i++) {
            tester(i + 1, inputs.get(i));
        }
    }

    private static void tester(int num, Input input) {
        RestoreTheArrayFromAdjacentPairs test = new RestoreTheArrayFromAdjacentPairs();
        System.out.println("TestNum:" + num);
        System.out.println("Answer:" + Arrays.toString(test.restoreArray(input.adjacentPairs())));
        System.out.println();
    }

    public int[] restoreArray(int[][] adjacentPairs) {
        //brute force
        Queue<int[]> queue = new ArrayDeque<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(adjacentPairs[0][0]);
        list.add(adjacentPairs[0][1]);
        for (int i = 1; i < adjacentPairs.length; i++) {
            queue.offer(adjacentPairs[i]);
        }
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            //matches with one of the lists end
            if (pair[0] == list.getFirst()) {
                list.addFirst(pair[1]);
            } else if (pair[0] == list.getLast()) {
                list.addLast(pair[1]);
            } else if (pair[1] == list.getFirst()) {
                list.addFirst(pair[0]);
            } else if (pair[1] == list.getLast()) {
                list.addLast(pair[0]);
            } else {
                queue.offer(pair);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

}
