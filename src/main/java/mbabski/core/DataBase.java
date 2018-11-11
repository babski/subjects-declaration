package mbabski.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataBase {
    private List<Subjects> subjects;
    private List<Courses> courses;

    private static final DataBase INSTANCE = new DataBase();

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    private DataBase() {
    }

    public static DataBase getInstance() {
        return INSTANCE;
    }

    public void loadSubjects() {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("subjects.txt")))) {
            subjects = (ArrayList<Subjects>) in.readObject();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadCourses() {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get("courses.txt")))) {
            courses = (ArrayList<Courses>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getSubjectInfo(List<Subjects> list, int i) {
        System.out.println(list.get(i).getSignature() + ": " + list.get(i).getSubject() + " | " +
                list.get(i).getLecturer() + " | " + list.get(i).getDay() + " " + list.get(i).getHour() + "-" +
                list.get(i).getHour().plusMinutes(100) + " | " + list.get(i).getVenue() + " | ECTS: " +
                list.get(i).getEcts());
    }

    public static String getCourseName(String signature) {
        for (int i = 0; i < DataBase.getInstance().getCourses().size(); i++) {
            if (signature.equals(DataBase.getInstance().getCourses().get(i).getSignature())) {
                return DataBase.getInstance().getCourses().get(i).getSubject();
            }
        }
        throw new IllegalStateException("Brak przedmiotu o wskazanej sygnaturze.");
    }

    public static int chooseLecturer(int end, int lecturers) {
        System.out.print("\nWybór: ");
        Scanner sc = new Scanner(System.in);
        String choice;
        while (true) {
            choice = sc.nextLine();
            for (int i = (end - lecturers + 1); i <= end; i++) {
                if (choice.equals(DataBase.getInstance().getSubjects().get(i).getSignature().
                        substring(7, 11))) {
                    return i;
                }
            }
            System.out.print("Podano nieprawidłową wartość, spróbuj jeszcze raz: ");
        }
    }
}
