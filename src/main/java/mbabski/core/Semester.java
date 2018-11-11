package mbabski.core;

import mbabski.gui.ConsoleGUI;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Semester {
    private static Scanner sc = new Scanner(System.in);
    private List<Subjects> chosenCourses;
    private int semesterNo;

    public List<Subjects> getChosenCourses() {
        return chosenCourses;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public void setChosenCourses(List<Subjects> chosenCourses) {
        this.chosenCourses = chosenCourses;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    public Semester(int semesterNo) {
        chosenCourses = new ArrayList<Subjects>();
        this.semesterNo = semesterNo;
    }

    public void semester1Action() {
        this.chosenCourses.add(new Subjects("201010-0001", "Lektorat z języka obcego",
                "Nowak Halina", DayOfWeek.MONDAY, LocalTime.of(17, 10), "C-201", 4.5));
        for (int i = 0; i < (Courses.COMPULSORY + Courses.FIELD_SEM1_END_INDEX); i++) {
            addLecturer(DataBase.getInstance().getCourses().get(i).getSignature());
        }
        addCourse(Courses.fieldCourse);
    }

    public void semester2Action() {
        this.chosenCourses.add(new Subjects("201020-0001", "Lektorat z języka obcego",
                "Tomczyk Zofia", DayOfWeek.THURSDAY, LocalTime.of(17, 10), "C-201", 4.5));
        for (int i = 0; i < Courses.FIELD_TO_CHOOSE_SEM23; i++) {
            addCourse(Courses.fieldCourse);
        }
    }

    public void semester3Action() {
        this.chosenCourses.add(new Subjects("290000-0001", "Seminarium magisterskie", "",
                DayOfWeek.MONDAY, LocalTime.of(19, 00), "", 8.0));
        for (int i = 0; i < Courses.FIELD_TO_CHOOSE_SEM23; i++) {
            addCourse(Courses.fieldCourse);
        }
    }

    public void semester4Action() {
        this.chosenCourses.add(new Subjects("290020-0001", "Seminarium magisterskie", "",
                DayOfWeek.TUESDAY, LocalTime.of(19, 00), "", 12.0));
        for (int i = 0; i < 6; i++) {
            addCourse(Courses.anyCourse);
        }
    }

    public void start() {
        switch (this.semesterNo) {
            case 1:
                semester1Action();
                break;
            case 2:
                semester2Action();
                break;
            case 3:
                semester3Action();
                break;
            default:
                semester4Action();
        }
        chooseAction();
    }

    public void printSemesterMenu() {
        System.out.print("\nWybierz akcję z poniższej listy:" +
                "\n1 - Wyświetl listę wszystkich przedmiotów" +
                "\n2 - Dodaj przedmiot do koszyka" +
                "\n3 - Usuń przedmiot z koszyka" +
                "\n4 - Wyświetl listę przedmiotów dodanych do koszyka" +
                "\n5 - Wyświetl tygodniowy harmonogram zajęć" +
                "\n6 - Koszyk -> Deklaracja (wysyłka do dziekanatu)" +
                "\n\nWybierz nr polecenia (1-6): ");
    }

    public void chooseAction() {
        printSemesterMenu();
        String s1 = sc.nextLine();
        switch (ConsoleGUI.getIntegerFromScanner(6, s1)) {
            case 1:
                Subjects.printSubjects();
                break;
            case 2:
                addCourse(Courses.anyCourse);
                break;
            case 3:
                removeCourse();
                break;
            case 4:
                printList();
                break;
            case 5:
                printSchedule();
                break;
            case 6:
                declareBasket();
                break;
            default:
                System.out.println("Wybrano nieprawidłowy nr, spróbuj ponownie:");
                break;
        }
        chooseAction();
    }

    public boolean checkIfAdded(String numer) {
        for (int i = 1; i < this.chosenCourses.size(); i++) {
            if (numer.equals(this.chosenCourses.get(i).getSignature().substring(0, 5))) {
                System.out.println("Przedmiot " + "\"" + DataBase.getCourseName(numer) + "\" o sygnaturze " +
                        numer + "X został już wcześniej dodany do koszyka. Wybierz inny przedmiot.");
                return true;
            }
        }
        return false;
    }

    public void addCourse(boolean fieldCourse) {
        String course = (fieldCourse) ? " kierunkowych:" : ":";
        System.out.println("Wybierz przedmiot z listy przedmiotów" + course);
        if (fieldCourse) {
            Subjects.printFieldSubjects();
        } else {
            Subjects.printSubjects();
        }
        boolean flag = true;
        String signature = "";
        while (flag) {
            System.out.print("\nWpisz sygnaturę przedmiotu: ");
            signature = sc.nextLine();
            flag = checkIfAdded(signature);
        }
        addLecturer(Courses.validateSignature(fieldCourse, signature));
    }

    public boolean isCollision(int courseNumber) {
        for (int j = 0; j < this.chosenCourses.size(); j++) {
            if (DataBase.getInstance().getSubjects().get(courseNumber).getDay().equals(this.chosenCourses.
                    get(j).getDay()) && DataBase.getInstance().getSubjects().get(courseNumber).getHour().equals
                    (this.chosenCourses.get(j).getHour())) {
                System.out.println("Nie można dodać:");
                DataBase.getSubjectInfo(DataBase.getInstance().getSubjects(), courseNumber);
                System.out.println("Wybrany przedmiot koliduje terminem zajęć z przedmiotem \"" +
                        this.chosenCourses.get(j).getSubject() + "\" o sygnaturze "
                        + this.chosenCourses.get(j).getSignature() + ".\nUsuń kolidujący przedmiot" +
                        " z koszyka bądź, jeśli to możliwe, wybierz innego wykładowcę.");
                return true;
            }
        }
        this.chosenCourses.add(DataBase.getInstance().getSubjects().get(courseNumber));
        System.out.println("Pomyślnie dodano:");
        DataBase.getSubjectInfo(DataBase.getInstance().getSubjects(), courseNumber);
        return false;
    }

    public void addLecturer(String signature) {
        int end = 0;
        int lecturers = 0;
        System.out.println("\nAby wybrać wykładowcę dla przedmiotu \"" + DataBase.getCourseName(signature) +
                "\" wpisz ostatnie 4 cyfry jego sygnatury:\n");
        for (int i = 0; i < DataBase.getInstance().getSubjects().size(); i++) {
            if (signature.equals(DataBase.getInstance().getSubjects().get(i).getSignature().substring(0, 5))) {
                end = i;
                lecturers++;
            }
        }
        for (int i = (end - lecturers + 1); i <= end; i++) {
            DataBase.getSubjectInfo(DataBase.getInstance().getSubjects(), i);
        }
        int chosen = DataBase.chooseLecturer(end, lecturers);
        if (isCollision(chosen)) {
            chooseAction();
        }
    }

    public double countECTSInBasket(List<Subjects> list) {
        return list.stream().mapToDouble(x -> x.getEcts()).sum();
    }

    public void printList() {
        System.out.println("Lista aktualnie zadeklarowanych przedmiotów:");
        for (int i = 0; i < this.chosenCourses.size(); i++) {
            System.out.print((i + 1) + ". ");
            DataBase.getSubjectInfo(this.chosenCourses, i);
        }
    }

    public int validateRemoveNumber() {
        String s = sc.nextLine();
        while ((!s.matches("[0-9]+")) || ((Integer.parseInt(s) < 2) && (Integer.parseInt(s) != 0))
                || (Integer.parseInt(s) > this.chosenCourses.size())) {
            System.out.print("Wybrano nieprawidłowy nr przedmiotu, spróbuj ponownie: ");
            s = sc.nextLine();
        }
        return Integer.parseInt(s);
    }

    public void removeCourse() {
        printList();
        System.out.print("\nWybierz nr, przedmiotu, który chcesz usunąć lub '0', aby wrócić do" +
                " poprzedniego menu: ");
        int choice = validateRemoveNumber();
        if (choice != 0) {
            System.out.println("Usunięto z listy przedmiot:");
            DataBase.getSubjectInfo(this.chosenCourses, choice - 1);
            this.chosenCourses.remove(choice - 1);
        }
        chooseAction();
    }

    public void printSchedule() {
        List<Subjects> sortedList = chosenCourses.stream()
                .sorted(Comparator.comparing(Subjects::getDay).thenComparing(Subjects::getHour))
                .collect(Collectors.toList());

        for (int i = 0; i < 7; i++) {
            System.out.println("\n" + DayOfWeek.values()[i]);
            int count = 0;
            for (int j = 0; j < sortedList.size(); j++) {
                if (sortedList.get(j).getDay().ordinal() == i) {
                    count++;
                    System.out.println(sortedList.get(j).getHour() + "-" + sortedList.get(j).getHour().
                            plusMinutes(100) + " | " + sortedList.get(j).getSubject() + " (" +
                            sortedList.get(j).getLecturer() + ") | " + sortedList.get(j).getVenue());
                }
            }
            if (count == 0) {
                System.out.println("Wolne!");
            }
        }
        System.out.println();
        chooseAction();
    }

    public int checkForCompulsoryCourses(int semesterNo) {
        String[] compulsoryCourses = new String[Courses.FIELD_SEM1_END_INDEX];
        for (int i = 0; i < compulsoryCourses.length; i++) {
            compulsoryCourses[i] = DataBase.getInstance().getCourses().get(i).getSignature();
        }
        int warningsSem1 = 0;
        int warningsSem234 = 0;
        for (int i = 0; i < compulsoryCourses.length; i++) {
            int count = 0;
            for (int j = 0; j < this.chosenCourses.size(); j++) {
                if (compulsoryCourses[i].equals(this.chosenCourses.get(j).getSignature().substring(0, 5))) {
                    if (semesterNo != 1) {
                        System.out.println("Błąd! Wybrano przedmiot \"" + DataBase
                                .getCourseName(compulsoryCourses[i]) + "\", który został zrealizowany " +
                                "w I semestrze. Usuń przedmiot z koszyka.");
                        warningsSem234++;
                    }
                    count++;
                    break;
                }
            }
            if (count == 0 && semesterNo == 1) {
                System.out.println("Błąd! Nie wybrano przedmiotu \"" + DataBase
                        .getCourseName(compulsoryCourses[i]) + "\".");
                warningsSem1++;
            }
        }
        return (semesterNo == 1) ? warningsSem1 : warningsSem234;
    }

    public int checkForFieldCourses(int semesterNo) {
        int coursesToDeclare = (semesterNo == 1) ? Courses.FIELD_TO_CHOOSE_SEM1 : Courses.FIELD_TO_CHOOSE_SEM23;
        int count = 0;
        for (int i = 0; i < this.chosenCourses.size(); i++) {
            for (int j = Courses.FIELD_SEM1_END_INDEX; j < Courses.FIELD_END_INDEX; j++) {
                if (this.chosenCourses.get(i).getSignature().substring(0, 5).equals(DataBase.getInstance()
                        .getCourses().get(j).getSignature())) {
                    count++;
                }
            }
        }
        if (count < coursesToDeclare && semesterNo != 4) {
            System.out.println("Nie wybrano wymaganej liczby przedmiotów kierunkowych." +
                    " Dobierz jeszcze przedmioty kierunkowe w liczbie przynajmniej: " + (coursesToDeclare - count)
                    + ".");
            return coursesToDeclare - count;
        }
        return 0;
    }

    public boolean ectsOutsideRange() {
        double ectsInBasket = countECTSInBasket(chosenCourses);
        if (ectsInBasket < Courses.ECTS_MIN) {
            System.out.println("Wybrano za mało przedmiotów. Dodaj do koszyka przedmioty o wartości " +
                    "przynajmniej " + (Courses.ECTS_MIN - ectsInBasket) + " pkt ECTS.");
            return true;
        }
        if (ectsInBasket > Courses.ECTS_MAX) {
            System.out.println("Wybrano za dużo przedmiotów. Usuń z koszyka przedmioty o wartości " +
                    "przynajmniej " + (ectsInBasket - Courses.ECTS_MAX) + " pkt ECTS.");
            return true;
        }
        return false;
    }

    public void declareBasket() {
        int warnings = checkForCompulsoryCourses(semesterNo) + checkForFieldCourses(semesterNo);
        if (warnings > 0 || ectsOutsideRange()) {
            chooseAction();
        }
        System.out.println("Przedmioty zawarte w koszyku poprawnie dodano do deklaracji i wysłano " +
                "do dziekanatu.");
        System.exit(0);
    }
}