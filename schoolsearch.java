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
		System.out.println("I[nfo]:");
		System.out.println("C[lassroom]: <number>");
		System.out.println("T[eachers]G[rade]: <grade level>");
		System.out.println("D[ata]: <bus route> | <grade level> | <teacher name> B | G | T");
		System.out.println("Q[uit]");
	}

	// Takes user input and determines which function to execute
	private boolean evaluateInput(ArrayList<Student> studentsList, ArrayList<Teacher> teacherslist) {
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
                R4(splitInput[1], studentsList, teacherslist);
            }
		}
		// R6
		if (input.charAt(0) == 'T') {
		//    R6(splitInput[1], studentsList);
        }

        //R7, R9
        if (input.charAt(0) == 'G') {
        	if (splitInput.length == 2) {
	        	R7(Integer.parseInt(splitInput[1]), studentsList);
	        }
	        else if (splitInput.length == 3) {
	        	if (splitInput[2].charAt(0) == 'L') {
	        		R9(Integer.parseInt(splitInput[1]), 'L', studentsList, teacherslist);
	        	}
	        	else if (splitInput[2].charAt(0) == 'H') {
	        		R9(Integer.parseInt(splitInput[1]), 'H', studentsList, teacherslist);
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

		// NR1
		if (input.charAt(0) == 'C' || input.equals("Classroom")) {
			if (splitInput.length == 2) {
				NR1(studentsList, Integer.parseInt(splitInput[1]));
			}
		}

		// NR3: Command is TG: <number>
		if ((input.charAt(0) == 'T' && input.charAt(1) == 'G') || splitInput[0].equals("TeachersGrade:") 
			|| splitInput[0].equals("TeachersGrade")) {

			if (splitInput.length == 2) {
				NR3(studentsList, teacherslist, Integer.parseInt(splitInput[1]));
			}
		}
		
		// NR5
		if (input.charAt(0) == 'D') {
			if (splitInput.length == 3) {
				if (splitInput[2].charAt(0) == 'B') {
					try {
						busRouteOfStudent(Integer.parseInt(splitInput[1]), studentsList);
						// is an integer!
					} catch (NumberFormatException e) {
						// not an integer!
						System.out.println("");
					}
				}
				if (splitInput[2].charAt(0) == 'G') {
					try {
						gradeLevelOfStudent(Integer.parseInt(splitInput[1]), studentsList);
						// is an integer!
					} catch (NumberFormatException e) {
						// not an integer!
						System.out.println("");
					}
				}
				if (splitInput[2].charAt(0) == 'T') {
					teacherOfStudent(splitInput[1], studentsList, teacherslist);
				}
			}
		}
		return true;
	}

	private void R4(String lastname, ArrayList<Student> students, ArrayList<Teacher> teachers) {
		ArrayList<Student> searchedStudents = new ArrayList<Student>();
		String TLastName = "";
		String TFirstName = "";
		for (int i = 0; i < teachers.size(); i++) {
			if (students.get(i).StLastName.equals(lastname)) {
				searchedStudents.add(students.get(i));
			}
		}
		for (int i = 0; i < searchedStudents.size(); i++) {
			for (int j = 0; j < teachers.size(); j++) {
				if (teachers.get(j).Classroom == searchedStudents.get(i).Classroom) {
					TLastName = teachers.get(j).TLastName;
					TFirstName = teachers.get(j).TFirstName;
				}
			}
			System.out.println(searchedStudents.get(i).StLastName + ", " + searchedStudents.get(i).StFirstName
					+ " " + searchedStudents.get(i).Grade + ", " + searchedStudents.get(i).Classroom + " "
					+ TLastName + ", " + TFirstName);
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

	private void R6(String lastname, ArrayList<Student> students, ArrayList<Teacher> teachers) {
		ArrayList<Student> searchedStudents = new ArrayList<Student>();
		int searchedClassroom = -1;
		String TLastName = "";
		String TFirstName = "";
		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).TLastName.equals(lastname)) {
				searchedClassroom = teachers.get(i).Classroom;
				TLastName = teachers.get(i).TLastName;
				TFirstName = teachers.get(i).TFirstName;
			}
		}
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).Classroom == searchedClassroom) {
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

	private void R9(int grade, char keyword, ArrayList<Student> studentsList, ArrayList<Teacher> teachersList) {
		ArrayList<Student> storedStudent = new ArrayList<Student>();
		ArrayList<Teacher> storedTeacher = new ArrayList<Teacher>();
		for (Student s : studentsList) {
			if (s.Grade == grade) {
				if (storedStudent.isEmpty()) {
					storedStudent.add(s);
					storedTeacher.add(NR2(s.Classroom, teachersList));
				}
				// High
				if (keyword == 'H' && storedStudent.get(0).GPA < s.GPA) {
					storedStudent.set(0, s);
					storedTeacher.set(0, NR2(s.Classroom, teachersList));
				}
				// Low
				if (keyword == 'L' && storedStudent.get(0).GPA > s.GPA) {
					storedStudent.set(0, s);
					storedTeacher.set(0, NR2(s.Classroom, teachersList));
				}
			}
		}
		System.out.printf(storedStudent.get(0).StFirstName + " " + storedStudent.get(0).StLastName + ", %.2f" + ", " + storedTeacher.get(0).TFirstName + " " + storedTeacher.get(0).TLastName + ", " + storedStudent.get(0).Bus + "\n", storedStudent.get(0).GPA);
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

	// Given a classroom number, lists all students assigned to it.
	private void NR1(ArrayList<Student> studentsList, int room) {
		for (Student s : studentsList) {
			if (s.Classroom == room) {
				System.out.println(s.StLastName + ", " + s.StFirstName);
			}
		}
	}

	//Classroom number => Teacher
	private Teacher NR2(int classroomNum, ArrayList<Teacher> teacherlist) {
		Teacher teach = new Teacher("Not found", "not found", 404);
		for (Teacher t : teacherlist){
			if (t.Classroom == classroomNum){
				teach = t;
			}
		}
		return teach;
	}

	// Given a grade, finds all teachers who teach it.
	private void NR3(ArrayList<Student> studentsList, ArrayList<Teacher> teachersList, int grade) {
		ArrayList<Integer> classroom = new ArrayList<Integer>();

		for (Student s : studentsList) {
			if (s.Grade == grade && !classroom.contains(s.Grade)) {
				classroom.add(s.Classroom);
			}
		}

		for (Teacher t : teachersList) {
			if (classroom.contains(t.Classroom)) {
				System.out.println(t.TLastName + ", " + t.TFirstName);
			}
		}
	}

	//List out classrooms, ordered by classroom num, w total # students in each class
	private void NR4(ArrayList<Student> studentsList) {
		ArrayList<Integer> classrooms;
		ArrayList<Integer> classroomSizes;
	}

	// NR5
	private void gradeLevelOfStudent(int gradeLevel, ArrayList<Student> students) {
        double averageGPA = 0;
		double numberOfStudents = 0;
        double totalGPA = 0;
		double min = 1000000000;
		double max = 0;
		double standardDeviation = 0;
        ArrayList<Double> gpaArray = new ArrayList<Double>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).Grade == gradeLevel) {
                double studentGPA = students.get(i).GPA;
                numberOfStudents++;
                totalGPA += studentGPA;
                gpaArray.add(studentGPA);
                if (min > studentGPA) {
                    min = studentGPA;
                }
                if (max < studentGPA) {
                    max = studentGPA;
                }
            }
        }
		if (numberOfStudents != 0) {
			averageGPA = totalGPA / numberOfStudents;
			standardDeviation = standardDeviation(gpaArray, averageGPA, numberOfStudents);
			System.out.println("Average GPA: " + averageGPA +
					"\nMin: " + min +
					"\nMax: " + max +
					"\nStandard Deviation: " + standardDeviation);
		}
		else {
			System.out.println("Average GPA: N/A" +
					"\nMin: N/A" +
					"\nMax: N/A" +
					"\nStandard Deviation: N/A");
		}
    }

	private void teacherOfStudent(String teacherLastName, ArrayList<Student> students, ArrayList<Teacher> teachers) {
        double averageGPA = 0;
		double numberOfStudents = 0;
        double totalGPA = 0;
        double min = 1000000000;
        double max = 0;
        double standardDeviation = 0;
        int searchedClassroom = -1;
        ArrayList<Double> gpaArray = new ArrayList<Double>();

		for (int i = 0; i < teachers.size(); i++) {
			if (teachers.get(i).TLastName.equals(teacherLastName)) {
				searchedClassroom = teachers.get(i).Classroom;
			}
		}
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).Classroom == searchedClassroom) {
				double studentGPA = students.get(i).GPA;
				numberOfStudents++;
				totalGPA += studentGPA;
				gpaArray.add(studentGPA);
				if (min > studentGPA) {
					min = studentGPA;
				}
				if (max < studentGPA) {
					max = studentGPA;
				}
			}
		}
		if (numberOfStudents != 0) {
			averageGPA = totalGPA / numberOfStudents;
			standardDeviation = standardDeviation(gpaArray, averageGPA, numberOfStudents);
			System.out.println("Average GPA: " + averageGPA +
					"\nMin: " + min +
					"\nMax: " + max +
					"\nStandard Deviation: " + standardDeviation);
		}
		else {
			System.out.println("Average GPA: N/A" +
					"\nMin: N/A" +
					"\nMax: N/A" +
					"\nStandard Deviation: N/A");
		}
	}

	private void busRouteOfStudent(int busRoute, ArrayList<Student> students) {
        double averageGPA = 0;
		double numberOfStudents = 0;
        double totalGPA = 0;
		double min = 1000000000;
		double max = 0;
		double standardDeviation = 0;
        ArrayList<Double> gpaArray = new ArrayList<Double>();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).Bus == busRoute) {
                double studentGPA = students.get(i).GPA;
                numberOfStudents++;
                totalGPA += studentGPA;
                gpaArray.add(studentGPA);
                if (min > studentGPA) {
                    min = studentGPA;
                }
                if (max < studentGPA) {
                    max = studentGPA;
                }
            }
        }
		if (numberOfStudents != 0) {
			averageGPA = totalGPA / numberOfStudents;
			standardDeviation = standardDeviation(gpaArray, averageGPA, numberOfStudents);
			System.out.println("Average GPA: " + averageGPA +
					"\nMin: " + min +
					"\nMax: " + max +
					"\nStandard Deviation: " + standardDeviation);
		}
		else {
			System.out.println("Average GPA: N/A" +
					"\nMin: N/A" +
					"\nMax: N/A" +
					"\nStandard Deviation: N/A");
		}
	}

    private double standardDeviation(ArrayList<Double> gpas, double mean, double total) {
        double sumOfSquardDifferences = 0;

        for (int i = 0; i < gpas.size(); i++) {
            sumOfSquardDifferences += (gpas.get(i) - mean) * (gpas.get(i) - mean);
        }
        return Math.sqrt(sumOfSquardDifferences/total);
    }

	public static void main(String[] args) {
		// variable which while loop evaluates
		boolean loopAgain = true;

		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		Parser parser = new Parser();
		schoolsearch application = new schoolsearch();

		// obtains array of Student objects
		students = parser.parseStudentFile("list.txt");
		teachers = parser.parseTeacherFile("teachers.txt");

		// performs io error checking for students.txt
		if (students == null || teachers == null) {
			System.out.println("Data file parsing error");
			return;
		}

		// main application loop
		while (loopAgain) {
			application.printMenu();
			loopAgain = application.evaluateInput(students, teachers);
		}
	}
}
