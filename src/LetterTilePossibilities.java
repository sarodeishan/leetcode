package src;

import java.util.*;

public class LetterTilePossibilities {

    public static void main(String[] args) {
        LetterTilePossibilities test = new LetterTilePossibilities();
        System.out.println("Answer:" + test.numTilePossibilities("AAB"));
        System.out.println();
        System.out.println("Answer:" + test.numTilePossibilities("AAABBC"));
        System.out.println();
        System.out.println("Answer:" + test.numTilePossibilities("V"));
        System.out.println();
    }

    public int numTilePossibilities(String tiles) {
        Set<String> set = new HashSet<>();
        List<Character> letters = new ArrayList<>();
        for (char letter : tiles.toCharArray()) {
            letters.add(letter);
        }
        findLetter(set, letters, new StringBuilder());
        return set.size();
    }

    private void findLetter(Set<String> tiles, List<Character> letters, StringBuilder tile) {
        if (!letters.isEmpty()) {
            for (int i = 0; i < letters.size(); i++) {
                Character letter = letters.get(i);
                tile.append(letter);
                boolean newTile = tiles.add(tile.toString());
                if (newTile) {
                    letters.remove(i);
                    findLetter(tiles, letters, tile);
                    letters.add(i, letter);
                }
                tile.deleteCharAt(tile.length() - 1);
            }
        }
    }

}
