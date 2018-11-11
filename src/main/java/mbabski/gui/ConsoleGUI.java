package mbabski.gui;

import mbabski.core.Semester;
import mbabski.core.Subjects;

import java.util.Scanner;

public class ConsoleGUI extends GUI {
    private static Scanner sc = new Scanner(System.in);

    public static int getIntegerFromScanner(int options, String s) {
        while ((!s.matches("[0-9]+")) || ((Integer.parseInt(s) < 0) || (Integer.parseInt(s) > options))) {
            System.out.print("Wybrano nieprawidłowy nr, spróbuj ponownie: ");
            s = sc.nextLine();
        }
        return Integer.parseInt(s);
    }

    public static void printMenu() {
        System.out.print("Menu główne:" +
                "\n1 - Zasady wyboru przedmiotów" +
                "\n2 - Lista dostępnych przedmiotów" +
                "\n3 - Rozpoczęcie procedury wyboru przedmiotów" +
                "\n0 - Wyjście" +
                "\n\n" + "Wybierz nr polecenia (0-3): ");
    }

    public void chooseSemester() {
        System.out.print("Wpisz nr semestru, na który chcesz wybrać przedmioty (1-4): ");
        String s2 = sc.nextLine();
        Semester semester;
        switch (getIntegerFromScanner(4, s2)) {
            case 1:
                semester = new Semester(1);
                semester.start();
                break;
            case 2:
                semester = new Semester(2);
                semester.start();
                break;
            case 3:
                semester = new Semester(3);
                semester.start();
                break;
            case 4:
                semester = new Semester(4);
                semester.start();
                break;
            default:
                showMainMenu();
                break;
        }
    }

    @Override
    public void showMainMenu() {
        printMenu();
        String s1 = sc.nextLine();
        switch (getIntegerFromScanner(3, s1)) {
            case 1:
                Subjects.printRules();
                showMainMenu();
                break;
            case 2:
                Subjects.printSubjects();
                showMainMenu();
                break;
            case 3:
                chooseSemester();
                break;
            default:
                System.exit(0);

        }

    }
}