import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class DataSaver {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        ArrayList<String> records = new ArrayList<>();

        int idCounter = 1;
        boolean addAnother = true;

        while (addAnother) {

            System.out.println("\nENTER PERSON INFORMATION");
            System.out.println("--------------------------");

            String firstName = SafeInput.getNonZeroLenString(
                    console, "Enter First Name: ");

            String lastName = SafeInput.getNonZeroLenString(
                    console, "Enter Last Name: ");

            String idNumber = String.format("%06d", idCounter);

            String email = SafeInput.getRegExString(
                    console,
                    "Enter Email: ",
                    "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
            );

            int yearOfBirth = SafeInput.getRangedInt(
                    console, "Enter Year of Birth: ", 1000, 9999);

            String record = firstName + ", " +
                    lastName + ", " +
                    idNumber + ", " +
                    email + ", " +
                    yearOfBirth;

            records.add(record);

            System.out.println("\nRecord added successfully!");
            System.out.println("ID Number: " + idNumber);

            idCounter++;

            // Ask whether the user wants to add another record
            addAnother = SafeInput.getYNConfirm(
                    console, "Would you like to add another person? ");

        }

        System.out.println("\nAll records have been collected.");

        String fileName = SafeInput.getNonZeroLenString(
                console, "Enter the CSV file name: ");

        if (!fileName.toLowerCase().endsWith(".csv")) {
            fileName += ".csv";
        }

        File outputFile = new File("src", fileName);

        try {
            PrintWriter writer = new PrintWriter(
                    new FileWriter(outputFile));

            // Write each record into the CSV file
            for (String record : records) {
                writer.println(record);
            }

            writer.close();

            System.out.println("\nFile saved successfully!");
            System.out.println("File Location: " + outputFile.getPath());
            System.out.println("Total Records Saved: " + records.size());

    } catch (IOException e) {
            System.out.println("Error saving the file!");
        }

        console.close();
    }
}