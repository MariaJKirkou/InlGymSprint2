import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setUp() {
        // Initiera en testperson
        person = new Person("1234567890", "Testnamn", LocalDate.now().minusMonths(6));
    }

    @Test
    public void testHarBetalatArsavgift() {
        assertTrue(person.harBetalatArsavgift(), "Personen ska ha betalat årsavgiften.");

        // senaste betalad datum mer än ett år sedan
        person = new Person("1234567890", "Testnamn", LocalDate.now().minusYears(2));
        assertFalse(person.harBetalatArsavgift(), "Personen ska inte ha betalat årsavgiften.");
    }

    @Test
    public void testKollaKategori() {
        assertEquals("Nuvarande medlem", person.kollaKategori(LocalDate.now()), "Kategorin ska vara 'Nuvarande medlem'.");

        // Sätt senaste betalad datum mer än ett år sedan
        person = new Person("1234567890", "Testnamn", LocalDate.now().minusYears(2));
        assertEquals("Föredetta kund", person.kollaKategori(LocalDate.now()), "Kategorin ska vara 'Föredetta kund'.");
    }
}
