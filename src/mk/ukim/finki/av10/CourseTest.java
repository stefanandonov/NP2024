package mk.ukim.finki.av10;



import java.util.*;
import java.util.stream.Collectors;

class Student {
    String idNumber;
    String name;

    int firstMidterm;

    int secondMidterm;

    int labs;

    public Student(String idNumber, String name) {
        this.idNumber = idNumber;
        this.name = name;
    }

    double totalPoints () {
        return firstMidterm*0.45 + secondMidterm*0.45 + labs;
    }

    int grade() {
        double points = totalPoints();

        if (points >= 90) {
            return 10; // Grade 10 for points 90 and above
        }
        if (points >= 80) {
            return 9; // Grade 9 for points 80-89
        }
        if (points >= 70) {
            return 8; // Grade 8 for points 70-79
        }
        if (points >= 60) {
            return 7; // Grade 7 for points 60-69
        }
        if (points >= 50) {
            return 6; // Grade 6 for points 50-59
        }
        return 5; // Grade 5 for points below 50
    }

    @Override
    public String toString() {
        return String.format("ID: %s Name: %s First midterm: %d Second midterm %d Labs: %d Summary points: %.2f Grade: %d", idNumber, name, firstMidterm, secondMidterm, labs, totalPoints(), grade());
    }
}


class AdvancedProgrammingCourse {
    Map<String, Student> studentsById = new HashMap<>();


    public void addStudent(Student student) {
        studentsById.put(student.idNumber, student);
    }

    public void updateStudent(String idNumber, String activity, int points) {
        Student s = studentsById.get(idNumber);

        switch (activity){
            case "midterm1":
                s.firstMidterm=points;
                break;
            case "midterm2":
                s.secondMidterm=points;
                break;
            case "labs":
                s.labs = points;
        }
    }

    public List<Student> getFirstNStudents(int n) {
        return studentsById.values().stream()
                .filter(s -> s.totalPoints()>=50)
                .sorted(Comparator.comparing(Student::totalPoints).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    Map<Integer, Long> getGradeDistribution() {
        return studentsById.values().stream().collect(Collectors.groupingBy(
                Student::grade,
                Collectors.counting()
        ));

    }

    public void printStatistics() {
        DoubleSummaryStatistics dss = studentsById.values().stream()
                .filter(s -> s.grade()>5)
                .mapToDouble(Student::totalPoints)
                .summaryStatistics();

        System.out.printf("Count: %d Min: %.2f Average: %.2f Max: %.2f\n%n", dss.getCount(), dss.getMin(), dss.getAverage(), dss.getMax());
    }
}


public class CourseTest {

    public static void printStudents(List<Student> students) {
        students.forEach(System.out::println);
    }

    public static void printMap(Map<Integer, Long> map) {
        map.forEach((k, v) -> System.out.printf("%d -> %d%n", k, v));
    }

    public static void main(String[] args) {
        AdvancedProgrammingCourse advancedProgrammingCourse = new AdvancedProgrammingCourse();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split("\\s+");

            String command = parts[0];

            if (command.equals("addStudent")) {
                String id = parts[1];
                String name = parts[2];
                advancedProgrammingCourse.addStudent(new Student(id, name));
            } else if (command.equals("updateStudent")) {
                String idNumber = parts[1];
                String activity = parts[2];
                int points = Integer.parseInt(parts[3]);
                advancedProgrammingCourse.updateStudent(idNumber, activity, points);
            } else if (command.equals("getFirstNStudents")) {
                int n = Integer.parseInt(parts[1]);
                printStudents(advancedProgrammingCourse.getFirstNStudents(n));
            } else if (command.equals("getGradeDistribution")) {
                printMap(advancedProgrammingCourse.getGradeDistribution());
            } else {
                advancedProgrammingCourse.printStatistics();
            }
        }
    }
}
