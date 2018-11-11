package mbabski.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SemesterTest {

    Semester sem;

    @BeforeAll
    public static void setUp() {
        WriteSubjects.writeToFile();
        DataBase instance = DataBase.getInstance();
        instance.loadCourses();
        instance.loadSubjects();
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 53, 145, 241, 312})
    void addCollisionFreeCourse(int courseNumber) {
        sem = new Semester(4);
        sem.isCollision(courseNumber);
        assertEquals(DataBase.getInstance().getSubjects().get(courseNumber).getSignature(), sem.getChosenCourses()
                .get(0).getSignature());
    }

    @Test
    void tryAddWhenCollision() {
        sem = new Semester(4);
        sem.isCollision(20);
        sem.isCollision(53);
        assertEquals(1, sem.getChosenCourses().size());
    }

    @Test
    void checkIfNotInBasket() {
        sem = new Semester(4);
        int[] courses = {58, 104, 192, 222, 294, 311};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertFalse(sem.checkIfAdded("22212"));
    }

    @Test
    void checkIfInBasket() {
        DataBase.getInstance().loadSubjects();
        DataBase.getInstance().loadCourses();
        sem = new Semester(3);
        int[] courses = {33, 78, 100, 199, 214, 250};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertTrue(sem.checkIfAdded("22349"));
    }

    @Test
    void ectsInBasketCounter() {
        sem = new Semester(3);
        int[] courses = {35, 72, 110, 149, 211, 244};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(18.0, sem.countECTSInBasket(sem.getChosenCourses()));
    }

    @Test
    void validateCompCoursesSem1() {
        sem = new Semester(1);
        int[] courses = {15, 78, 100, 121};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(2, sem.checkForCompulsoryCourses(sem.getSemesterNo()));
    }

    @Test
    void validateCompCoursesSem2() {
        sem = new Semester(2);
        int[] courses = {18, 24, 55, 88, 124, 140};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(3, sem.checkForCompulsoryCourses(sem.getSemesterNo()));
    }

    @Test
    void overOneFieldCourseAddedSem1() {
        sem = new Semester(1);
        int[] courses = {24, 55, 88, 124, 140, 170};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(0, sem.checkForFieldCourses(sem.getSemesterNo()));
    }

    @Test
    void noFieldCourseAddedSem1() {
        sem = new Semester(1);
        int[] courses = {24, 55, 99, 211, 324};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(Courses.FIELD_TO_CHOOSE_SEM1 - 0, sem.checkForFieldCourses(sem.getSemesterNo()));
    }

    @Test
    void twoFieldCourseAddedSem2() {
        sem = new Semester(2);
        int[] courses = {24, 33, 63, 66, 111, 135, 170, 222};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(Courses.FIELD_TO_CHOOSE_SEM23 - 2, sem.checkForFieldCourses(sem.getSemesterNo()));
    }

    @Test
    void sevenFieldCoursesAddedSem3() {
        sem = new Semester(3);
        int[] courses = {36, 48, 60, 111, 135, 142, 152, 156};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(0, sem.checkForFieldCourses(sem.getSemesterNo()));
    }

    @Test
    void sevenFieldCoursesAddedSem4() {
        sem = new Semester(4);
        int[] courses = {36, 48, 60, 111, 135, 142, 152, 156};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(0, sem.checkForFieldCourses(sem.getSemesterNo()));
    }

    @Test
    void noFieldCourseAddedSem4() {
        sem = new Semester(4);
        int[] courses = {24, 55, 99, 211, 324};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertEquals(0, sem.checkForFieldCourses(sem.getSemesterNo()));
    }

    @Test
    void lessThan30EctsAdded() {
        sem = new Semester(4);
        int[] courses = {24, 55, 99, 211, 324};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertTrue(sem.ectsOutsideRange());
    }

    @Test
    void moreThan70EctsAdded() {
        sem = new Semester(4);
        int[] courses = {24, 34, 44, 54, 64, 74, 84, 94, 104, 114, 124, 134, 144, 154, 164, 174, 184, 194,
                204, 214, 224, 234, 244, 254, 264, 274, 304, 314, 324, 354};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertTrue(sem.ectsOutsideRange());
    }

    @Test
    void correctNumberOfEctsAdded() {
        sem = new Semester(4);
        int[] courses = {24, 34, 44, 54, 64, 74, 84, 94, 104, 114, 124, 134, 144, 154, 164, 174, 184, 194};
        for (int x : courses) {
            sem.isCollision(x);
        }
        assertFalse(sem.ectsOutsideRange());
    }

}