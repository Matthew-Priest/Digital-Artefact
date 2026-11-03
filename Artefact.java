import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Artefact {
    public static void main(String[] args) {
        // HERE WILL LOAD CASES FROM TEXT FILE AND ADD EACH TO AN ARRAYLIST SO THEY CAN BE EDITED, THEN REPRINTED TO FILE BEFORE CLOSING OF PROGRAM
        ArrayList<Case> caseList = LoadCases();       
        System.out.println("This is a case management system");
        String answer = "";
        Scanner scanner = new Scanner(System.in);

        while (!answer.equals("breakout")) {
            System.out.println("Would you like to create a case, edit a case, or delete a case?");
            answer = scanner.nextLine().toLowerCase();
            switch (answer) {
                case "create":
                    createCase(scanner, caseList);
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
            System.out.println("Would you like to continue?");
            answer = scanner.nextLine();
            if(answer.equals("no")){
                answer = "breakout";
            }
        }
        pasteCases(caseList);
        scanner.close();
    }

    public static void createCase(Scanner scanner, ArrayList<Case> caseList) {
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
        caseList.add(newCase);
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
                    fields = new String[5]; //Validation so that if user leaves a box empty, info isn't being reused from past cases
                }
                else {
                    int startOfValue = line.indexOf(":");  
                    if(startOfValue != -1){                 //Validation here in the case a line doesn't contain a colon
                        fields[count]  = line.substring(startOfValue+ 2);
                        count += 1;
                    } 
                }    
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
        return caseList;
    }

    public static void pasteCases(ArrayList<Case> caseList) {
        try {
            new FileWriter("caseList.txt", false).close();
        } catch (Exception e) {
        }
        for(int i=0; i<caseList.size(); i++){
            caseList.get(i).writeToFile();
        }
    }
}