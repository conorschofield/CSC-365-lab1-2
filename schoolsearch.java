/* schoolsearch.java
 *
 * Dimitri Charitou, Ellen Liu, Connor Schofield
 * Andrew Migler
 * CPE 365-03
 */

import java.util.ArrayList;
import java.util.Scanner;

class schoolsearch {

	// prints the different input options
	private void printMenu() {

		System.out.println("\nS[tudent]: <lastname> [B[us]]");
		System.out.println("T[eacher]: <lastname>");
		System.out.println("B[us]: <number>");
		System.out.println("G[rade]: <number> [H[igh]|L[ow]]");
		System.out.println("A[verage]: <number>");
		System.out.println("I[nfo]");
		System.out.println("Q[uit]");
	}

	// Takes user input and determines which function to execute
	private boolean evaluateInput(ArrayList<Student> studentsList) {
		System.out.print("\nPlease enter option: ");
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();
		String[] splitInput = input.split(" ");

        // check if first input is S
		if (input.charAt(0) == 'S') {
		    // check if the optional B is used
            if (splitInput.length > 2 && splitInput[2].charAt(0) == 'B') {
                // R5
                R5(splitInput[1], studentsList);
            }
            else {
                // R4
                R4(splitInput[1], studentsList);
            }
		}
		// R6
		if (input.charAt(0) == 'T') {
		    R6(splitInput[1], studentsList);
        }

        //R7 and R9
        if (input.charAt(0) == 'G') {
        	if (splitInput.length == 2) {
	        	R7(Integer.parseInt(splitInput[1]), studentsList);
	        }
	        else if (splitInput.length == 3) {
	        	if (splitInput[2].charAt(0) == 'L') {
	        		R9(Integer.parseInt(splitInput[1]), 'L', studentsList);
	        	}
	        	else if (splitInput[2].charAt(0) == 'H') {
	        		R9(Integer.parseInt(splitInput[1]), 'H', studentsList);
	        	}
	        }
        }
		// R8
		if (input.charAt(0) == 'B') {

			R8(Integer.parseInt(splitInput[1]), studentsList);
		}
		// R10
		if (input.charAt(0) == 'A') {
			R10(Integer.parseInt(splitInput[1]), studentsList);
		}
		// R11
		if (input.charAt(0) == 'I') {
			R11(studentsList);
		}
		// R12
		if (input.equals("Q") || input.equals("Quit")) {
			return false;
		}
		return true;
	}

	private void R4(String lastname, ArrayList<Student> students) {
		ArrayList<Student> searchedStudents = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).StLastName.equals(lastname)) {
				searchedStudents.add(students.get(i));
			}
		}
		for (int i = 0; i < searchedStudents.size(); i++) {
			System.out.println(searchedStudents.get(i).StLastName + ", " + searchedStudents.get(i).StFirstName
					+ " " + searchedStudents.get(i).Grade + ", " + searchedStudents.get(i).Classroom + " "
					+ searchedStudents.get(i).TLastName + ", " + searchedStudents.get(i).TFirstName);
		}
	}

	private void R5(String lastname, ArrayList<Student> students) {
		ArrayList<Student> searchedStudents = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).StLastName.equals(lastname)) {
				searchedStudents.add(students.get(i));
			}
		}
		for (int i = 0; i < searchedStudents.size(); i++) {
			System.out.println(searchedStudents.get(i).StLastName + ", " + searchedStudents.get(i).StFirstName
					+ ", " + searchedStudents.get(i).Bus);
		}
	}

	private void R6(String lastname, ArrayList<Student> students) {
		ArrayList<Student> searchedStudents = new ArrayList<Student>();
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).TLastName.equals(lastname)) {
				searchedStudents.add(students.get(i));
			}
		}
		for (int i = 0; i < searchedStudents.size(); i++) {
			System.out.println(searchedStudents.get(i).StLastName + ", " + searchedStudents.get(i).StFirstName);
		}
	}

	// prints students with certain GPA
	private void R7(int grade, ArrayList<Student> studentsList) {

		Student student;
		for (int i = 0; i < studentsList.size(); i++) {
			student = studentsList.get(i);
			if (student.Grade == grade) {
				System.out.println(student.StLastName + ", " + student.StFirstName);
			}
		}
	}

	private void R8(int bus, ArrayList<Student> studentsList) {
		for (Student s : studentsList) {
			if (s.Bus == bus) {
				System.out.println(s.StFirstName + ", " + s.StLastName + ", " + s.Grade + ", " + s.Classroom);
			}
		}
	}

	private void R9(int grade, char keyword, ArrayList<Student> studentsList) {
		ArrayList<Student> storedStudent = new ArrayList<Student>();
		for (Student s : studentsList) {
			if (s.Grade == grade) {
				if (storedStudent.isEmpty()) {
					storedStudent.add(s);
				}
				// High
				if (keyword == 'H' && storedStudent.get(0).GPA < s.GPA) {
					storedStudent.set(0, s);
				}
				// Low
				if (keyword == 'L' && storedStudent.get(0).GPA > s.GPA) {
					storedStudent.set(0, s);
				}
			}
		}
		System.out.printf(storedStudent.get(0).StFirstName + " " + storedStudent.get(0).StLastName + ", %.2f" + ", " + storedStudent.get(0).TFirstName + " " + storedStudent.get(0).TLastName + ", " + storedStudent.get(0).Bus + "\n", storedStudent.get(0).GPA);
	}

	private void R10(int number, ArrayList<Student> studentsList) {
		double total = 0;
		int numStudents = 0;

		for (int i = 0; i < studentsList.size(); i++) {
			if (studentsList.get(i).Grade == number) {
				total += studentsList.get(i).GPA;
				numStudents++;
			}
		}

		if (numStudents == 0) {
			System.out.println(number + ": 0.00");
		}
		else {
			System.out.printf(number + ": %.2f\n", total / numStudents);
		}
	}

	private void R11(ArrayList<Student> studentsList) {
		int[] gradesArray = new int[7];

		for (int i = 0; i < studentsList.size(); i++) {
			// performs error checking
			if (studentsList.get(i).Grade > 6) {
				System.out.println("Grade out of range");
				return;
			} else {
				gradesArray[studentsList.get(i).Grade]++;
			}
		}

		for (int i = 0; i < 7; i++) {
			System.out.println(i + ": " + gradesArray[i]);
		}
	}

	public static void main(String[] args) {
		// variable which while loop evaluates
		boolean loopAgain = true;

		ArrayList<Student> students = new ArrayList<Student>();
		Parser parser = new Parser();
		schoolsearch application = new schoolsearch();

		// obtains array of Student objects
		students = parser.parseFile("students.txt");

		// performs io error checking for students.txt
		if (students == null) {
			return;
		}

		// main application loop
		while (loopAgain) {
			application.printMenu();
			loopAgain = application.evaluateInput(students);
		}
	}
}