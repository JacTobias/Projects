package trie;

import java.util.ArrayList;

/**
 * This class implements a Trie. 
 * 
 * @author Sesh Venugopal
 *
 */
public class Trie {
	
	// prevent instantiation
	private Trie() { }
	
	/**
	 * Builds a trie by inserting all words in the input array, one at a time,
	 * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)
	 * The words in the input array are all lower case.
	 * 
	 * @param allWords Input array of words (lowercase) to be inserted.
	 * @return Root of trie with all words inserted from the input array
	 */
	public static TrieNode buildTrie(String[] allWords) {
		/** COMPLETE THIS METHOD **/
		Indexes index = new Indexes(0, (short)0, (short)(allWords[0].length() - 1));
		TrieNode firstChild = new TrieNode(index, null, null);
		TrieNode root = new TrieNode(null, null, null);
		root.firstChild = firstChild;
		for (int x = 1; x < allWords.length; x++){
			TrieNode temp = firstChild;
			while(temp != null){ //checking sibling nodes
				Character tempWord = allWords[temp.substr.wordIndex].charAt(temp.substr.startIndex);
				Character currentWord = allWords[x].charAt(temp.substr.startIndex);
				int compared = tempWord.compareTo(currentWord);
				if(compared == 0){//check how much of prefix is in word
					short newEnd = temp.substr.endIndex;
					for(int i = temp.substr.startIndex; i < (temp.substr.endIndex + 1); i++){
						Character tempLetter = allWords[temp.substr.wordIndex].charAt(i);
						Character currentLetter = allWords[x].charAt(i);
						int compared1 = tempLetter.compareTo(currentLetter);
						if(compared1 == 0){
						}else{
							newEnd = (short)(i - 1);
							break;
						}
					}//make new prefix
					if(newEnd == temp.substr.endIndex){//all of prefix within current word, check children
						TrieNode temp2 = temp.firstChild; 
						while(temp2 != null){//checking all children of first prefix
							Character tempWord2 = allWords[temp2.substr.wordIndex].charAt(temp2.substr.startIndex);
							Character currentWord2 = allWords[x].charAt(temp2.substr.startIndex);
							int compared2 = tempWord2.compareTo(currentWord2);
							if(compared2 == 0){//same first letter
								short newEnd2 = temp2.substr.endIndex;
								for(int i = temp2.substr.startIndex; i < (temp2.substr.endIndex + 1); i++){
									Character tempWordT2 = allWords[temp2.substr.wordIndex].charAt(i);
									Character currentWordT2 = allWords[x].charAt(i);
									int compared2T = tempWordT2.compareTo(currentWordT2);
									if(compared2T == 0){
									}else{
										newEnd2 = (short)(i - 1);
										break;
									}
								}
									temp2.substr.startIndex = (short)(temp.substr.endIndex + 1);
									temp2.substr.endIndex = newEnd2;
									Indexes tempWord2I = new Indexes(temp2.substr.wordIndex, (short)(newEnd2 + 1), (short)(allWords[temp2.substr.wordIndex].length() - 1));
									TrieNode tempWord2T = new TrieNode(tempWord2I, null, null);
									temp2.firstChild = tempWord2T;
									Indexes currentWordI = new Indexes(x, (short)(newEnd2 + 1), (short)(allWords[x].length() - 1));
									TrieNode currentWordT = new TrieNode(currentWordI, null, null);
									tempWord2T.sibling = currentWordT;
									break;
								
							}else if((temp2.sibling == null) && (compared2 != 0)){//add new sibling node
							Indexes newSibling2 = new Indexes(x, temp2.substr.startIndex, (short)(allWords[x].length() - 1));
							TrieNode newSiblingT = new TrieNode(newSibling2, null, null);
							temp2.sibling = newSiblingT;
							break;
							}
						temp2 = temp2.sibling;
						}
					}else{
						temp.substr.endIndex = newEnd;
						int index1 = temp.substr.wordIndex;
						short endIndexC = (short)allWords[x].length();
						short endIndex = (short)(allWords[index1].length() - 1);
						Indexes tempWordI = new Indexes(temp.substr.wordIndex, (short)(newEnd + 1), endIndex);
						TrieNode tempWordT = new TrieNode(tempWordI, null, null);
						temp.firstChild = tempWordT;
						Indexes currentWordI = new Indexes(x, (short)(newEnd + 1), (short)(endIndexC - 1));
						TrieNode currentWordT = new TrieNode(currentWordI, null, null);
						tempWordT.sibling = currentWordT;
						break;
					}
				}else if((temp.sibling == null) && (compared != 0)){
					Indexes newIndex = new Indexes(x, (short)0, (short)(allWords[x].length() - 1));
					TrieNode newSibling = new TrieNode(newIndex, null, null);
					temp.sibling = newSibling;
				}
			temp = temp.sibling;
			}
		}
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		return root;
	
	}
	/**
	 * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 
	 * trie whose words start with this prefix. 
	 * For instance, if the trie had the words "bear", "bull", "stock", and "bell",
	 * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 
	 * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 
	 * and for prefix "bell", completion would be the leaf node that holds "bell". 
	 * (The last example shows that an input prefix can be an entire word.) 
	 * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",
	 * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].
	 *
	 * @param root Root of Trie that stores all words to search on for completion lists
	 * @param allWords Array of words that have been inserted into the trie
	 * @param prefix Prefix to be completed with words in trie
	 * @return List of all leaf nodes in trie that hold words that start with the prefix, 
	 * 			order of leaf nodes does not matter.
	 *         If there is no word in the tree that has this prefix, null is returned.
	 */
	public static ArrayList<TrieNode> completionList(TrieNode root,
										String[] allWords, String prefix) {
		/** COMPLETE THIS METHOD **/
		ArrayList<TrieNode> words = new ArrayList<TrieNode>(1);
		TrieNode temp = root.firstChild;
		while(temp != null){
			Character prefixLetter = prefix.charAt(temp.substr.startIndex);
			Character tempLetter = allWords[temp.substr.wordIndex].charAt(temp.substr.startIndex);
			int compared = prefixLetter.compareTo(tempLetter);
			if(compared == 0){
				//System.out.println("here1");
				if(prefix.length() != 1){
					//System.out.println("here2");
					TrieNode temp2 = temp.firstChild;
					while(temp2 != null){
					int prefixLength = prefix.length();
					String tempWordLength = allWords[temp2.substr.wordIndex].substring(0, prefixLength);
					boolean isSame = prefix.equals(tempWordLength);
					if(isSame == true){//prefix within that word, go to children nodes
						//System.out.println("here4");
						if(temp2.firstChild == null){
							//System.out.println("here8");
							TrieNode temp3 = temp2;
							while(temp3 != null){
								words.add(temp3);
							temp3 = temp3.sibling;
							}
							break;
						}else{
							TrieNode temp3 = temp2.firstChild;//,firstChild
							while(temp3 != null){
								if(temp3.firstChild == null){
								//System.out.println("here");
								TrieNode temp4 = temp3;
									while(temp4 != null){
									//System.out.println("here7");
									words.add(temp4);
								temp4 = temp4.sibling;
								}
								}else{//continue down
							}
						temp3 = temp3.firstChild;
						}
					}
					}else{//continue through while loop
						//System.out.println("here5");
					}
					temp2 = temp2.sibling;
				}
				}else{	//length of prefix = 1, go until leaf nodes
					TrieNode temp2 = temp.firstChild;
					while(temp2 != null){ //goes through children
						if(temp2.firstChild == null){
							words.add(temp2);
							//TrieNode temp3 = temp2;
							//while(temp3 != null){
							//	words.add(temp3);
							//temp3 = temp3.sibling;
							//}
						}else{//not null, continue going down
							TrieNode temp3 = temp2;
							while(temp3 != null){
								if(temp3.firstChild == null){
									TrieNode temp4 = temp3;
									while(temp4 != null){
										words.add(temp4);
									temp4 = temp4.sibling;
									}
								}
							temp3 = temp2.firstChild;
							}
						}
					temp2 = temp2.sibling;
					}
			}
			}else{// same first letter, continue down siblings
				System.out.println("here3");
			}
		temp = temp.sibling;
		}
		//words = addToArrayList(temp, prefix, allWords, words);
		//words.add();
		// FOLLOWING LINE IS A PLACEHOLDER TO ENSURE COMPILATION
		// MODIFY IT AS NEEDED FOR YOUR IMPLEMENTATION
		/*if(words.size() == 0){
			return null;
		}else{
			return words;
		}*/
		if(words.size() == 0){
			return null;
		}
		return words;
	}

	/*private static ArrayList<TrieNode> addToArrayList(TrieNode temp, String prefix, String[] allWords, ArrayList<TrieNode> words){
		if(temp == null){
			return words;
		}
		else{
			int prefixLastIndex = prefix.length();
			Character prefixLetter = prefix.charAt(0);
			Character tempStringPrefix = allWords[temp.substr.wordIndex].charAt(0);
			int compared = prefixLetter.compareTo(tempStringPrefix);
			if(compared == 0){//checked first letter
				String tempWord = allWords[temp.substr.wordIndex].substring(0, prefixLastIndex);
				boolean comparedB = prefix.equals(tempWord);
				if(comparedB == true){//prefix within word index for that TrieNode, go to children
					if(temp.firstChild == null){//no more children, leaf node, go to sibling
						words.add(temp);
						addToArrayList(temp.sibling, prefix, allWords, words);
					}else{//not null, continue down children
						addToArrayList(temp.firstChild, prefix, allWords, words);
					}
				}else{//false, go to child

					addToArrayList(temp.firstChild.sibling, prefix, allWords, words);
				}
			}else{
				addToArrayList(temp.sibling, prefix, allWords, words);
			}
		}
	return words;
	}*/


	public static void print(TrieNode root, String[] allWords) {
		System.out.println("\nTRIE\n");
		print(root, 1, allWords);
	}
	
	private static void print(TrieNode root, int indent, String[] words) {
		if (root == null) {
			return;
		}
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		
		if (root.substr != null) {
			String pre = words[root.substr.wordIndex]
							.substring(0, root.substr.endIndex+1);
			System.out.println("      " + pre);
		}
		
		for (int i=0; i < indent-1; i++) {
			System.out.print("    ");
		}
		System.out.print(" ---");
		if (root.substr == null) {
			System.out.println("root");
		} else {
			System.out.println(root.substr);
		}
		
		for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {
			for (int i=0; i < indent-1; i++) {
				System.out.print("    ");
			}
			System.out.println("     |");
			print(ptr, indent+1, words);
		}
	}
 }
