package mbabski.core;

import java.io.Serializable;
import java.util.Scanner;

public class Courses implements Serializable {
    public static final int COMPULSORY = 2; // Przedmioty obowiązkowe dla wszystkich kierunków na I semestrze
    public static final int FIELD_SEM1_END_INDEX = 5; // Przedmioty obowiązkowe dla kierunku FiR na I semestrze
    public static final int FIELD_END_INDEX = 28; // Przedmioty kierunkowe na kierunku FiR
    public static final int FACULTATIVE_END_INDEX = 44; // Przedmioty fakultatywne dla kierunku FiR
    public static final int FIELD_TO_CHOOSE_SEM1 = 1; // Obowiązkowy przedmiot kierunkowy do wybrania na I semestrze
    public static final int FIELD_TO_CHOOSE_SEM23 = 6; // Obowiązkowe przedmioty kierunkowe na II i III semestrze
    public static final boolean fieldCourse = true;
    public static final boolean anyCourse = false;
    public static final int ECTS_MIN = 30; // Minimalna liczba przedmiotów do realizacji podczas semestru
    public static final int ECTS_MAX = 70; // Maksymalna liczba przedmiotów do realizacji podczas semestru

    private String signature;
    private String subject;
    private double ects;

    public Courses() {
    }

    public Courses(String signature, String subject) {
        this.signature = signature;
        this.subject = subject;
    }

    public Courses(String signature, String subject, double ects) {
        this.signature = signature;
        this.subject = subject;
        this.ects = ects;
    }

    public String getSignature() {
        return signature;
    }

    public String getSubject() {
        return subject;
    }

    public double getEcts() {
        return ects;
    }

    public static String validateSignature(boolean fieldCourse, String signature) {
        Scanner sc = new Scanner(System.in);
        int start = (fieldCourse) ? Courses.COMPULSORY : 0;
        int end = (fieldCourse) ? Courses.FIELD_END_INDEX : DataBase.getInstance().getCourses().size();
        while (true) {
            for (int i = start; i < end; i++) {
                if (signature.equals(DataBase.getInstance().getCourses().get(i).getSignature())) {
                    return signature;
                }
            }
            System.out.print("Wpisano niepoprawną sygnaturę przedmiotu, spróbuj jeszcze raz: ");
            signature = sc.nextLine();
        }
    }
}
