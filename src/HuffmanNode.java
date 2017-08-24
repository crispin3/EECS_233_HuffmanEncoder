import java.util.LinkedList;
import java.io.*;
import java.util.*;

// Chose linkedlist for assignment because it is I am not familiar enough
// with them
public class HuffmanNode {
	
	// Initialize variables
	private Character inChar;
	private int frequency = 0;
	private HuffmanNode left;
	private HuffmanNode right;
	
	// Builds code for characters while traversing through Huffman tree
	private static StringBuilder createCode = new StringBuilder();
	// Stores Huffman code for each character
	private static String[] encoding = new String[500];
	
	// Constructor to create node
	public HuffmanNode(Character inChar, int frequency, HuffmanNode right, HuffmanNode left) {
		this.inChar = inChar;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	// Link to stored node and variable information
	  public int getFreq(){
		    return frequency;
		  }
		  
	  public Character getTheChar() {
		    return inChar;
		  }
	  
	  public HuffmanNode getLeft() {
		    return left;
		  }
		  
	  public HuffmanNode getRight() {
		    return right;
		  }
	  
	  public static String[] getEncoder() {
		    return encoding;
		  }
	  
	  // Counts each character in input text file
	  public static int[] countFreq(String inputFile) throws IOException {
			if (inputFile == null) {
				throw new IOException();
			}
			File input = new File(inputFile);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(input));
			
			int count = 0;
		    int[] charCount = new int[500];
		    
		    while ((count = bufferedReader.read()) != -1) {
		    	if (count < 500) {
		    		charCount[(int)count]++;
		    	}
		    }
		    bufferedReader.close();
			return charCount;
		  }
	  
	  // Creates unordered linklist utilizing HuffmanNode constructor based on
	  // character frequency
	  public static LinkedList<HuffmanNode> unorderedLinkedList(int[] charCount) {
		  	LinkedList<HuffmanNode> countList = new LinkedList<HuffmanNode>();
		  	// Iterates through charCount list and creates Huffman node
		    for (int i = 0; i < charCount.length; i++) {
		        if(charCount[i] > 0){
		          countList.add(new HuffmanNode((char)i, charCount[i], null, null));
		        }
		      }
		      return countList;
		    }
	  
	  // Returns node with lowest character count
	  public static HuffmanNode minFreq(LinkedList<HuffmanNode> countList) {	  
		  HuffmanNode lowestFreqNode = countList.peek();
		  // Traverses countList to find node with lowest char count
		  for (HuffmanNode currentNode : countList) {
			  if (currentNode.getFreq() < lowestFreqNode.getFreq()) {
				  lowestFreqNode = currentNode;
			  }
		  }
		  return lowestFreqNode;
	  }
	  
	  // 
	  public static HuffmanNode huffmanTree(LinkedList<HuffmanNode> countList) {
		  HuffmanNode root = null;
		  // Takes the two lowest nodes and attaches to a null parent node with
		  // the children nodes combined character count 
		  while (countList.size() > 1) {
			  HuffmanNode lowest = HuffmanNode.minFreq(countList);
			  countList.remove(lowest);
			  HuffmanNode secondLowest = HuffmanNode.minFreq(countList);
			  countList.remove(secondLowest);
			  root = new HuffmanNode (null, lowest.getFreq() + secondLowest.getFreq(), secondLowest, lowest);
			  countList.add(root);
		  }
		  return root;
	  }
	  
	  // Method to traverse through Huffman tree and generate Huffman code
	  public static void traversal(HuffmanNode root){
		    // Base case for recursion to stop and print leaf node
		  	// By detecting whether the children nodes are null
		    if(root.getLeft() == null && root.getRight() == null){
		      encoding[(int)root.getTheChar()] = createCode.toString();
		      System.out.println(root.getTheChar() + ":" + root.getFreq() + ":" + createCode.toString());
		      
		    }
		    // Recursively goes through Huffman tree left child that 
		    // is not null, appends 0, and deletes last character in createCode
		    if(root.getLeft() != null){
		      createCode.append('0');
		      traversal(root.getLeft());
		      createCode.deleteCharAt(createCode.length() -1);
		    }
		    
		    if(root.getRight() != null){
		      createCode.append('1');
		      traversal(root.getRight());
		      createCode.deleteCharAt(createCode.length() -1);
		    }
		  }


}
