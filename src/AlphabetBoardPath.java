package src;

import java.time.Instant;

public class AlphabetBoardPath {

    public static void main(String[] args) {
        String[] input = new String[]{
                "leet",
                "code",
                "zdz"
        };
        String[] input2 = new String[]{
                "DDR!UURRR!!DDD!",
                "RR!DDRR!UUL!R!",
                "DDDDD!UUUUURRR!DDDDLLLD!"
        };
        for (int i = 0; i < input.length; i++) {
            tester(i + 1, input[i], input2[i]);
        }
    }

    private static void tester(int testNumber, String input, String output) {
        AlphabetBoardPath obj = new AlphabetBoardPath();
        Instant start = Instant.now();
        String result = obj.alphabetBoardPath(input);
        System.out.println(result);
        System.out.println(result.equals(output));
        System.out.println("Test:" + testNumber + " took:" + (start.getNano() - Instant.now().getNano()) + "ns");
        System.out.println();
    }

    public String alphabetBoardPath(String target) {
        if (target == null || target.isBlank()) {
            return "";
        }
        int[][] board = new int[26][2];
        int row = 0, col = 0;
        for (int alphabet = 0; alphabet < 26; alphabet++) {
            if (col == 5) {
                col = 0;
                row++;
            }
            board[alphabet] = new int[]{row, col};
            col++;
        }

        StringBuilder result = new StringBuilder();
        int pos = 0;
        for (char alphabet : target.toCharArray()) {
            int nextAlphabet = alphabet - 'a';
            result.append(provideMoves(board, pos, nextAlphabet));
            pos = nextAlphabet;
        }
        return result.toString();
    }

    private String provideMoves(int[][] board, int start, int end) {
        StringBuilder result = new StringBuilder();
        if (end != start) {
            int vertical = board[start][0] - board[end][0];
            int horizontal = board[start][1] - board[end][1];
            if (end == 25) {
                vertical++;
            }
            while (vertical < 0) {
                result.append('D');
                vertical++;
            }
            while (vertical > 0) {
                result.append('U');
                vertical--;
            }
            while (horizontal < 0) {
                result.append('R');
                horizontal++;
            }
            while (horizontal > 0) {
                result.append('L');
                horizontal--;
            }
            if (end == 25) {
                result.append('D');
            }
        }
        result.append('!');
        return result.toString();
    }

}