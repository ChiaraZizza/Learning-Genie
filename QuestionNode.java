import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class QuestionNode implements DecisionNode {
	
	public String question;
	public DecisionNode left;
	public DecisionNode right;

	/** Constructs a question node
	 * @param str a question
	 * @param l a decision node
	 * @param r a decision node
	 */
	public QuestionNode(String str, DecisionNode l, DecisionNode r) {
		question = str;
		left = l;
		right = r;
	}
	
	@Override
	/** Counts the number of guess nodes
	 * @return count the number of guess nodes
	 */
	public int countObjects() {
		int counter = 0;
		if(this.left != null) {
			counter += this.left.countObjects();
		}
		
		if(this.right != null) {
			counter += this.right.countObjects();
		} else {
			return 0;
		}
		return counter;
	}

	@Override
	/** Performs the guessing game starting at this node using the given 
	 * Scanner object to query the user for input; returns an updated node
	 * that is the result of any knowledge learned during the game
	 * @param in the scanner for user input
	 */
	public DecisionNode guess(Scanner in) {
		System.out.print(this.question.substring(1, question.length()) + " ");
		String response = in.next();
		
		if(response.equalsIgnoreCase("Yes")) {
			left = left.guess(in);
			return this;
		} else if(response.equalsIgnoreCase("No")) {
			right = right.guess(in);
			return this;
		} else {
			System.out.println("Invalid response.");
			return this.guess(in);
		}
	}

	@Override
	/** Writes this node in the serialized format described above to the given file
	 * represented by a FileWriter object
	 */
	public void write(FileWriter out) throws IOException {
		out.write(this.question);
		out.write("\n");
		left.write(out);
		right.write(out);
	}
}
