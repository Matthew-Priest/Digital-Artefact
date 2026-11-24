package caseManager;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArtefactTest {

    private ArrayList<Case> cases;

    // Runs before EVERY test, so each test gets a fresh list of 2 cases
    @BeforeEach
    void setUp(){
        cases = new ArrayList<>();
        cases.add(new Case(1, "Login bug", "Users can't log in", "Matthew", Status.active));
        cases.add(new Case(2, "Crash on save", "App crashes when saving", "Sam", Status.in_testing));
    }
    //Find Case via number 
    @Test 
    void findCaseViaNumberWithMatchingCase(){
        Scanner input = new Scanner("2\n");
        Case found = Artefact.findCaseViaNumber(input, cases);
        assertEquals("Crash on save", found.getCaseTitle());
    }
}