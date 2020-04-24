import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    private Map<Character, TrieNode> children;
    private boolean terminates = false;
    private char character;
    private int score;

    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
    }

    public TrieNode(char character) {
        this();
        this.character = character;
    }

    public void addWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        char firstChar = word.charAt(0);
        TrieNode child = getChild(firstChar);
        if (child == null) {
            child = new TrieNode(firstChar);
            children.put(firstChar, child);
        }

        if (word.length() > 1) {
            child.addWord(word.substring(1));
        }
        else {
            child.setTerminates(true);
        }
    }

    public TrieNode getChild(char c) {
        return children.get(c);
    }

    public boolean terminates() {
        return terminates;
    }

    public void setTerminates(boolean t) {
        terminates = t;
    }

    public char getCharacter() {
        return character;
    }

    public int getScore() {
        return score;
    }

    public void setScore(String word) {
        switch (word.length()) {
            case 0:
            case 1:
            case 2:
                score = 0;
                break;
            case 3:
            case 4:
                score = 1;
                break;
            case 5:
                score = 2;
                break;
            case 6:
                score = 3;
                break;
            case 7:
                score = 5;
                break;
            default:
                score = 11;
                break;
        }
    }
}