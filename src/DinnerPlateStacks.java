package src;

import java.time.Instant;
import java.util.*;

public class DinnerPlateStacks implements TreeTemplate {

    enum Operation {
        DinnerPlates, push, pop, popAtStack;
    }

    record TestCase(Operation[] operations, Integer[] arguments, Integer[] output) {
    }

    public static void main(String[] args) {
        TestCase[] testCases = new TestCase[]{
                new TestCase(
                        Arrays.stream(new String[]{"DinnerPlates", "push", "push", "push", "push", "push", "popAtStack", "push", "push", "popAtStack", "popAtStack", "pop", "pop", "pop", "pop", "pop"}).map(Operation::valueOf).toList().toArray(new Operation[]{}),
                        new Integer[]{2, 1, 2, 3, 4, 5, 0, 20, 21, 0, 2, null, null, null, null, null},
                        new Integer[]{null, null, null, null, null, null, 2, null, null, 20, 21, 5, 4, 3, 1, -1})
        };
        for (int i = 0; i < testCases.length; i++) {
            tester(i + 1, testCases[i]);
        }
    }

    private static void tester(int testNumber, TestCase testCase) {
        DinnerPlateStacks obj = new DinnerPlateStacks();
        Instant start = Instant.now();
        var result = obj.perform(testCase.operations(), testCase.arguments());
        boolean success = (Arrays.equals(result, testCase.output()));
        StringBuilder message = new StringBuilder();
        message.append("Test:").append(testNumber).append(" took:").append(start.getNano() - Instant.now().getNano()).append("ns")
                .append(" ").append("Success:" + (success))
                .append(" ").append("Output:").append(Arrays.toString(result));
        if (success) {
            System.out.println(message);
        } else {
            System.err.println(message);
        }
        System.out.println();
    }

    private Integer[] perform(Operation[] operations, Integer[] arguments) {
        Integer[] result = new Integer[operations.length];
        DinnerPlates obj = new DinnerPlates(arguments[0]);
        for (int i = 1; i < operations.length; i++) {
            System.out.println(operations[i] + " " + arguments[i]);
            switch (operations[i]) {
                case push -> obj.push(arguments[i]);
                case pop -> result[i] = obj.pop();
                case popAtStack -> result[i] = obj.popAtStack(arguments[i]);
            }
        }
        return result;
    }

    static class DinnerPlates {

        private final ArrayList<Stack<Integer>> data;
        private final Queue<Integer> gaps;
        private final int stackCapacity;

        public DinnerPlates(int capacity) {
            this.stackCapacity = capacity;
            this.data = new ArrayList<>();
            this.data.add(new Stack<>());
            this.gaps = new PriorityQueue<>(Comparator.naturalOrder());
        }

        public void push(int val) {
            if (gaps.isEmpty()) {
                if (data.get(data.size() - 1).size() < stackCapacity) {
                    data.get(data.size() - 1).push(val);
                } else {
                    Stack<Integer> stack = new Stack<>();
                    stack.push(val);
                    data.add(stack);
                }
            } else {
                int stackIndex = gaps.poll();
                data.get(stackIndex).push(val);
            }
        }

        public int pop() {
            int val = -1;
            for (int i = data.size() - 1; i >= 0; i--) {
                if (!data.get(i).isEmpty()) {
                    val = data.get(i).pop();
                    break;
                }
            }
            return val;
        }

        public int popAtStack(int index) {
            if (index < data.size() && !data.get(index).isEmpty()) {
                gaps.offer(index);
                return data.get(index).pop();
            }
            return -1;
        }

    }

}