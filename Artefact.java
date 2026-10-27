import java.util.Scanner;
public class Artefact{
    public static void main(String[] args){
        System.out.println("This is a case management system");
        String answer = "";
        Scanner scanner = new Scanner(System.in);
        while(!answer.equals("create") && !answer.equals("edit") && !answer.equals("delete")){
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
    public static void createCase(Scanner scanner){
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
        scanner.close();
        newCase.writeToFile();
    }
    public static void editCase(Scanner scanner){
        
    }
    public static void deleteCase(Scanner scanner){
        
    }
}