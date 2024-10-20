import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class GymsystemTest {
    private Gymsystem gymsystem;

    private final String TEST_MEDLEMSFIL = "test_medlemmar.txt";
    private final String TEST_TRANINGSFIL = "test_tranings.txt";

    @BeforeEach
    public void setUp() { //skapa ny instans av GymSystem före varje test
        gymsystem = new Gymsystem();
        LocalDate ettÅrSedan = LocalDate.now().minusYears(1);
        LocalDate tvåÅrSedan = LocalDate.now().minusYears(2);

//skapar testfil med ngra medlemmar
        try(PrintWriter writer = new PrintWriter(TEST_MEDLEMSFIL)) {
            writer.println("7803061234, Hanna Grönvall");
            writer.println(ettÅrSedan.toString());
            writer.println("8910111234, Magnus Bengtsson");
            writer.println(tvåÅrSedan.toString());
        }catch(FileNotFoundException e) {
            fail("Kunde inte skapa en textfil.");
        }
    }
    @AfterEach
    public void cleanup() { //tar bort testfilerna efter varje test
        new File(TEST_MEDLEMSFIL).delete();
        new File(TEST_TRANINGSFIL).delete();
    }
    @Test
    public void testLasInPersoner() throws FileNotFoundException { //skapar en scanner för testfilen
        Scanner scanner = new Scanner(new File(TEST_MEDLEMSFIL));

    }

}