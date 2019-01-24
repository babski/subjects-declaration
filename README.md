**Deklaracja semestralna przedmiotów – opis projektu**
/ aplikacja konsolowa - w repozytorium znajduje się również projekt aplikacji webowej o tej samej nazwie /

Program umożliwia deklarację semestralną przedmiotów na wybranym przez studenta semestrze studiów magisterskich na kierunku finanse i rachunkowość. Student w jednym semestrze może zrealizować nie mniej niż 30 pkt ECTS oraz nie więcej niż 70 pkt ECTS, ponadto w zależności od semestru deklaracja studenta może zawierać pewne elementy obowiązkowe np. język obcy lub seminarium magisterskie, które znajdują się już w koszyku wybranych przedmiotów i student nie ma możliwości usunięcia ich z tego koszyka. W przypadku większości przedmiotów student ma do wyboru przynajmniej dwóch wykładowców prowadzących zajęcia w różnych terminach. W momencie próby wyboru zajęć, których terminy się pokrywają zostaje zwrócony komunikat o kolizji. Oprócz dodawania i usuwania przedmiotów z koszyka program pozwala podejrzenie aktualnej zawartości koszyka wybranych przedmiotów, a także na wyświetlenie tygodniowego planu zajęć. W przypadku, gdy zawartość koszyka spełnia wymogi dla wybranego semestru możliwe jest przeniesienie zawartości koszyka do deklaracji semestralnej. W momencie, gdy koszyk wybranych przez studenta przedmiotów nie zawiera wszystkich wymaganych na danym semestrze przedmiotów, zostaje wyświetlony komunikat z informacją, które przedmioty należy dodać do koszyka, aby jego zawartość mogła zostać pomyślnie przeniesiona do deklaracji semestralnej.

Przedmioty obowiązkowe dla poszczególnych semestrów:

1. a)semestr I:

- Język obcy,
- Historia myśli ekonomicznej,
- Prawo gospodarcze,
- Ekonomia menedżerska,
- Ekonomia rozwoju,
- Ekonomia sektora publicznego,
- dowolny przedmiot z grupy przedmiotów kierunkowych.

1. b)semestr II:

- Język obcy,
- 6 przedmiotów z grupy przedmiotów kierunkowych.

1. c)semestr III:

- Seminarium magisterskie,
- 6 przedmiotów z grupy przedmiotów kierunkowych.

1. d)semestr IV

- Seminarium magisterskie.

**Opisy klas:**

**GUI** – abstrakcyjna, posiada metodę abstrakcyjną wywołującą menu główne programu

**WindowGUI** – rozszerza klasę _GUI_, dopuszcza możliwość dodania okienkowego interfejsu użytkownika

**ConsoleGUI** – rozszerza klasę _GUI_, wywołuje menu główne programu w konsoli

**Courses** – implementuje interfejs _Serializable_, jej obiekty przechowują informacje dotyczące przedmiotów z katalogu studiów (sygnatura przedmiotu, nazwa przedmiotu, liczba pkt ECTS)

**DataBase** – stworzona w oparciu o wzorzec projektowy _Singleton_, wczytuje z plików listy przedmiotów z katalogu studiów oraz z harmonogramu zajęć

**Main** – tworzy obiekt typu ConsoleGUI wywołujący metodę uruchamiającą menu główne

**Semester** – zawiera funkcję dodania do koszyka przedmiotów, usunięcia przedmiotów z koszyka, wyświetlenia zawartości listy wybranych przedmiotów oraz harmonogramu zajęć oraz sprawdza czy zawartość koszyka zawiera wszystkie wymagane dla danego semestru przedmioty i tym samym może być przeniesiona do deklaracji semestralnej

**Subjects** – implementuje interfejs _Serializable_ oraz _Comparator_, jej obiekty przechowują informacje dotyczące przedmiotów z semestralnego harmonogramu zajęć (sygnatura przedmiotu, nazwa przedmiotu, wykładowca, dzień, godzina rozpoczęcia zajęć, miejsce oraz liczba pkt ECTS), posiada metody umożliwiające wyświetlenie zasad wyboru przedmiotów, listy wszystkich przedmiotów oraz listy przedmiotów kierunkowych

**WriteSubjects** – dodaje przedmioty do listy przedmiotów z katalogu studiów oraz harmonogramu zajęć, a następnie zapisuje je do plików
