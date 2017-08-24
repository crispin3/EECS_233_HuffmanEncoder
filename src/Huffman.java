import java.io.*;
import java.io.IOException;
import java.io.File;


public class Huffman {
	
	public static String huffmanEncoder(String inputFileName, String outputFileName) throws IOException {
		try {
		// Initialize variables
		int oldSize = 0;
		int newSize = 0;
		int inputCount = 0;
		int outputCount = 0;
		File inputFile = new File(inputFileName);
		File outputFile = new File(outputFileName); 
		// Sets up input and output file
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
        FileOutputStream of = new FileOutputStream(outputFile);
        OutputStreamWriter osw = new OutputStreamWriter(of); 
        Writer w = new BufferedWriter(osw);
        // Encodes output file and counts each character in input file
        while ((inputCount = input.read()) != -1) {
        	if (inputCount < 500) {
        		char ch = (char) inputCount;
        		osw.write(HuffmanNode.getEncoder()[(int)ch]);
        		oldSize += 8;
        	}
        }
		w.close();
		// Counts each character in newly create output file
		BufferedReader output = new BufferedReader(new FileReader(outputFile));
        while ((outputCount = output.read()) != -1) { 
			newSize += 1;
		}
        input.close();
        output.close();
	    System.out.println("Total space saved is" + " " + (oldSize - newSize) + " " + "bits");
		return "That's all folks";
		}
		catch (IOException e) {
			return "Danger, something is not work.";			
		}
	}
	
	  public static void main(String[] args) throws IOException {
		  try {
			  HuffmanNode.traversal(HuffmanNode.huffmanTree(HuffmanNode.unorderedLinkedList(HuffmanNode.countFreq("input.txt"))));
			  Huffman.huffmanEncoder("input.txt", "output.txt");
		  }
		  catch(FileNotFoundException e) {
			  System.out.println("Something happened in main argument, but WHAT!");
		  }
	  }
	

	
	
	
	


}
