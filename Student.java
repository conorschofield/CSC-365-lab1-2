public class Student {
    String StLastName;
    String StFirstName;
    int Grade;
    int Classroom;
    int Bus;
    double GPA;

    public Student (String StlastName, String StFirstName, int Grade, int Classroom,
                    int Bus, double GPA) {
        this.StLastName = StlastName;
        this.StFirstName = StFirstName;
        this.Grade = Grade;
        this.Classroom = Classroom;
        this.Bus = Bus;
        this.GPA = GPA;
    }
}