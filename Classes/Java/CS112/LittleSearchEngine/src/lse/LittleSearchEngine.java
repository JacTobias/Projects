package lse;

import java.io.*;
import java.util.*;

/**
 * This class builds an index of keywords. Each keyword maps to a set of pages in
 * which it occurs, with frequency of occurrence in each page.
 *
 */
public class LittleSearchEngine {
	
	/**
	 * This is a hash table of all keywords. The key is the actual keyword, and the associated value is
	 * an array list of all occurrences of the keyword in documents. The array list is maintained in 
	 * DESCENDING order of frequencies.
	 */
	HashMap<String,ArrayList<Occurrence>> keywordsIndex;
	
	/**
	 * The hash set of all noise words.
	 */
	HashSet<String> noiseWords;
	
	/**
	 * Creates the keyWordsIndex and noiseWords hash tables.
	 */
	public LittleSearchEngine() {
		keywordsIndex = new HashMap<String,ArrayList<Occurrence>>(1000,2.0f);
		noiseWords = new HashSet<String>(100,2.0f);
	}
	
	/**
	 * Scans a document, and loads all keywords found into a hash table of keyword occurrences
	 * in the document. Uses the getKeyWord method to separate keywords from other words.
	 * 
	 * @param docFile Name of the document file to be scanned and loaded
	 * @return Hash table of keywords in the given document, each associated with an Occurrence object
	 * @throws FileNotFoundException If the document file is not found on disk
	 */
	public HashMap<String,Occurrence> loadKeywordsFromDocument(String docFile) 
	throws FileNotFoundException {
		/** COMPLETE THIS METHOD **/ //use scanner class?
		Scanner newScanner = new Scanner(new File(docFile));
		HashMap<String,Occurrence> keyWords = new HashMap<String,Occurrence>(200, 2.0f);
		while(newScanner.hasNext()){
			String word = newScanner.next();
			//System.out.print(word + " ");
			String newWord = getKeyword(word);
			if(newWord == null){
			}else{//not null
				if(keyWords.containsKey(newWord)){//if keyword already in hashmap
					Occurrence newWordOccurrence = keyWords.get(newWord);
					//System.out.println(newWord + " " + newWordOccurrence.frequency);
					newWordOccurrence.frequency++;

				}else{//doesnt contain keyWord
					Occurrence newWordOccurrence = new Occurrence(docFile, 1);
					keyWords.put(newWord, newWordOccurrence);
				}
			}
		}
		System.out.println(keyWords);
		System.out.println(2/2);
		
		// following line is a placeholder to make the program compile
		// you should modify it as needed when you write your code
		return keyWords;
	}
	
	/**
	 * Merges the keywords for a single document into the master keywordsIndex
	 * hash table. For each keyword, its Occurrence in the current document
	 * must be inserted in the correct place (according to descending order of
	 * frequency) in the same keyword's Occurrence list in the master hash table. 
	 * This is done by calling the insertLastOccurrence method.
	 * 
	 * @param kws Keywords hash table for a document
	 */
	public void mergeKeywords(HashMap<String,Occurrence> kws) {
		/** COMPLETE THIS METHOD **/ //must call insertLastOccurrence
		for(String keyWord : kws.keySet()){
			if(keywordsIndex.containsKey(keyWord)){
				ArrayList<Occurrence> currentKeyword = keywordsIndex.get(keyWord);
				Occurrence keyWordO = kws.get(keyWord);
				currentKeyword.add(keyWordO);
				insertLastOccurrence(currentKeyword);
			}else{//doesnt contain key
				ArrayList<Occurrence> newOList = new ArrayList<Occurrence>(1);
				Occurrence newOccurrence = kws.get(keyWord);
				newOList.add(newOccurrence);
				insertLastOccurrence(newOList);
				keywordsIndex.put(keyWord, newOList);
			}
		}
		// System.out.println(keywordsIndex);
		/*ArrayList<Occurrence> keyWords = new ArrayList<Occurrence>(1);
		for(String key : kws.keySet()){
			Occurrence currentKeyWordValue = kws.get(key);
			keyWordsIndex.put(key, currentKeyWordValue);
			keyWords.add(currentKeyWordValue);
			insertLastOccurrence(keyWords);
		}*/
	}
	
	/**
	 * Given a word, returns it as a keyword if it passes the keyword test,
	 * otherwise returns null. A keyword is any word that, after being stripped of any
	 * trailing punctuation(s), consists only of alphabetic letters, and is not
	 * a noise word. All words are treated in a case-INsensitive manner.
	 * 
	 * Punctuation characters are the following: '.', ',', '?', ':', ';' and '!'
	 * NO OTHER CHARACTER SHOULD COUNT AS PUNCTUATION
	 * 
	 * If a word has multiple trailing punctuation characters, they must all be stripped
	 * So "word!!" will become "word", and "word?!?!" will also become "word"
	 * 
	 * See assignment description for examples
	 * 
	 * @param word Candidate word
	 * @return Keyword (word without trailing punctuation, LOWER CASE)
	 */
	public String getKeyword(String word) {
		/** COMPLETE THIS METHOD **/
		
		String lowerCase = word.toLowerCase();
		//System.out.print(lowerCase + " ");
		for(int i = 0; i < word.length(); i++){
			
			if(!(lowerCase.charAt(i)>= 'a' && lowerCase.charAt(i) <= 'z')){//not a lowercase letter
				//System.out.print(lowerCase + " ");
				int lastIndex = i;
				for(int j = i+1; j < word.length(); j++){
					if((lowerCase.charAt(j) >= 'a' && lowerCase.charAt(j) <= 'z')){//is a letter after puncuation
						//System.out.print(lowerCase + "here2 ");
						return null;
					}
				}
				//no letters after
				String returnedWord = lowerCase.substring(0, lastIndex); //CHECK NOISEWORDS
				//System.out.print(returnedWord + "here1 ");
				if(noiseWords.contains(returnedWord)){//is a noise word
					return null;
				}else{//not a noise word
				return returnedWord;	
				}			
			}else{ //CHECK NOISEWORDS
				if(i ==(word.length()-1)){
					if(noiseWords.contains(lowerCase)){//noiseword
						return null;
					}else{//not a noiseword
					return lowerCase;
					}				
				}
			}
		}
		// following line is a placeholder to make the program compile
		// you should modify it as needed when you write your code
		return null;
	}
	
	/**
	 * Inserts the last occurrence in the parameter list in the correct position in the
	 * list, based on ordering occurrences on descending frequencies. The elements
	 * 0..n-2 in the list are already in the correct order. Insertion is done by
	 * first finding the correct spot using binary search, then inserting at that spot.
	 * 
	 * @param occs List of Occurrences
	 * @return Sequence of mid point indexes in the input list checked by the binary search process,
	 *         null if the size of the input list is 1. This returned array list is only used to test
	 *         your code - it is not used elsewhere in the program.
	 */
	public ArrayList<Integer> insertLastOccurrence(ArrayList<Occurrence> occs) {
		/** COMPLETE THIS METHOD **///private method that uses binary search recursively
		ArrayList<Integer> binarySearchMidpoints = new ArrayList<Integer> (1);
		if(occs.size() == 1){
			return null;
		}else{
		int lengthOriginal = occs.size();
		int lengthWithoutEnd = lengthOriginal - 1;
		Occurrence needToSort = occs.get(lengthOriginal-1);
		Occurrence lastIndexHigh = occs.get(lengthOriginal-2);
		Occurrence firstIndexLow = occs.get(0);
		Integer needToSortFrequency = needToSort.frequency;
		binarySearch(binarySearchMidpoints, occs, needToSort, needToSortFrequency, 0, (lengthWithoutEnd-1));//length - 1 = not included in binary search
		return binarySearchMidpoints;
		}
		// following line is a placeholder to make the program compile
		// you should modify it as needed when you write your code
		//return null;

	}//low is equal to high frequency, high is equal to low frequency
	private static ArrayList<Integer> binarySearch(ArrayList<Integer> midpoints, ArrayList<Occurrence> occs, Occurrence needToInsert, Integer insertingIntegerFrequency, int low, int high){
		Occurrence highestFrequency = occs.get(low);
		Occurrence lowestFrequency = occs.get(high);
		Integer highestFrequencyInt = highestFrequency.frequency;
		Integer lowestFrequencyInt = lowestFrequency.frequency;
		int midpoint = low + ((high - low) / 2);
		midpoints.add(midpoint);
		Integer middleFrequency = occs.get(midpoint).frequency;
		if(low == high){//same index, same frequency value
			if(insertingIntegerFrequency > middleFrequency){//larger
				occs.add(midpoint, needToInsert);
				int size = occs.size();
				occs.remove(size-1);
				return midpoints;
			}else if(insertingIntegerFrequency < middleFrequency){//smaller
				occs.add((midpoint+1), needToInsert);
				int size = occs.size();
				occs.remove(size-1);
				return midpoints;
			}else{//same frequency value
				occs.add(low, needToInsert);
				int size = occs.size();
				occs.remove(size-1);
				return midpoints;
			}
		}
		else{
			if(middleFrequency > insertingIntegerFrequency){
				return binarySearch(midpoints, occs, needToInsert, insertingIntegerFrequency, midpoint+1, high);
			}else if(middleFrequency < insertingIntegerFrequency){
				return binarySearch(midpoints, occs, needToInsert, insertingIntegerFrequency, low, midpoint);
			}else{//same
				occs.add(midpoint, needToInsert);
				int size = occs.size();
				occs.remove(size-1);
				return midpoints;
			}
		}
	//return null;
	}
	/**
	 * This method indexes all keywords found in all the input documents. When this
	 * method is done, the keywordsIndex hash table will be filled with all keywords,
	 * each of which is associated with an array list of Occurrence objects, arranged
	 * in decreasing frequencies of occurrence.
	 * 
	 * @param docsFile Name of file that has a list of all the document file names, one name per line
	 * @param noiseWordsFile Name of file that has a list of noise words, one noise word per line
	 * @throws FileNotFoundException If there is a problem locating any of the input files on disk
	 */
	public void makeIndex(String docsFile, String noiseWordsFile) 
	throws FileNotFoundException {
		// load noise words to hash table
		Scanner sc = new Scanner(new File(noiseWordsFile));
		while (sc.hasNext()) {
			String word = sc.next();
			noiseWords.add(word);
		}
		
		// index all keywords
		sc = new Scanner(new File(docsFile));
		while (sc.hasNext()) {
			String docFile = sc.next();
			HashMap<String,Occurrence> kws = loadKeywordsFromDocument(docFile);
			mergeKeywords(kws);
		}
		sc.close();
	}
	
	/**
	 * Search result for "kw1 or kw2". A document is in the result set if kw1 or kw2 occurs in that
	 * document. Result set is arranged in descending order of document frequencies. 
	 * 
	 * Note that a matching document will only appear once in the result. 
	 * 
	 * Ties in frequency values are broken in favor of the first keyword. 
	 * That is, if kw1 is in doc1 with frequency f1, and kw2 is in doc2 also with the same 
	 * frequency f1, then doc1 will take precedence over doc2 in the result. 
	 * 
	 * The result set is limited to 5 entries. If there are no matches at all, result is null.
	 * 
	 * See assignment description for examples
	 * 
	 * @param kw1 First keyword
	 * @param kw1 Second keyword
	 * @return List of documents in which either kw1 or kw2 occurs, arranged in descending order of
	 *         frequencies. The result size is limited to 5 documents. If there are no matches, 
	 *         returns null or empty array list.
	 */
	public ArrayList<String> top5search(String kw1, String kw2) {
		/** COMPLETE THIS METHOD **/
		//System.out.print(keywordsIndex);
		ArrayList<String> returnedArrayList = new ArrayList<String>(1);//documents
		if(keywordsIndex.containsKey(kw1)){
			//System.out.println("here1");
			if(keywordsIndex.containsKey(kw2)){
				//System.out.println("here2");
				//contains both
				ArrayList<Occurrence> key1 = keywordsIndex.get(kw1);
				ArrayList<Occurrence> key2 = keywordsIndex.get(kw2);
				ArrayList<Occurrence> sortThis = new ArrayList<Occurrence>(1);
				for(Occurrence merge : key1){
					//System.out.println("here5");
					sortThis.add(merge);
					//System.out.println(merge.document + "key1" + merge.frequency);
				}
				System.out.println();
				for(Occurrence merge2 : key2){
					//System.out.println("here6");
					//use binary search to insert this in correct spot, private
					int size = sortThis.size();
					binarySearchTop5(sortThis, 0, size, merge2, key1, key2);
				}
				/*for(int i = 0; i < sortThis.size(); i++){
					System.out.print(sortThis.get(i).frequency);
					System.out.println(sortThis.get(i).document);
				}*/
				for (int i = 0; i < sortThis.size(); i++){
					//System.out.println("here7");
					Occurrence current = sortThis.get(i);
					String currentS = current.document;
					if(returnedArrayList.size() == 0){
						returnedArrayList.add(currentS);
					}else{
					for(int j = 0; j < returnedArrayList.size(); j++){
						if(returnedArrayList.contains(currentS)){
							//System.out.println("here8");
							//dont add
						}else if((!returnedArrayList.contains(currentS)) && (j == (returnedArrayList.size() - 1))){//doesn't contain that document file
						//System.out.println("here9");
						returnedArrayList.add(currentS);
						//System.out.println(currentS);
						}
					}
					}
				}
				if(returnedArrayList.size() > 5){
					ArrayList<String> smallerReturned = new ArrayList<String>(1);
					for(int i = 0; i < 5; i++){
						String current = returnedArrayList.get(i);
						smallerReturned.add(current);
					}
					return smallerReturned;
				}else{
				return returnedArrayList;
			}
			}else{//doesnt contain kw2
				//System.out.println("here4");
			ArrayList<Occurrence> keywords1List = keywordsIndex.get(kw1);
				for(Occurrence documentO : keywords1List){
					String document = documentO.document;
					returnedArrayList.add(document);
				}
				if(returnedArrayList.size() > 5){
					ArrayList<String> smallerReturned = new ArrayList<String>(1);
					for(int i = 0; i < 5; i++){
						String current = returnedArrayList.get(i);
						smallerReturned.add(current);
					}
					return smallerReturned;
				}else{
				return returnedArrayList;
				}
			}
		}else{//doesnt contain kw1
			if(keywordsIndex.containsKey(kw2)){//doesnt contain kw1, does kw2
			ArrayList<Occurrence> keywords2List = keywordsIndex.get(kw2);
				for(Occurrence documentO : keywords2List){
					String document = documentO.document;
					returnedArrayList.add(document);
				}
				if(returnedArrayList.size() > 5){
					ArrayList<String> smallerReturned = new ArrayList<String>(1);
					for(int i = 0; i < 5; i++){
						String current = returnedArrayList.get(i);
						smallerReturned.add(current);
					}
					return smallerReturned;
				}else{
				return returnedArrayList;
			}
			}else{//doesnt contain kw2 or kw1
				return null;
			}
		}
		// following line is a placeholder to make the program compile
		// you should modify it as needed when you write your code
	//return null;
	}


	private static ArrayList<Occurrence> binarySearchTop5(ArrayList<Occurrence> sortingThis, int low, int high, Occurrence sorting, ArrayList<Occurrence> keyword1List, ArrayList<Occurrence> keyword2List){
		//Occurrence lowIndexFirst = sortingThis.get(low);
		//Occurrence highIndexEnd = sortingThis.get(high);
		int middle = low + (high - low)/2;
		Occurrence middleIndexO = sortingThis.get(middle);
		int frequencyMid = middleIndexO.frequency;
		int frequencySorting = sorting.frequency;
		if(low == high){//same index, found insertion point CHECK THE FREQUENCIES AT THIS POINT
			if(frequencyMid > frequencySorting){//insert after
				sortingThis.add((middle+1), sorting);
			}else if(frequencyMid < frequencySorting){
				sortingThis.add(middle, sorting);
			}else{
			if(keyword1List.contains(middleIndexO)){//middle occ is kw1
				sortingThis.add((middle+1), sorting);
				return sortingThis;
			}else if(keyword2List.contains(middleIndexO)){//middle occ is kw2
				sortingThis.add(middle, sorting);
				return sortingThis;
			}
		}
		}else{
			if(frequencyMid > frequencySorting){//frequency is less than, go to lower half 
				binarySearchTop5(sortingThis, (middle+1), high, sorting, keyword1List, keyword2List);

			}else if(frequencyMid < frequencySorting){//greater than, go to higher half
				binarySearchTop5(sortingThis, low, middle, sorting, keyword1List, keyword2List);
			}else{//equal
				//check middle, if keyword1 or keyword2
				if(keyword1List.contains(middleIndexO)){//middle = keyword1
					sortingThis.add((middle+1), sorting);
					return sortingThis;
				}else if(keyword2List.contains(middleIndexO)){//middle = kw2
					//sorting = kw2
					sortingThis.add(middle, sorting);
					return sortingThis;
				}
			}
		}
	return null;
	}
}
