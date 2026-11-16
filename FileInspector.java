import java.io.FileNotFoundException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class FileInspector {
    public static void main(String[] args) {
        File myObj = new File("filename.txt");
        JFileChooser chooser = new JFileChooser(new File("src"));

        chooser.showSaveDialog(null);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "GIF", "jpg", "gif");
        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File selectedFile = chooser.getSelectedFile();

            int lineCount = 0;
            int wordCount = 0;
            int charCount = 0;

            try {
                Scanner fileScanner = new Scanner(selectedFile);

                System.out.println("\nFILE CONTENTS:");
                System.out.println("-------------------------");

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();

                    System.out.println(line);

                    lineCount++;

                    charCount += line.length();

                    String trimmedLine = line.trim();

                    if (!trimmedLine.isEmpty()) {
                        String[] words = trimmedLine.split("\\s+");
                        wordCount += words.length;
                    }

                }

                fileScanner.close();

                System.out.println("\nFILE SUMMARY REPORT");
                System.out.println("-------------------------");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Number of Lines: " + lineCount);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + charCount);
                            
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");

            }
            
        } else {
            System.out.println("No file was selected.");
        }
    }
}