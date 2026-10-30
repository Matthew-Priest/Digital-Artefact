import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class Artefact {
    public static void main(String[] args) {
        // HERE WILL LOAD CASES FROM TEXT FILE AND ADD EACH TO AN ARRAYLIST SO THEY CAN BE EDITED, THEN REPRINTED TO FILE BEFORE CLOSING OF PROGRAM
        ArrayList<Case> caseList = LoadCases();       
        System.out.println("This is a case management system");
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        while (!answer.equals("create") && !answer.equals("edit") && !answer.equals("delete")) {
            System.out.println("Would you like to create a case, edit a case, or delete a case?");
            answer = scanner.nextLine();
            answer = answer.toLowerCase();
        }
        switch (answer) {
            case "create":
                createCase(scanner);
                break;
            case "edit":
                editCase(scanner);
                break;
            case "delete":
                deleteCase(scanner);
                break;
            default:
                break;
        }
        scanner.close();
    }

    public static void createCase(Scanner scanner) {
        String caseName;
        String caseDescription;
        String Dev;

        System.out.println("What should the case be called");
        caseName = scanner.nextLine();

        System.out.println("What is the problem");
        caseDescription = scanner.nextLine();

        System.out.println("Who will be in charge of the case");
        Dev = scanner.nextLine();

        Case newCase = new Case(caseName, caseDescription, Dev);
        newCase.writeToFile();
    }

    public static void editCase(Scanner scanner) {

    }

    public static void deleteCase(Scanner scanner) {

    }

    public static ArrayList<Case> LoadCases() {
        ArrayList<Case> caseList = new ArrayList<Case>();
        String[] fields = new String[5];
        int count = 0;
        String Value;

        try (
            BufferedReader br = new BufferedReader(new FileReader("caseList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals("--------------------")){
                    Case newCase = new Case(fields[0], fields[1], fields[2], fields[3], fields[4]);
                    caseList.add(newCase);
                    count = 0;
                }
                else {
                    int startOfValue = line.indexOf(":");  
                    if(startOfValue == -1){                 //Validation here in the unlikely case a value doesn't contain a colon
                        Value = line;
                    } else {
                        Value = line.substring(startOfValue+ 2);
                    }
                    fields[count] = Value;
                    count += 1;
                }    
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return caseList;
    }

    public static void pasteCases(ArrayList caseList) {

    }
}