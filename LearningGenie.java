import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class LearningGenie {

	public static void main(String[] args) throws IOException {
		DecisionTree tree;
		File file = new File("EDecisionTree.txt");
		if(Files.exists(file.toPath())) {
			if(file.length() == 0) {
				System.out.println("Please give me guess to start with in the file.");
				return;
			} else {
				tree = new DecisionTree(file);
			}
		} else {
			tree = new DecisionTree();
		}
		
		Scanner scan = new Scanner(System.in);
		
		/* Genie introduction */
		System.out.println("I am the learning genie!");
		System.out.println("I can figure out whatever you are thinking of by asking questions.");
		System.out.print("I know " + tree.countObjects());
		if(tree.countObjects() == 1) {
			System.out.println(" thing.\n");
		} else {
			System.out.println(" things.\n");
		}
		
		/* Write to file */
		FileWriter fw = new FileWriter(file);
		tree.guess(scan);
		tree.write(fw);
		
		fw.close();
		scan.close();
	}
}
