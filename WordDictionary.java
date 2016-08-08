package leetcodeTest;

public class WordDictionary {
	
	Trie root = new Trie();
	// Adds a word into the data structure.
    public void addWord(String word) {
        root.insert(word);
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return root.search(word);
    }
    
    public static void main(String[] args) {
    	WordDictionary wordDictionary = new WordDictionary();
    	wordDictionary.addWord("word");
    	wordDictionary.addWord("qwe");
    	wordDictionary.addWord("qqqqqq");
    	wordDictionary.addWord("wwqwqw");
    	wordDictionary.addWord("qqqqwweeeee");
    	System.out.println(wordDictionary.search("..."));
    	System.out.println(wordDictionary.search("qq..qq"));
    	System.out.println(wordDictionary.search("qqqqwwwww"));
    	System.out.println(wordDictionary.search("qqqqwweeeee."));
    }
}
