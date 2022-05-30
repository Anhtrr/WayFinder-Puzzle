package project4;

import java.util.*;

/** This class represents the WayFinder puzzle itself. It is responsible for recursively 
 * solving the array puzzle, that is passed into it's only method: findPath.
 * 
 * @author Anh Tran 
 */
public class Puzzle {

	/** This method is responsible for recursively solving the puzzle. It does not return anything
	 * but it will print an answer if any are found. It takes in several parameters in order to 
	 * execute required features of the WayFinder program. 
	 *  
	 * @param index - this parameter is an integer and it represents the index in the array puzzle, 
	 * that the recursive function points to and executes with. This integer will start at 0, 
	 * passed through from the main method. It will change throughout this recursive method 
	 * accordingly to the direction that the program decides to move. 
	 *  
	 * @param puzzle - this parameter is an array of integer values, that will be constructed from 
	 * the main method. Its length will be used as conditions for the recursive method to execute, 
	 * while it's values will be used for both conditions as well as printing out the answers. This
	 * array will be originally constructed in the main and will not change from this recursive 
	 * method. It will only be used for its values and as conditions for this recursive method's 
	 * execution.
	 * 
	 * @param paths - this parameter is a string arraylist that will store every answer, each time 
	 * one is found. The size of this arraylist represents the number of solutions there are to the
	 * puzzle. This arraylist will be originally constructed in the main method but will be appended
	 * to from this recursive method.
	 * 
	 * @param answer - this parameter is a string, named answer. It is responsible for storing the 
	 * puzzle's answer lines. For each iteration that is a valid move, it will append a new answer 
	 * line. Ultimately, if the method reaches the last index of the puzzle, the answer string will
	 * store the complete answer. If a path is reached, answer will be printed and added to the 
	 * arraylist paths. This string will be originally constructed in the main but will be appended 
	 * to from this recursive method.
	 */
	public static void findPath(int index, int[] puzzle, ArrayList<String> paths, String answer) {

		// base case - when index reaches last index of puzzle
		if (index == puzzle.length - 1) {

			// if puzzle is only given one argument of 0.
			if (puzzle.length == 1) {
				paths.add(answer);
				System.out.println("[ 0 ]");
			} else {
				paths.add(answer);
				System.out.println(answer);
			}

			// end recursion if solution is found
			return;
		}

		// format answer line if program moves right
		StringBuilder right = new StringBuilder();
		// iterate through puzzle values to format line if puzzle moves right
		for (int i = 0; i < puzzle.length; i++) {
			// if recursive function index is the same as position i
			if (index == i) {
				// first index
				if (i == 0) {
					// format if number is two digits
					if (puzzle[i] > 9) {
						right.append("[" + puzzle[i] + "R,");
					}
					// format if number is one digit
					else {
						right.append("[ " + puzzle[i] + "R,");
					}
				}
				// middle index
				else {
					// two digits
					if (puzzle[i] > 9) {
						right.append(" " + puzzle[i] + "R,");
					}
					// one digit
					else {
						right.append("  " + puzzle[i] + "R,");
					}
				}
			}
			// if position i does not match recursive function index
			else {
				// first index
				if (i == 0) {
					// two digits
					if (puzzle[i] > 9) {
						right.append("[" + puzzle[i] + " ,");
					}
					// one digit
					else {
						right.append("[ " + puzzle[i] + " ,");
					}
				}
				// last index
				if (i == puzzle.length - 1) {
					right.append("  " + puzzle[i] + " ]");
				}
				// middle index
				else if (i > 0) {
					// two digits
					if (puzzle[i] > 9) {
						right.append(" " + puzzle[i] + " ,");
					}
					// one digit
					else {
						right.append("  " + puzzle[i] + " ,");
					}
				}
			}
		}

		// format answer line if program moves left
		StringBuilder left = new StringBuilder();
		// iterate through puzzle values to format line if puzzle moves left
		for (int i = 0; i < puzzle.length; i++) {
			// if recursive function index is the same as position i
			if (index == i) {
				// two digits
				if (puzzle[i] > 9) {
					left.append(" " + puzzle[i] + "L,");
				}
				// one digit
				else {
					left.append("  " + puzzle[i] + "L,");
				}
			}

			// if position i does not match recursive function index
			else {
				// first index
				if (i == 0) {
					// two digits
					if (puzzle[i] > 9) {
						left.append("[" + puzzle[i] + " ,");
					}
					// one digit
					else {
						left.append("[ " + puzzle[0] + " ,");
					}
				}
				// last index
				if (i == puzzle.length - 1) {
					// last index always 1 digit
					left.append("  " + puzzle[i] + " ]");
				}
				// middle index
				else if (i > 0) {
					// two digits
					if (puzzle[i] > 9) {
						left.append(" " + puzzle[i] + " ,");
					}
					// one digit
					else {
						left.append("  " + puzzle[i] + " ,");
					}
				}
			}
		}

		// next index if program decides to go right
		int indexR = index + puzzle[index];

		// recursive call right
		if (indexR <= puzzle.length - 1) {

			// Check if answer contains line of previous iteration(s) - avoid infinite loop
			if (answer.contains(right) == false && answer.contains(left) == false) {

				// create new string to change answer for next recursive calls
				String answerR = new String();
				answerR = answer + right + "\n";

				// call right recursive with new string
				findPath(indexR, puzzle, paths, answerR);

			}
		}

		// next index if program decides to go left
		int indexL = index - puzzle[index];

		// recursive call left
		if (indexL > 0) {

			// Check if answer contains line of previous iteration(s) - avoid infinite loop
			if (answer.contains(right) == false && answer.contains(left) == false) {

				// create new string to change answer for next recursive calls
				String answerL = new String();
				answerL = answer + left + "\n";

				// call right recursive with new string
				findPath(indexL, puzzle, paths, answerL);

			}
		}
		// end recursive call if this point is reached
		return;
	}
}
