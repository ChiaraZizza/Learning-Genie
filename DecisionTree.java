import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DecisionTree {
	
	DecisionNode root;
	
	/** Constructs a decision tree with an initial guess node containing Dog
	 */
	public DecisionTree() {
		root = new GuessNode("Dog");
	}
	
	/** Constructs a decision tree stored in a serialized form in the given file
	 * @param file the file where the decision tree is formed
	 */
	public DecisionTree(File file) throws IOException {
		Scanner input = new Scanner(file);
		root = DecisionTreeH(input);
	}

	/** Reads an existing file and constructs the appropriate decision tree
	 * @param scan a scanner
	 * @return a decision tree
	 */
	public DecisionNode DecisionTreeH(Scanner scan) {
		while(scan.hasNext()) {
			String line = scan.nextLine();

			if(line.charAt(0) == '#') {
				return new QuestionNode(line, DecisionTreeH(scan), DecisionTreeH(scan));
			} else {
				return new GuessNode(line);
			}
		}
		return null;
	}

	/** Counts the number of guess nodes in a decision tree
	 * @return count the number of guess nodes
	 */
	public int countObjects() {
		if(root == null) {
			return 0;
		} else {
			return root.countObjects();
		}
	}
	
	/** Performs the guessing game starting at the root of this decision tree using the given
	 * Scanner object to query the user for input and modifies the decision tree if the genie
	 * learns any additional information in playing the game
	 * @param in the scanner for user input
	 */
	public void guess(Scanner in) {
		boolean proceed = true;
		
		while(proceed) {		
			System.out.println("Think of an object!");
			root = root.guess(in);	
			proceed = wantContinue(in);
		}
	}
	
	/** Asks user if he or she wishes to continue playing
	 * @param in a scanner for user input
	 * @return true if user wishes to continue; false otherwise
	 */
	private boolean wantContinue(Scanner in) {
		while(true) {
			System.out.print("Do you want to continue? ");
			String response = in.next();
			
			if (response.equalsIgnoreCase("No")) {
				return false;
			} else if(response.equalsIgnoreCase("Yes")) {
				System.out.println(); //separates different games into different chunks of text 
				return true;
			} else {
				System.out.println("Invalid response.");
			}
		}
	}
	
	/** Writes this decision tree in the serialized format to the given file
	 * @param out a stream of characters in a file
	 * @throws IOException
	 */
	public void write(FileWriter out) throws IOException {
		root.write(out);
		out.close(); //close stream to flush buffer to file
	}
}