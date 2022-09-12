package friends;

import java.util.ArrayList;

import structures.Queue;
import structures.Stack;

public class Friends {

	/**
	 * Finds the shortest chain of people from p1 to p2.
	 * Chain is returned as a sequence of names starting with p1,
	 * and ending with p2. Each pair (n1,n2) of consecutive names in
	 * the returned chain is an edge in the graph.
	 * 
	 * @param g Graph for which shortest chain is to be found.
	 * @param p1 Person with whom the chain originates
	 * @param p2 Person at whom the chain terminates
	 * @return The shortest chain from p1 to p2. Null or empty array list if there is no
	 *         path from p1 to p2
	 */
	public static ArrayList<String> shortestChain(Graph g, String p1, String p2) {
		Queue<Integer> queueFNum = new Queue<Integer>();
		ArrayList<String> returnedChain = new ArrayList<String>(1);
		ArrayList<Integer> temporaryChain = new ArrayList<Integer>(1);
		int lengthOfMembers = g.members.length;
			boolean[] marked = new boolean[lengthOfMembers];
			Integer[] edgeTo = new Integer[lengthOfMembers];
			Integer[] distanceTo = new Integer[lengthOfMembers];
		Integer p1Number = g.map.get(p1); //starting integer
		Integer p2Number = g.map.get(p2); //terminate here
		queueFNum.enqueue(p1Number);
		marked[p1Number] = true;
		distanceTo[p1Number] = 0;
		while(!queueFNum.isEmpty()){
			Integer v = queueFNum.dequeue();
			String vPersonS = g.members[v].name;
			for(Friend f = g.members[v].first; f != null; f = f.next){
				Integer fNumber = f.fnum;
				if(!marked[fNumber]){
				queueFNum.enqueue(fNumber);	
				marked[fNumber] = true;
				distanceTo[fNumber] = distanceTo[v] + 1;
				edgeTo[fNumber] = v;
				if(fNumber == p2Number){
					break;
				}
				}
			}
			if(marked[p2Number] == true){
				break;
			}
		}
		Integer traverse = p2Number;
		returnedChain.add(p2);
		while(traverse != p1Number){
			//System.out.println(traverse);
			Integer nextFriend = edgeTo[traverse];
			String nextFriendName = g.members[nextFriend].name;
			returnedChain.add(0, nextFriendName);
			traverse = nextFriend;
		}
		return returnedChain;
		/** COMPLETE THIS METHOD **/
		/*for(int i = 0; i < g.members.length; i++){
			System.out.println(g.members[i].name);
			for(Friend f = g.members[i].first; f != null; f = f.next){
				int friendNumber = f.fnum;
				String name = g.members[friendNumber].name;
				System.out.println(name + " friend");
			}
		}*/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		//return null;
	}
	
	/**
	 * Finds all cliques of students in a given school.
	 * 
	 * Returns an array list of array lists - each constituent array list contains
	 * the names of all students in a clique.
	 * 
	 * @param g Graph for which cliques are to be found.
	 * @param school Name of school
	 * @return Array list of clique array lists. Null or empty array list if there is no student in the
	 *         given school
	 */
	public static ArrayList<ArrayList<String>> cliques(Graph g, String school) {
		//System.out.println("here");
		ArrayList<ArrayList<String>> returnedList = new ArrayList<ArrayList<String>>(1);
		int length = g.members.length;
		boolean [] marked = new boolean[length];
		//int[] edgeTo = new int[length];
		boolean[] rightSchool = new boolean[length];
		Queue<Integer> newQueue = new Queue<Integer>();
		int startingIndex = 0;
		boolean studentInSchool = false;
		for (int i = 0; i < length; i++){
			String school1 = g.members[i].school;
			//System.out.println(g.members[i].name + " " + g.members[i].school);
			if(school1.equals(school)){
				startingIndex = i;
				studentInSchool = true;
				break;
			}
		}
		if(studentInSchool == false){
			//System.out.println("here");
			return returnedList;
		}else{
		newQueue.enqueue(startingIndex);
		marked[startingIndex] = true;
		rightSchool[startingIndex] = true;
		ArrayList<String> firstClique = new ArrayList<String>(1);
		firstClique.add(g.members[startingIndex].name);
		returnedList.add(firstClique);
		while(!newQueue.isEmpty()){
			//System.out.println("here2");
			int currentIndex = newQueue.dequeue();//already in/ not correct school
			//System.out.println(currentIndex + " index");
			for(Friend f = g.members[currentIndex].first; f != null; f = f.next){//going throug currentIndex friends
				if(!marked[f.fnum]){//not marked
					//System.out.println("here6");
					newQueue.enqueue(f.fnum);
					marked[f.fnum] = true;
					boolean hasFriend = false;
					int fIndex = f.fnum;
					if(g.members[fIndex].school != null){//same school
						if(g.members[fIndex].school.equals(school)){
						//System.out.println("here00");
						for(int i = 0; i < returnedList.size(); i++){
							ArrayList<String> current = returnedList.get(i);
							//System.out.println("here11");
							for(int j = 0; j < current.size(); j++){
								//System.out.println("here22");
								String nameFriend = current.get(j);
								for(Friend f2 = g.members[f.fnum].first; f2 != null; f2 = f2.next){
									//System.out.println("here33");
									//System.out.println(g.members[f.fnum].name);
									String friend = g.members[f2.fnum].name;
									//System.out.println(friend);
									//System.out.println()
									if(friend.equals(nameFriend)){
										//System.out.println("here44");
										current.add(g.members[f.fnum].name);
										//System.out.println("here99" + g.members[f.fnum].name);
										hasFriend = true;
										break;
									}
								}
								if(hasFriend == true){
									break;
								}
							}
							if(hasFriend == true){
								break;
							}
						}
						if(hasFriend == false){
							ArrayList<String> newList = new ArrayList<String>(0);
							newList.add(g.members[f.fnum].name);
							returnedList.add(newList);
						}
					}
					}
				}
			}
		}
	}
		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return returnedList;
	}
	
	/**
	 * Finds and returns all connectors in the graph.
	 * 
	 * @param g Graph for which connectors needs to be found.
	 * @return Names of all connectors. Null or empty array list if there are no connectors.
	 */
	public static ArrayList<String> connectors(Graph g) {
		ArrayList<String> connectors = new ArrayList<String>(1);
		int length = g.members.length;
		int[] dfsNumber = new int[length];
		int[] backNumber = new int[length];
		int[] edgeTo = new int[length];
		boolean[] marked = new boolean[length];
		int dfsNum = 0;
		connectors = dfsConnector(g, dfsNumber, backNumber, 0, edgeTo, 0, connectors, marked, dfsNum);
		//go through rest of members array to find other connectors in different island
		for(int i = 0; i < marked.length; i++){
			if(!marked[i]){
				dfsConnector(g, dfsNumber, backNumber, i, edgeTo, i, connectors, marked, dfsNum);
			}
		}

		/** COMPLETE THIS METHOD **/
		
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE COMPILER HAPPY
		// CHANGE AS REQUIRED FOR YOUR IMPLEMENTATION
		return connectors;
		
	}

	private static ArrayList<String> dfsConnector(Graph g, int[] dfsnum, int[] backnum, int startingPoint, int[] edgeTo, int currentIndex, ArrayList<String> connector, boolean[] marked, int dfsNumber){
		if(!marked[startingPoint]){
			marked[startingPoint] = true;
			dfsNumber++;
			dfsnum[startingPoint] = dfsNumber;
			backnum[startingPoint] = dfsNumber;
		}
		marked[currentIndex] = true;
		for(Friend f = g.members[currentIndex].first; f != null; f = f.next){//going through friends of currentIndex
			if(!marked[f.fnum]){//not marked
				dfsNumber++;
				//marked[f.fnum] = true;
				edgeTo[f.fnum] = currentIndex;
				dfsnum[f.fnum] = dfsNumber;
				backnum[f.fnum] = dfsNumber;
				dfsConnector(g, dfsnum, backnum, startingPoint, edgeTo, f.fnum, connector, marked, dfsNumber);
				if(dfsnum[currentIndex] > backnum[f.fnum]){
					backnum[currentIndex] = min(backnum[currentIndex], backnum[f.fnum]);
				}
				if(dfsnum[currentIndex] <= backnum[f.fnum]){
					//can be connector
					if(currentIndex == startingPoint){
						//do something else, can be connector
						for(Friend f2 = g.members[startingPoint].first; f2 != null; f2 = f2.next){
							int f2Friend = f2.fnum;
							if(g.members[f2Friend].first.next == null){//connector
								if(!connector.contains(g.members[startingPoint].name)){
									connector.add(g.members[startingPoint].name);
									break;
								}
							}
						}
					}else{
					String name = g.members[currentIndex].name;
					if(!connector.contains(name)){
						connector.add(name);
					}
				}
				}
			}
			if(marked[f.fnum] == true){
				//backnum[currentIndex]--;
				backnum[currentIndex] = min(backnum[currentIndex], dfsnum[f.fnum]);
				/*if(dfsnum[f.fnum] <= backnum[currentIndex]){
					if(f.next == null){
					String nameAdd= g.members[f.fnum].name;
					if(!connector.contains(nameAdd)){
						connector.add(nameAdd);
						//return connector;
					}
				}
				}*/
			}
		}
	return connector;
	}

	private static int min(int i, int j) {
		if(i > j){
			return j;
		}else if(j > i){
			return i;
		}else{
			return j;
		}
	}
}




