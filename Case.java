import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Case{
    private String caseName;
    private String caseDescription;
    private int caseNumber;
    private LocalDate setDate;
    private String Dev;
    private int caseCount = 0;


    public Case(String newName, String caseDescription, String Dev){
        this.caseName = newName;
        this.caseDescription = caseDescription;
        this.Dev = Dev;
        this.setDate = LocalDate.now();
        this.caseNumber = (caseCount += 1);
        caseCount += 1;
    }

    public void writeToFile(){
        try {
            FileWriter myWriter = new FileWriter("caseList.txt", true);

            myWriter.write("Case Number: " + caseNumber + "\n");
            myWriter.write("Case Name: " + caseName + "\n");
            myWriter.write("Description: " + caseDescription + "\n");
            myWriter.write("Developer: " + Dev + "\n");
            myWriter.write("Date Created: " + setDate + "\n");
            myWriter.write("--------------------\n");

            myWriter.close();
            System.out.println("Successfully wrote the case to the file.");
            
        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int readCaseCount(){
        try (BufferedReader reader =
            new BufferedReader(new FileReader("caseList.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.out.println("Could not read case count.");
        }
        return 1;
    }
    public static void main(String[] args) {
        System.out.println(readCaseCount());
    }
}