import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GuessNode implements DecisionNode {

	public String str;

	/** Constructs a guess node containing the guess str
	 * @param str a guess
	 */
	public GuessNode(String str) {
		this.str = str;
	}

	@Override
	/** Counts the number of guess nodes
	 * @return count the number of guess nodes
	 */
	public int countObjects() {
		return 1;
	}

	@Override
	/** Performs the guessing game starting at this node using the given 
	 * Scanner object to query the user for input; returns an updated node
	 * that is the result of any knowledge learned during the game
	 * @param in the scanner for user input
	 */
	public DecisionNode guess(Scanner in) { 
		System.out.print("Are you thinking of " + this.str + "? ");
		
		String response = in.next();
		
		if(response.equalsIgnoreCase("Yes")) {
			System.out.println("Excellent, thanks!");
			return this;
		} else if(response.equalsIgnoreCase("No")){
			System.out.println("Oh no, I was wrong!");
			System.out.print("What animal were you thinking of? ");
			String correctAnswer = in.next();
			System.out.println("What is a yes/no question that distinguishes a "
					+ this.str + " from a " + correctAnswer + "?");
			System.out.print("(Yes corresponds to " + correctAnswer +
					"; No corresponds to " + this.str + ") ");

			@SuppressWarnings("resource")
			Scanner phrase = new Scanner(System.in);
			String question = phrase.nextLine();

			QuestionNode cur = new QuestionNode("#" + question, new GuessNode(correctAnswer),
					new GuessNode(this.str));

			return cur;
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
		out.write(this.str);
		out.write("\n");
	}
}
