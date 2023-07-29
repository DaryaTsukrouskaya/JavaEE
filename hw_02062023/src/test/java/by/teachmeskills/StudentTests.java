package by.teachmeskills;

import by.teachmeskills.studentTest.Sex;
import by.teachmeskills.studentTest.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StudentTests {
    private static Student student1;
    private static Student student2;
    private static Student student3;

    private static List<Student> actual;
    private static List<Student> actualBySexMale;
    private static List<Student> actualBySexFemale;
    private static int actualCountByFemaleSex;
    private static int actualStudentsCount;
    private static int actualStudentsAgeSum;
    private static int actualFemaleStudentsAgeSum;
    private static int actualStudentsAverageAge;
    private static int actualFemaleAverage;


    @BeforeAll
    public static void setUp() {
        student1 = new Student("Ivan", 17, Sex.MALE);
        student2 = new Student("Anna", 19, Sex.FEMALE);
        student3 = new Student("Polina", 18, Sex.FEMALE);

        actual = new ArrayList<>();
        actual.add(student1);
        actual.add(student2);
        actual.add(student3);

        actualBySexMale = new ArrayList<>();
        actualBySexFemale = new ArrayList<>();
        actualBySexMale.add(student1);
        actualBySexFemale.add(student2);
        actualBySexFemale.add(student3);
        actualCountByFemaleSex = actualBySexFemale.size();
        actualStudentsCount = actual.size();
        actualStudentsAgeSum = student1.getAge() + student2.getAge() + student3.getAge();
        actualFemaleStudentsAgeSum = student2.getAge() + student3.getAge();
        actualStudentsAverageAge = actualStudentsAgeSum / actualStudentsCount;
        actualFemaleAverage = actualFemaleStudentsAgeSum / actualCountByFemaleSex;
    }

    @Test
    public void checkAllStudentsReturned() {
        assertEquals(Student.getAllStudents(), actual);
    }

    @Test
    public void checkAllStudentsReturned_NotNull() {
        assertNotNull(Student.getAllStudents());
    }

    @Test
    public void getAllUsers_MALE() {
        assertEquals(Student.getAllStudentsBySex(Sex.MALE), actualBySexMale);
    }


    @Test
    public void checkStudentsCountWithSex() {
        assertEquals(Student.getStudentsCountWithSex(Sex.FEMALE), actualCountByFemaleSex);
    }

    @Test
    public void checkAllStudentsCount() {
        assertEquals(Student.getStudentsCount(), actualStudentsCount);
    }

    @Test
    public void checkSumOfAllStudentsAge() {
        assertEquals(Student.getSumOfAllStudentsAge(), actualStudentsAgeSum);
    }

    @Test
    public void checkSumOfFemaleStudentsAge() {
        assertEquals(Student.getSumOfAllStudentsAgeBySex(Sex.FEMALE), actualFemaleStudentsAgeSum);
    }

    @Test
    public void checkAverageAgeOfAllStudents() {
        assertEquals(Student.getAverageAgeOfAllStudents(), actualStudentsAverageAge);
    }

    @Test
    public void checkAverageOfStudentsBySex() {
        assertEquals(Student.getAverageOfAllStudentsBySex(Sex.FEMALE), actualFemaleAverage);
    }

}
