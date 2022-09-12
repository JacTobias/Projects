package friends;

import java.io.*;
import java.util.*;

// Testing client for Friends
public class FriendsApp {

    public static void main (String[] args) {

	if ( args.length < 1 ) {
	    System.out.println("Expecting graph text file as input");
	    return;
	}

	String filename = args[0];
	try {
	    Graph g = new Graph(new Scanner(new File(filename)));

	    // Update p1 and p2 to refer to people on Graph g
	    // sam and sergei are from sample graph
	    String p1 = "sam";
	    String p2 = "sergei";
	    ArrayList<String> shortestChain = Friends.shortestChain(g, p1, p2);

	    // Testing Friends.shortestChain
	    System.out.println("Shortest chain from " + p1 + " to " + p2);
	    for ( String s : shortestChain ) {
		System.out.println(s);
	    }

		String s1 = "rutgers";
		System.out.println();
		System.out.println("cliques");
	    ArrayList<ArrayList<String>> cliques = Friends.cliques(g, s1);
		for(int i = 0; i < cliques.size(); i++){
			ArrayList<String> current = cliques.get(i);
			for(int j = 0; j < current.size(); j++){
				System.out.println(current.get(j) + "oopps" + i);
				System.out.println(s1);
			}
		}
		System.out.println();
	    // ADD test for Friends.cliques() here
	    System.out.println("connectors");
	    ArrayList<String> connector = Friends.connectors(g);
		for(int i = 0; i < connector.size(); i++){
			System.out.println(connector.get(i));
		}
	    // ADD test for Friends.connectors() here
	} catch (FileNotFoundException e) {

	    System.out.println(filename + " not found");
	}
    }
}
