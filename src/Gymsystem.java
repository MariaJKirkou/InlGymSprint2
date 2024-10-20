import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gymsystem {
    private List<Person> kunder = new ArrayList<>();
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public void lasInPersoner(Scanner fileScanner) { //som läser från en fil och fyller medlemslistan med personer.
        //Dessa data används för att skapa nya Person-objekt som sedan läggs till i medlemslistan (kunder).
        while (fileScanner.hasNextLine()) {
            String personInfo = fileScanner.nextLine();
            if (!fileScanner.hasNextLine()) {
                break;
            }
            String dateInfo = fileScanner.nextLine();
            String[] data = personInfo.split(", ");
            String personnummer = data[0];
            String namn = data[1];

            LocalDate senastBetaldDatum = LocalDate.parse(dateInfo.trim(), dateFormatter);
            kunder.add(new Person(personnummer, namn, senastBetaldDatum));
        }
    }

    public String kollaPerson(String personnummer, LocalDate today) { //kontrollerar medlemsstatus
        for (Person person : kunder) {
            if (person.getPersonnummer().equals(personnummer)) {
                return person.kollaKategori(today);
            }
        }
        return "Obehörig";
    }

    //Metoden returnerar true om träningsbesöket registrerades framgångsrikt, annars false
    public boolean sparaTraning(String personnummer, LocalDate datum, FileWriter writer) throws IOException {
        for (Person person : kunder) {
            if (person.getPersonnummer().equals(personnummer)) {
                if (person.harBetalatArsavgift()) {
                    writer.write(person.getPersonnummer() + ", " + person.getNamn() + ", " + datum + "\n");
                    return true;
                } else {
                    System.out.println("Har inte betalat årsavgiften. Träningsbesök kan inte registreras.");
                    return false;
                }
            }
        }
        System.out.println("Kunden hittades inte i systemet.");
        return false;
    }
}