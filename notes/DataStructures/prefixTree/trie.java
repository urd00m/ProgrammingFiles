package prefixTree;
import java.util.*;

public class trie {
	
	public static void main(String args[]) {
		Trie test = new Trie();
		test.insert("majedoc"); test.insert("maj"); test.insert("mah"); test.insert("malan"); test.insert("muh"); test.insert("molon");
		System.out.println(test.startsWith("maj")); 
	}
	
	//prefix tree implementation 
	public static class Trie {
		private TrieNode root;

		/** Initialize your data structure here. */
		public Trie() {
			root = new TrieNode();
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if (!node.containsKey(currentChar)) {
					node.put(currentChar, new TrieNode());
				}
				node = node.get(currentChar);
			}
			node.setEnd();
		}

		// Returns if the word is in the trie.
		public boolean search(String word) {
			TrieNode node = searchPrefix(word);
			return node != null && node.isEnd();
		}

		// Returns if there is any word in the trie
		// that starts with the given prefix.
		public boolean startsWith(String prefix) {
			TrieNode node = searchPrefix(prefix);
			return node != null;
		}

		// search a prefix or whole key in trie and
		// returns the node where search ends
		public TrieNode searchPrefix(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char curLetter = word.charAt(i);
				if (node.containsKey(curLetter)) {
					node = node.get(curLetter);
				} else {
					return null;
				}
			}
			return node;
		}
		
		/*   this is just for printing  
		int id = 0; 
		public void printTree() {
			id = 0; 
			//bfs search and just printing each level 
			Queue<pair> next = new LinkedList<pair>(); 
			next.add(new pair(root, -1)); 
			while(next.isEmpty() == false) {
				pair cur = next.remove(); 
				//go through each of its children and print 
				for(int i = 0; i < 26; i++) {
					if(cur.a.containsKey((char)(i+'a')) == true) {
						next.add(new pair(cur.a.get((char)(i+'a')), ++id)); 
						System.out.println((char)(i+'a')+" id: "+id+" par: " + cur.i);
					}
				}
			}
		}
		
		
		//just for printing 
		class pair {
			TrieNode a; 
			int i; //parent id for its children 
			public pair(TrieNode b, int c) {
				a = b; 
				i = c; 
			}
		}
		*/
		//the nodes in the graph 
		class TrieNode {

			// R links to node children only works with lower cases so you have to convert 
			private TrieNode[] links;

			private final int R = 26;

			private boolean isEnd;
			ArrayList<Integer> next = new ArrayList<Integer>(); //O(n) lookup increases speed a little bit 
			public TrieNode() {
				links = new TrieNode[R];
			}

			public boolean containsKey(char ch) {
				return links[ch - 'a'] != null;
			}

			public TrieNode get(char ch) {
				return links[ch - 'a'];
			}

			public void put(char ch, TrieNode node) {
				links[ch - 'a'] = node;
				next.add(ch - 'a'); 
			}

			public void setEnd() {
				isEnd = true;
			}

			public boolean isEnd() {
				return isEnd;
			}
		}
	}
}
