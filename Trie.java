package leetcodeTest;

import java.util.HashSet;

/* 208. Implement Trie (Prefix Tree)
 * 
 * public class Trie {
	private TrieNode root;
	private TrieNode curr;
	public HashSet<String> hash = new HashSet<String>();
    public Trie() {
        root = new TrieNode();
        curr = root;
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if(word.length() == 0){
        	
        	curr = root;
        	return ;
        }
        int k = word.charAt(0) - 'a';
        if(curr.childNodes[k] == null){
        	curr.childNodes[k] = new TrieNode();
        	curr.childNodes[k].nodeChar = word.charAt(0);
        }
        if(curr == root)
        	this.hash.add(word);
        curr = curr.childNodes[k];
        String next = word.substring(1);
        insert(next);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
    	if(this.hash.contains(word)) return true;
    	else return false;
        
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	if(prefix.length() == 0) {
    		curr = root;
    		return true;
    	}
    	int k = prefix.charAt(0) - 'a';
    	if(curr.childNodes[k] == null) {
    		curr = root;
    		return false;
    	}
    	else curr = curr.childNodes[k];
    	String next = prefix.substring(1);
    	return startsWith(next);
        
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie root = new Trie();
		root.insert("asdf");
		root.insert("azsd");
		root.insert("asde");
		root.insert("bdas");
		root.insert("bgas");
		root.insert("power");
		root.insert("");
		System.out.println(root.search("asde"));
		System.out.println(root.search("asdfd"));
		System.out.println(root.startsWith("asdw"));
		System.out.println(root.startsWith("as"));
	}

}
class TrieNode {
	public TrieNode[] childNodes;
	public char nodeChar;
    // Initialize your data structure here.
    public TrieNode() {
        childNodes = new TrieNode[26];

    }
}*/
public class Trie {
	private TrieNode root;
	private boolean start;
	public Trie(){
		root = new TrieNode();
	}
	public void insert(String word){
		insert(word,root,0);
	}
	public void insert(String word,TrieNode root,int idx){
		if(idx == word.length()){
			root.isWord = true;
			return ;
		}
		int index = word.charAt(idx)-'a';
		if(root.childNodes[index] == null){
			root.childNodes[index] = new TrieNode();
		}
		insert(word,root.childNodes[index],idx+1);
	}
	public boolean search(String word){
		return search(word,root,0);
	}
	public boolean search(String word,TrieNode root,int idx){
		if(idx == word.length()){
			start = true;
			return root.isWord;
		}
		if(word.charAt(idx)=='.'){
			boolean flag = false;
			for(int i=0;i<26 && !flag;i++){
				if(root.childNodes[i]!=null){
					flag = search(word, root.childNodes[i], idx+1);
				}
			}
			return flag;
		}
		else {
			int index = word.charAt(idx) - 'a';
			
			if(root.childNodes[index] == null){
				start = false;
				return false;
			}
			return search(word, root.childNodes[index], idx+1);
		}
	}
	public boolean startsWith(String prefix){
		search(prefix);
		return start;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Trie root = new Trie();
		root.insert("asdf");
		root.insert("azsd");
		root.insert("asde");
		root.insert("bdas");
		root.insert("bgas");
		root.insert("power");
		root.insert("");
		System.out.println(root.search("asde"));
		System.out.println(root.search("asdfd"));
		System.out.println(root.startsWith("asdw"));
		System.out.println(root.startsWith("as"));
	}
}
class TrieNode {
	public TrieNode[] childNodes;
	public boolean isWord;
    // Initialize your data structure here.
    public TrieNode() {
        childNodes = new TrieNode[26];
        isWord = false;

    }
}
