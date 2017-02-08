/*
 * Word Ladder Problem
 * Date: 19.04.2015
 * 
 * Execute as "main.java <dictionary.txt> <input.txt> <output.txt>"
 * There is 2 words that is read from input.txt, first word is starting point second word is end point
 * There must be a path between starting point and ending point
 * You have to change one letter into another word until reach end point
 * poli - foli - fali - fati - fatv
 * 
 * @author Fatih Baltaci
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {
	
	static List<String> dicWords = new ArrayList<String>();      //Dictionary Words

	public static void main(String[] args) {
		
		BufferedReader dicReader = null;
		BufferedReader inReader = null;
		PrintWriter outWrite = null;
		
		try 
		{
		    File dicfile = new File(args[0]);
		    File infile = new File(args[1]);
		    
		    // Cp1252 is for ANSI encoding 
		    dicReader = new BufferedReader(new InputStreamReader(new FileInputStream(dicfile), "Cp1252"));
		    inReader = new BufferedReader(new InputStreamReader(new FileInputStream(infile), "Cp1252"));
		    outWrite = new PrintWriter(new File(args[2]));

		    /*
			 * Reads dictionary.txt then adds the words to Array List which is dicWords
			 *******************************************************************************************/
		    String word;
		    while ((word = dicReader.readLine()) != null) {
		        dicWords.add(word);
		    }

		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		    	dicReader.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

		
		/*
		 * Creating Graph:
		 * Looks all the nodes difference if different word number is 1 
		 * Add a connection(edge) between to nodes ( node1 ------ node2 )
		 ****************************************************************/
		
		MyGraph graph = new MyGraph(dicWords.size());
		String sum;
		for (int i = 0; i < dicWords.size(); i++) {
			for (int j = i ; j < dicWords.size(); j++) 
			{
				String node1 = dicWords.get(i);
				String node2 = dicWords.get(j);
				makeCalculations(node1, node2);
				if(diffTwoNodes(node1, node2) == 1)
				{
					sum = node1 + node2;
					makeCalculations(sum, node2);
					graph.addConnection(dicWords.indexOf(node1), dicWords.indexOf(node2));
				}
			}
		}
		
		/*
		 * Makes Breath First Search to find shortest path from start to end
		 * path List is for keep word path from start to end
		 ***********************************************************************/
		String line ="";
		try {
			while((line = inReader.readLine()) != null)
			{
				List<Integer> path = new ArrayList<Integer>();
				String lines[] = line.split(" ");  
				String start = lines[0];
				String end = lines[1];
				//There are no such word in the dictionary
				if(dicWords.indexOf(end) == -1 || dicWords.indexOf(start) == -1)
				{ 
					outWrite.println("Failure");
					continue;
				}
				MyBFS mybfs = new MyBFS(graph, dicWords.indexOf(start));
				if(mybfs.visitedOrNot(dicWords.indexOf(end)) == 1)
				{
					int i;
					for (i = dicWords.indexOf(end);  mybfs.edgeDistance(i) != 0; i = mybfs.previousEdge(i)) {
						path.add(i);
					}
					path.add(i);
					
					for (int j = path.size()-1; j > 0; j--) {
						outWrite.print(dicWords.get(path.get(j))+",");
						if(j == 1) 
							outWrite.println(end);
					}
				}
				//There is no connection
				else
					outWrite.println(("Failure"));
				//Starting and ending word is equal
				if(start.matches(end))
					outWrite.println(start);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		outWrite.close();
	}
	
	private static void makeCalculations(String node1, String node2) {
		String make1 = node1;
		String make2 = node2;
		
		for (int i = 0; i < node1.length(); i++) {
			if(node1.length() == node2.length())
			{
				make1 = make2;
				make2 = make1;
				continue;
			}
				
			else
				return;
		}
		return;
		
	}

	/*
	 * Returns two nodes' difference letters number
	 * For Example:
	 * node1: lesk
	 * node2: mask
	 * diffTwoNodes method returns 2
	 * Return 0 means the Strings are equal
	 ******************************************************************************************/
	private static int diffTwoNodes(String node1, String node2)
	{
		int nodeDiff = 0;

		for (int j = 0; j < node1.length(); j++) {
			if(node1.charAt(j) != node2.charAt(j))
				nodeDiff++;
		}
		return nodeDiff;
	}
}
