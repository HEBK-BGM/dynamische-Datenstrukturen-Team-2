package de.hebk;

import java.util.Scanner;

public class Anmeldung {
    Scanner sc = new Scanner(System.in);
    String vorname;
    String nachname;
    String passwort;
    int alter;


    Anmeldung(){
        System.out.println("Wie lautet dein Name?");
        System.out.println("Vorname:");
        vorname=sc.nextLine();
        System.out.println("Nachname:");
        nachname=sc.nextLine();
        breakline();
        System.out.println("Wie alt bist du?");
        alter=sc.nextInt();
        breakline();
        System.out.println("Wie soll dein Passwort lauten?");
        passwort=sc.nextLine();
        breakline();

        Person p1 = new Person(vorname, nachname,alter,passwort);
    }

public void breakline(){
    System.out.println("---------------------");
}

}
