package project4;

import java.util.ArrayList;

/** This class represents the main class as a program named WayFinder. It is responsible
 * for taking in a command line of values, verifying that they are valid, then 
 * constructing it as an integer array named puzzle. It is then responsible for calling 
 * this array to the Puzzle class in order to solve the puzzle and print out the
 * solutions. It also contains certain variables to execute the required features of 
 * this program. For example, the string answer stores the printed paths; the arraylist
 * paths store each answer that is found in order to track number of answers.
 * 
 * @author Anh Tran 
 *
 */
public class WayFinder {

	/** This is the main method. It is responsible for intaking the commandline argument:
	 * a series of number values - and verifying that all values are legal. It will then 
	 * convert the parameter args to an integer array called puzzle, which will then be 
	 * used as a parameter to the findPath method under the Puzzle class.
	 * 
	 * @param args - command line argument is a series of number values which will be used
	 * as the puzzle values. 
	 */
	public static void main(String[] args) {

		// Verify that command line is not empty
		// Terminate program if empty
		if (args.length == 0) {
			System.err.println("WayFinder expects an argument.");
			System.exit(1);
		}

		// construct a new integer array to store command line arguments as ints
		int[] puzzle = new int[args.length];

		// test whether command line argument are integers
		try {
			// Convert all command line values to integers and store in puzzle array
			for (int i = 0; i < args.length; i++) {
				puzzle[i] = Integer.parseInt(args[i]);
			}

			// exception if any value is not an integer, then exit in stream 1
		} catch (Exception e) {
			System.err.println("WayFinder expects Integers only");
			System.exit(1);
		}

		// if all values are integers, then test whether command line 
		// arguments are legal/ valid
		try {

			// Verify that last puzzle value is 0 - if not then terminate
			if (puzzle[puzzle.length-1] != 0) {
				System.err.println("WayFinder expects last value to be 0");
				System.exit(1);
			}

			// Verify that puzzle values are not out of range
			for (int i = 0; i < puzzle.length; i++) {
				// verify puzzle values not larger than 100
				if (puzzle[i] > 99 ) {
					System.err.println("WayFinder values have to be less than 100");
					System.exit(1);
				}
				// verify puzzle values are not negative
				if (puzzle[i] < 0) {
					System.err.println("WayFinder values must not be negative");
					System.exit(1);
				}
			}
		
			// if wayfinder values are invalid in any other way
		} catch (Exception e) {
			System.err.println("Wayfinder expects a valid argument");
			System.exit(1);
		}
		
		// new string serves as format builder for answer/ found path
		String answer = new String();
		
		// new arrayList to store paths. Everytime a path is found, it will be added here.
		// size of this arraylist will signify how many solutions there are.
		ArrayList<String> paths = new ArrayList<>();

		// call recursive method
		Puzzle.findPath(0, puzzle, paths, answer);
		
		// Solution Count
		int solutionCount = paths.size();
		
		// Print solution count 
		// if there are no solutions
		if (solutionCount == 0) {
			System.out.println("No way through this puzzle.");
		}
		
		// if there is one solution
		if (solutionCount == 1) {
			System.out.println("There is 1 way through the puzzle.");
		}
		
		// if there is more than one solution
		if (solutionCount > 1) {
			System.out.println("There are " + solutionCount + " ways through the puzzle.");
		}

	}
}
