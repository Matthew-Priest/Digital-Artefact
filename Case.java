
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
    private Status status;
    private String closeReason = "";

    //Constructor Overloading. One for creating an initial case from create method, other for creating a case that has been read from a txt file
    public Case(int caseNumber, String newName, String caseDescription, String Dev, Status status) {
        this.caseNumber = caseNumber;
        this.caseName = newName;
        this.caseDescription = caseDescription;
        this.Dev = Dev;
        this.setDate = LocalDate.now();
        this.status = status;
    }
    public Case(String caseNum, String caseName, String caseDescription, String Dev, String creationDate, Status status, String closeReason){
        this.caseName = caseName;
        this.caseDescription = caseDescription;
        this.Dev = Dev;
        this.setDate = LocalDate.parse(creationDate);
        this.status = status;
        this.caseNumber = Integer.parseInt(caseNum);
        if (closeReason == null) {
            this.closeReason = "";
        } else {
            this.closeReason = closeReason;
        }
    }
    @Override
    public String toString() {
        String text = "Case Number: " + caseNumber + "\n"
         + "Title: " + caseName + "\n"
         + "Description: " + caseDescription + "\n"
         + "Developer: " + Dev + "\n"
         + "Date Created: " + setDate + "\n"
         + "Status: " + status;
        if(status == Status.closed){
            text += "\nClose Reason: " + closeReason;
        }
        return text;
}

    public void writeToFile(){
        try {
            FileWriter myWriter = new FileWriter("caseList.txt", true);

            myWriter.write("Case Number: " + caseNumber + "\n");
            myWriter.write("Case Name: " + caseName + "\n");
            myWriter.write("Description: " + caseDescription + "\n");
            myWriter.write("Developer: " + Dev + "\n");
            myWriter.write("Date Created: " + setDate + "\n");
            myWriter.write("Status: "+ status + "\n");
            if(status == Status.closed){
                myWriter.write("Close Reason: "+ closeReason + "\n");
            }
            myWriter.write("--------------------\n");

            myWriter.close();

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void closeCase(String reason){
        this.status = Status.closed;
        this.closeReason = reason;
    }
    public int getCaseNumber() {
        return caseNumber;
    }
    public String getCaseTitle() {
        return caseName;
    }
    public void setCaseTitle(String title){
        this.caseName = title;
    }
    public void setCaseDescription(String description){
        this.caseDescription = description;
    }
    public void setCaseStatus(Status status){
        this.status = status;
    }
    public void setCaseDev(String dev){
        this.Dev = dev;
    }

}