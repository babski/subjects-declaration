package mbabski.core;

import mbabski.gui.ConsoleGUI;


public class Main {
    public static void main(String[] args) {
        ConsoleGUI gui = new ConsoleGUI();
        System.out.println("Deklaracja semestralna przedmiotów na studiach magisterskich");
        WriteSubjects.writeToFile();
        DataBase instance = DataBase.getInstance();
        instance.loadCourses();
        gui.showMainMenu();
    }
}