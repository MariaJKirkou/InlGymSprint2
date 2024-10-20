import java.time.LocalDate;

public class Person {
    private String personnummer;
    private String namn;
    private LocalDate senasteBetaldDatum; // innehåller datumet då personen senast betalade sin medl.avgift

    //Konstruktorn tar in tre parametrar används för att initiera objektets tillstånd.
    public Person(String personnummer, String namn, LocalDate senasteBetaldDatum) {
        this.personnummer = personnummer;
        this.namn = namn;
        this.senasteBetaldDatum = senasteBetaldDatum;

    }

    public boolean harBetalatArsavgift() {
        LocalDate idag = LocalDate.now();
        return senasteBetaldDatum.plusYears(1).isAfter(idag);
        //jämnför om betalningsdatumet är mindre än ett år gammalt,fr idag
        //Om personen har betalat inom den senaste 12-månadersperioden returnerar metoden true, annars false.
        // Metoden alltid använder det aktuella datumet för jämförelsen.
        //Den kommer att returnera true så länge det inte har gått mer än ett år sedan senaste betalningen.
    }

    public String getPersonnummer() {
        return personnummer;
    }

    public String getNamn() {
        return namn;
    }

    public LocalDate getSenasteBetaldDatum() {
        return senasteBetaldDatum;
    }


    public String kollaKategori(LocalDate today) {
        if (senasteBetaldDatum.isAfter(today.minusYears(1))) {
            return "Nuvarande medlem";
        } else {
            return "Föredetta kund";
        }
    }
}

