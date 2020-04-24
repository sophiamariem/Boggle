/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashSet;
import java.util.Set;

public class BoggleSolver {
    private final Trie trie;
    private int rows;
    private int cols;
    private Set<String> words;
    private BoggleBoard boggleBoard;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
        trie = new Trie(dictionary);
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        this.words = new HashSet<>();
        this.boggleBoard = board;
        this.rows = board.rows();
        this.cols = board.cols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boolean[][] visited = new boolean[rows][cols];
                dfs(i, j, visited, "");
            }
        }
        return words;
    }

    private void dfs(int i, int j, boolean[][] visited, String prefix) {
        if (visited[i][j]) {
            return;
        }

        char letter = boggleBoard.getLetter(i, j);
        if (letter == 'Q') {
            prefix += "QU";
        }
        else {
            prefix += letter;
        }

        TrieNode node = trie.getPrefix(prefix);
        if (node == null) {
            return;
        }
        if (prefix.length() > 2 && node.terminates()) {
            words.add(prefix);
        }

        visited[i][j] = true;

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }

                int row = x + i;
                int col = y + j;
                if (withinBounds(row, col)) {
                    dfs(row, col, visited, prefix);
                }
            }
        }

        visited[i][j] = false;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        TrieNode node = trie.getNodeFromWord(word);
        return node == null ? 0 : node.getScore();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board)) {
            StdOut.println(word);
            score += solver.scoreOf(word);
            StdOut.println(score);
        }
        StdOut.println("Score = " + score);
    }

    private boolean withinBounds(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
