package mbabski.core;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Comparator;

public class Subjects implements Serializable, Comparator<Subjects> {

    private String signature;
    private String subject;
    private String lecturer;
    private DayOfWeek day;
    private LocalTime hour;
    private String venue;
    private double ects;

    public String getSignature() {
        return signature;
    }

    public String getSubject() {
        return subject;
    }

    public String getLecturer() {
        return lecturer;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public LocalTime getHour() {
        return hour;
    }

    public String getVenue() {
        return venue;
    }

    public double getEcts() {
        return ects;
    }

    public Subjects(String signature, String subject, String lecturer,
                    DayOfWeek day, LocalTime hour, String venue, double ects) {
        this.signature = signature;
        this.subject = subject;
        this.lecturer = lecturer;
        this.day = day;
        this.hour = hour;
        this.venue = venue;
        this.ects = ects;
    }

    public static void printSubjects() {
        System.out.println("\nPrzedmioty obowiązkowe dla wszystkich kierunków (I semestr):\n");
        for (int i = 0; i < Courses.COMPULSORY; i++) {
            System.out.println(DataBase.getInstance().getCourses().get(i).getSignature() + ": " +
                    DataBase.getInstance().getCourses().get(i).getSubject());
        }
        System.out.println("\nPrzedmioty obowiązkowe dla kierunku Finanse i rachunkowość:\n");
        for (int i = Courses.COMPULSORY; i < Courses.FIELD_END_INDEX; i++) {
            System.out.println(DataBase.getInstance().getCourses().get(i).getSignature() + ": " +
                    DataBase.getInstance().getCourses().get(i).getSubject());
        }
        System.out.println("\nPrzedmioty fakultatywne dla kierunku Finanse i rachunkowość:\n");
        for (int i = Courses.FIELD_END_INDEX; i < Courses.FACULTATIVE_END_INDEX; i++) {
            System.out.println(DataBase.getInstance().getCourses().get(i).getSignature() + ": " +
                    DataBase.getInstance().getCourses().get(i).getSubject());
        }
        System.out.println("\nPozostałe przedmioty do wyboru:\n");
        for (int i = Courses.FACULTATIVE_END_INDEX; i < DataBase.getInstance().getCourses().size(); i++) {
            System.out.println(DataBase.getInstance().getCourses().get(i).getSignature() + ": " +
                    DataBase.getInstance().getCourses().get(i).getSubject());
        }
    }

    public static void printFieldSubjects(){
        System.out.println("\nPrzedmioty obowiązkowe dla kierunku Finanse i rachunkowość:");
        for (int i = Courses.COMPULSORY; i < Courses.FIELD_END_INDEX; i++) {
            System.out.println(DataBase.getInstance().getCourses().get(i).getSignature() + ": " +
                    DataBase.getInstance().getCourses().get(i).getSubject());
        }
    }

    public static void printRules() {
        System.out.println("\nProgram umożliwia deklarację semestralną przedmiotów na wybranym przez studenta " +
                "\nsemestrze studiów magisterskich na kierunku finanse i rachunkowość. Student w jednym semestrze " +
                "\nmoże zrealizować nie mniej niż 30 pkt ECTS oraz nie więcej niż 70 pkt ECTS, ponadto w zależności " +
                "\nod semestru deklaracja studenta może zawierać pewne elementy obowiązkowe np. język obcy lub " +
                "\nseminarium magisterskie, które znajdują się już w koszyku wybranych przedmiotów i student nie ma " +
                "\nmożliwości usunięcia ich z tego koszyka. W przypadku większości przedmiotów student ma do wyboru " +
                "\nprzynajmniej dwóch wykładowców prowadzących zajęcia w różnych terminach. W momencie próby wyboru " +
                "\nzajęć, których terminy się pokrywają zostaje zwrócony komunikat o kolizji. Oprócz dodawania " +
                "\ni usuwania przedmiotów z koszyka program pozwala podejrzenie aktualnej zawartości koszyka " +
                "\nwybranych przedmiotów, a także na wyświetlenie tygodniowego planu zajęć. W przypadku, gdy " +
                "\nzawartość koszyka spełnia wymogi dla wybranego semestru możliwe jest przeniesienie zawartości " +
                "\nkoszyka do deklaracji semestralnej. W momencie, gdy koszyk wybranych przez studenta przedmiotów " +
                "\nnie zawiera wszystkich wymaganych na danym semestrze przedmiotów, zostaje wyświetlony komunikat " +
                "\nz informacją, które przedmioty należy dodać do koszyka, aby jego zawartość mogła zostać " +
                "\npomyślnie przeniesiona do deklaracji semestralnej.\n" +
                "\nPrzedmioty obowiązkowe dla poszczególnych semestrów:" +
                "\na) semestr I:" +
                "\n- Język obcy," +
                "\n- Historia myśli ekonomicznej," +
                "\n- Prawo gospodarcze," +
                "\n- Ekonomia menedżerska," +
                "\n- Ekonomia rozwoju," +
                "\n- Ekonomia sektora publicznego," +
                "\n- dowolny przedmiot z grupy przedmiotów kierunkowych." +
                "\nb) semestr II:" +
                "\n- Język obcy," +
                "\n- 6 przedmiotów z grupy przedmiotów kierunkowych." +
                "\nc) semestr III:" +
                "\n- Seminarium magisterskie," +
                "\n- 6 przedmiotów z grupy przedmiotów kierunkowych." +
                "\nd) semestr IV" +
                "\n- Seminarium magisterskie.\n");
    }

    @Override
    public int compare(Subjects o1, Subjects o2) {
        return o1.getDay().compareTo(o2.getDay());
    }

}
