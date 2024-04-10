package src;

import java.util.Arrays;

public class MovingStonesUntilConsecutive {

    public static void main(String[] args) {
        MovingStonesUntilConsecutive test = new MovingStonesUntilConsecutive();
        System.out.println("Answer:" + Arrays.toString(test.numMovesStones(1, 2, 5)));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.numMovesStones(4, 3, 2)));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.numMovesStones(3, 5, 1)));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.numMovesStones(1, 5, 9)));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.numMovesStones(1, 6, 11)));
        System.out.println();
        System.out.println("Answer:" + Arrays.toString(test.numMovesStones(7, 4, 1)));
        System.out.println();
    }

    class Position {
        int low;
        int mid;
        int high;

        Position(int low, int mid, int high) {
            this.low = low;
            this.mid = mid;
            this.high = high;
        }

    }

    public int[] numMovesStones(int a, int b, int c) {
        int[] res = new int[2];
        Position position = resolvePosition(a, b, c);
        res[1] = position.high - 1 - position.low - 1;
        if (position.high == position.mid + 1 && position.mid == position.low + 1) {
            res[0] = 0;
        } else if (position.high == position.mid + 1 || position.mid == position.low + 1) {
            res[0] = 1;
        } else if (position.high == position.mid + 2 || position.mid == position.low + 2) {
            res[0] = 1;
        } else {
            res[0] = 2;
        }
        return res;
    }

    private Position resolvePosition(int a, int b, int c) {
        Position res = null;
        if (a < b && b < c) {
            res = new Position(a, b, c);
        } else if (a < c && c < b) {
            res = new Position(a, c, b);
        } else if (b < a && a < c) {
            res = new Position(b, a, c);
        } else if (b < c && c < a) {
            res = new Position(b, c, a);
        } else if (c < a && a < b) {
            res = new Position(c, a, b);
        } else {
            res = new Position(c, b, a);
        }
        return res;
    }


}
