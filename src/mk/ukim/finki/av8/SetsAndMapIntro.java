package mk.ukim.finki.av8;


import java.util.Comparator;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

class Student implements Comparable<Student> {
    String index;
    double averageGrade;

    public Student(String index, double averageGrade) {
        this.index = index;
        this.averageGrade = averageGrade;
    }

    public String getIndex() {
        return index;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.averageGrade, averageGrade) == 0 && Objects.equals(index, student.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, averageGrade);
    }

    @Override
    public String toString() {
        return "Student{" +
                "index='" + index + '\'' +
                ", averageGrade=" + averageGrade +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (Double.compare(this.averageGrade, o.averageGrade)==0){
            return this.index.compareTo(o.index);
        } else {
            return Double.compare(this.averageGrade, o.averageGrade);
        }
    }
}

public class SetsAndMapIntro {
    public static void main(String[] args) {

//        HashSet<String> stringHashSet = new HashSet<>();
//
//        stringHashSet.add("Stefan");
//        stringHashSet.add("Andonov");
//        stringHashSet.add("NP");
//        stringHashSet.add("napredno");
//        stringHashSet.add("napredno");
//
//        //O(N)
//        System.out.println(stringHashSet);
//
//        //O(1) - less complex than searching with for and if
//        System.out.println(stringHashSet.contains("Stefan"));
//
//        HashSet<Student> studentHashSet = new HashSet<>();
//
//        studentHashSet.add(new Student("151020", 9.0));
//        studentHashSet.add(new Student("221222", 10.0));
//        studentHashSet.add(new Student("191122", 6.5));
//
//        System.out.println(studentHashSet);
//
//        System.out.println(studentHashSet.contains(new Student("191122", 6.5)));

//        TreeSet

//        TreeSet<String> stringTreeSet = new TreeSet<>(Comparator.reverseOrder());
//
//        //O(logn)
//        stringTreeSet.add("Stefan");
//        stringTreeSet.add("Andonov");
//        stringTreeSet.add("NP");
//        stringTreeSet.add("napredno");
//
//        // O(nlogn)
//        System.out.println(stringTreeSet);
//
//        //O(logn)
//        System.out.println(stringTreeSet.contains("Stefan"));
//
        TreeSet<Student> studentTreeSet = new TreeSet<>(Comparator.comparing(Student::getAverageGrade).reversed().thenComparing(Student::getIndex).reversed());
//
//        studentTreeSet.add(new Student("151020", 9.0));
//        studentTreeSet.add(new Student("221222", 10.0));
//        studentTreeSet.add(new Student("191122", 6.5));
//
//        System.out.println(studentTreeSet);


        //HashMap

//        HashMap<String, String> hashMap = new HashMap<>();
//
//        //O(1)
//        hashMap.put("Stefan", "Stefan");
//        hashMap.put("Andonov", "Stefan");
//        hashMap.put("NP", "Stefan");
//        hashMap.put("Stefan", "NP");
//
//        //O(n)
//        System.out.println(hashMap);
//
//        //O(1)
//        System.out.println(hashMap.getOrDefault("Kola", "Traktor"));
//        System.out.println(hashMap.values());
//        System.out.println(hashMap.keySet());


        //TreeMap

        TreeMap<String, Student> treeMap = new TreeMap<>();


        treeMap.put("151030", new Student("151030", 9.0));
        treeMap.put("151030", new Student("151021", 6.5));

        System.out.println(treeMap);

    }
}
