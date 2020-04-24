public class Trie {
    private final TrieNode root;

    public Trie(String[] words) {
        root = new TrieNode();
        for (String word : words) {
            root.addWord(word);
        }
    }

    public TrieNode getNodeFromWord(String word) {
        TrieNode node = getPrefix(word);
        if (node == null || !node.terminates()) {
            return null;
        }
        node.setScore(word);
        return node;
    }

    public TrieNode getPrefix(String prefix) {
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null) {
                return null;
            }
        }
        return lastNode;
    }

    public TrieNode getRoot() {
        return root;
    }

}