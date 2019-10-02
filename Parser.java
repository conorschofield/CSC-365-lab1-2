/* Lab1-1 
 * 
 * Dimitri Charitou
 * CPE 365-03
 * Andrew Migler
 */

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

	// Parses a given student data file and returns and ArrayList of Student objects
	public ArrayList<Student> parseStudentFile(String fileName)
	{
		String line;
		Student student;
		String[] attribs = new String[6];
		ArrayList<Student> studentsList = new ArrayList<Student>();
		
		try (FileReader fr = new FileReader(fileName)) 
		{
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) 
			{
				attribs = line.split(",", 6);
				studentsList.add(new Student(attribs[0], attribs[1], Integer.parseInt(attribs[2]), 
					Integer.parseInt(attribs[3]), Integer.parseInt(attribs[4]), 
					Float.parseFloat(attribs[5])));
			}

		//  close buffers
			fr.close();
			br.close();

			return studentsList;
		}
		catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}

	// Parses a given student data file and returns and ArrayList of Student objects
	public ArrayList<Teacher> parseTeacherFile(String fileName)
	{
		String line;
		Teacher teacher;
		String[] attribs = new String[3];
		ArrayList<Teacher> teachersList = new ArrayList<Teacher>();
		
		try (FileReader fr = new FileReader(fileName)) 
		{
			BufferedReader br = new BufferedReader(fr);

			while ((line = br.readLine()) != null) 
			{
				attribs = line.split(", ", 3);
				teachersList.add(new Teacher(attribs[0], attribs[1], Integer.parseInt(attribs[2])));
			}

		//  close buffers
			fr.close();
			br.close();

			return teachersList;
		}
		catch (IOException e) {
			System.out.println(e);
			return null;
		}
	}
}

