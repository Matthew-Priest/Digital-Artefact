
//import java.io.BufferedReader;
//import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Case {
    private String caseName;
    private String caseDescription;
    private int caseNumber;
    private LocalDate setDate;
    private String Dev;

    //Constructor Overloading. One for creating an initial case from create method, other for creating a case that has been read from a txt file
    public Case(int caseNumber, String newName, String caseDescription, String Dev) {
        this.caseNumber = caseNumber;
        this.caseName = newName;
        this.caseDescription = caseDescription;
        this.Dev = Dev;
        this.setDate = LocalDate.now();
    }

    public  Case(String caseNum, String caseName, String caseDescription, String Dev, String creationDate){
        this.caseName = caseName;
        this.caseDescription = caseDescription;
        this.Dev = Dev;
        this.setDate = LocalDate.parse(creationDate);
        this.caseNumber = Integer.parseInt(caseNum);
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

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public int getCaseNumber() {
        return caseNumber;
    }

}