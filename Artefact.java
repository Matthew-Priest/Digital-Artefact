import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Artefact {
    public static void main(String[] args) {
        ArrayList<Case> caseList = LoadCases();       
        System.out.println("This is a case management system");
        String answer = "";
        Scanner scanner = new Scanner(System.in);

        while (!answer.equals("breakout")) {
            System.out.println("Would you like to create a case, edit a case, close a case or view a case?");
            answer = scanner.nextLine().toLowerCase();

            switch (answer) {
                case "create":
                    createCase(scanner, caseList);
                    break;
                case "edit":
                    editCase(scanner, caseList);
                    break;
                case "close":
                    closeCase(scanner, caseList);
                    break;
                case "view":
                    viewCase(scanner, caseList);
                    break;
                default:
                    break;
            }

            System.out.println("Would you like to continue?");
            answer = scanner.nextLine().toLowerCase();

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
        Status status;
        int highestCaseNum = 0;

        for(Case c : caseList) {
            if(c.getCaseNumber() > highestCaseNum){
                highestCaseNum = c.getCaseNumber();
            }
        }
        System.out.println("What should the case be called");
        caseName = scanner.nextLine();

        System.out.println("What is the problem");
        caseDescription = scanner.nextLine();

        System.out.println("Who will be in charge of the case");
        Dev = scanner.nextLine();

        status = Status.active;

        Case newCase = new Case(highestCaseNum + 1, caseName, caseDescription, Dev, status);
        caseList.add(newCase);
    }

    public static void editCase(Scanner scanner, ArrayList<Case> caseList) {
        String answer = "";
        Case selectedCase = findCase(scanner, caseList);

        if (selectedCase == null) {
            System.out.println("No case found");
            return;
        }

        while (!answer.equals("name") && !answer.equals("dev") && !answer.equals("description") && !answer.equals("status")) {
            System.out.println("What part of the case would you like to edit?");
            answer = scanner.nextLine().toLowerCase();
        }

        if(answer.equals("status")){
            System.out.println("Pick a status from these options");
            for(Status s : Status.values()){
                System.out.println();
            }
        }

        System.out.println("What would you like to change it to");
        String change = scanner.nextLine();

        if(answer.equals("status")){
            change = change.trim().toLowerCase().replace(" ", "_");
        }

        switch (answer) {
            case "name":
                selectedCase.setCaseTitle(change);
                break;
            case "description":
                selectedCase.setCaseDescription(change);
                break;
            case "dev":
                selectedCase.setCaseDev(change);
                break;
            case "status":
                try {
                    selectedCase.setCaseStatus(Status.valueOf(change));
                } catch (IllegalArgumentException e) {
                    System.out.println("That isn't a valid status, so nothing was changed."); //Validation so that program doesn't crash if the input doesn't match a value of Status
                }
                break;        
            default:
                break;
        }
    }

    public static void closeCase(Scanner scanner, ArrayList<Case> caseList) { //Add functionality to give a reason to why the case is being closed
        Case selectedCase = findCase(scanner, caseList);
        selectedCase.setCaseStatus(Status.closed);

    }

    public static void viewCase(Scanner scanner, ArrayList<Case> caseList){
        Case selectedCase = findCase(scanner, caseList);
        if(selectedCase == null){
            System.out.println("No case found");
        }
        System.out.println(selectedCase);
    }

    public static ArrayList<Case> LoadCases() {
        ArrayList<Case> caseList = new ArrayList<Case>();
        String[] fields = new String[6];
        int count = 0;
        String Value;

        try (
            BufferedReader br = new BufferedReader(new FileReader("caseList.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals("--------------------")){
                    Case newCase = new Case(fields[0], fields[1], fields[2], fields[3], fields[4], Status.valueOf(fields[5]));
                    caseList.add(newCase);
                    count = 0;
                    fields = new String[6]; //Validation so that if user leaves a box empty, info isn't being reused from past cases
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
        System.out.println("Successfully saved "+ caseList.size() +" cases");
    }

    public static Case findCaseViaNumber(Scanner scanner, ArrayList<Case> caseList){
        int answer = -1;
        try {
            System.out.println("What  case number do you want to look for");
            answer = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Input error");
        }

        for(Case c : caseList){
            if(c.getCaseNumber() == answer){
                return c;
            }
        }
        return null;
    }

    public static Case findCaseViaTitle(Scanner scanner,  ArrayList<Case> caseList){
        System.out.println("What search phrase do you want to look for in the title?");
        String answer = scanner.nextLine().toLowerCase();
        for(Case c : caseList){
             if(c.getCaseTitle().toLowerCase().contains(answer)){
                return c;
            }
        }
        return null;
    }

    public static Case findCase(Scanner scanner,  ArrayList<Case> caseList){
        System.out.println("Would you like to find your case through the case number or title");
        String answer = scanner.nextLine().toLowerCase();
        Case c;
        switch (answer) {
            case "case number":
                c = findCaseViaNumber(scanner, caseList);
                break;
            case "number":
                c = findCaseViaNumber(scanner, caseList);
                break;            
            case "title":
                c = findCaseViaTitle(scanner, caseList);
                break;
            default:
                c = null;
                break;
        }
        return c;
    }

}