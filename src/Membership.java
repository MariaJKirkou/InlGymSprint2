import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Membership {
    public static void main(String[] args) {

        //För att kunna läsa in filer, skriva till filer, samt hantera datum, J-klasser som Scanner, File, FileWriter, och LocalDate.
        // Dessa klasser är viktiga för att hantera in- och utmatning samt datum.
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Ange namn på filen med GymMedlemmarna: ");
        String inputFileName = inputScanner.nextLine();

        System.out.print("Ange namn på den nya filen för att registerera träningsbesök: ");
        String outputFileName = inputScanner.nextLine();

        LocalDate today = LocalDate.now();

        // Vi skapar en instans av GymSystem för att hantera logik
        Gymsystem gymsystem = new Gymsystem();


        // Programmet använder ett så kallat "try-with-resources" block för att öppna indata- och utdatafiler.
        try (Scanner fileScanner = new Scanner(new File("Gymmembers.txt"));
             FileWriter writer = new FileWriter("Gymsortering.txt",true)) {

            gymsystem.lasInPersoner(fileScanner);

            System.out.print("Ange personnummer att kontrollera: ");
            String personnummer = inputScanner.nextLine();

            String kategori = gymsystem.kollaPerson(personnummer, today);
            System.out.println("Kundkategori: " + kategori);


            if (!kategori.equals("Obehörig")) {
                boolean besokRegistrerat = gymsystem.sparaTraning(personnummer, today, writer);
                if (besokRegistrerat) {
                    System.out.println("Träningsbesök registrerat.");
                }
            } else {
                System.out.println("Personen är obehörig.");
            }
//Använder ett "try-with-resources" block för att säkerställa att filresurser hanteras korrekt

        } catch (FileNotFoundException e) {
            System.err.println("Kunde inte hitta filen: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            inputScanner.close();
        }
    }
}


