package com.DPV_Vocabulary_Crafter.Client;

import java.util.Scanner;

public class ConsoleUI {

    private final InputValidator inputValidator;
    private final WebDAOClient web_dao_clnt;
    private final UIPanel uiPanel;

    private boolean exit;
    private boolean firstMenu;
    private boolean createNew;
    private boolean load;
    private boolean save;
    private boolean edit;

    public ConsoleUI(){
        this.inputValidator = new InputValidator();
        this.web_dao_clnt = new WebDAOClient();
        this.uiPanel = new UIPanel();

        this.exit = false;
        this.firstMenu = true;
        this.createNew = false;
        this.load = false;
        this.save = false;
        this.edit = false;
    }

    public void run(){
        try (Scanner input = new Scanner(System.in)){
            while (!exit){
                if (firstMenu){
                    firstMenu = false;

                    mainMenu1(input);
                } else if (createNew) {
                    createNew = false;

                    mainMenu2(input);
                }else if (load) {
                    if (!edit){
                        load = false;

                        mainMenu3(input);
                    }else {
                        load = false;
                        edit = false;

                        mainMenu4(input);
                    }
                }
            }
        }

    }

    // First Menu. (No choices were made!)
    private void mainMenu1(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Main Menu:");
            System.out.println();
            System.out.println("1. Original DPV");
            System.out.println("2. Create New Personal DPV");
            System.out.println("3. Load Personal DPV");
            System.out.println("4. Help");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                originalDPVMenu(input);
            } else if (option.equals("2")) {
                createNewPersonalDPV();
                return;
            } else if (option.equals("3")) {
                loadPersonalDPV(input);
                return;
            } else if (option.equals("4")) {
                // TODO: Write ALL the 'Help.txt' files in the 'resources' folder.
                uiPanel.run("Help Main Menu", web_dao_clnt.readTextFromResources("HelpMenu1.txt"));
            } else if (option.equals("0")) {
                exit = true;

                System.out.println("Bye Bye!");
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    // Second Menu. (User chose option 2!)
    private void mainMenu2(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Main Menu:");
            System.out.println();
            System.out.println("1. Original DPV");
            System.out.println("2. Personal DPV");
            System.out.println("3. Re-Create New Personal DPV");
            System.out.println("4. Save Personal DPV");
            System.out.println("5. Load Personal DPV");
            System.out.println("6. Help");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                originalDPVMenu(input);
            } else if (option.equals("2")) {
                personalDPVMenu(input);
            } else if (option.equals("3")) {
                createNewPersonalDPV();
                return;
            } else if (option.equals("4")) {
                savePersonalDPV(input);
            } else if (option.equals("5")) {
                loadPersonalDPV(input);
                return;
            } else if (option.equals("6")) {
                uiPanel.run("Help Main Menu", web_dao_clnt.readTextFromResources("HelpMenu2.txt"));
            } else if (option.equals("0")) {

                if (!save) {
                    saveBeforeExit(input);
                }
                exit = true;

                System.out.println("Bye Bye!");
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    // Third Menu. (User chose option 3 without editing!)
    private void mainMenu3(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Main Menu:");
            System.out.println();
            System.out.println("1. Original DPV");
            System.out.println("2. Personal DPV");
            System.out.println("3. Create New Personal DPV");
            System.out.println("4. Re-Load Personal DPV");
            System.out.println("5. Help");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                originalDPVMenu(input);
            } else if (option.equals("2")) {
                personalDPVMenu(input);
                if (edit) {
                    load = true;
                    return;
                }
            } else if (option.equals("3")) {
                createNewPersonalDPV();
                return;
            } else if (option.equals("4")) {
                loadPersonalDPV(input);
                return;
            } else if (option.equals("5")) {
                uiPanel.run("Help Main Menu", web_dao_clnt.readTextFromResources("HelpMenu3.txt"));
            } else if (option.equals("0")) {
                exit = true;

                System.out.println("Bye Bye!");
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }
    }

    // Forth Menu. (User chose option 3 and edited as well!)
    private void mainMenu4(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Main Menu:");
            System.out.println();
            System.out.println("1. Original DPV");
            System.out.println("2. Personal DPV");
            System.out.println("3. Create New Personal DPV");
            System.out.println("4. Save Personal DPV");
            System.out.println("5. Re-Load Personal DPV");
            System.out.println("6. Help");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                originalDPVMenu(input);
            } else if (option.equals("2")) {
                personalDPVMenu(input);
            } else if (option.equals("3")) {
                createNewPersonalDPV();
                return;
            } else if (option.equals("4")) {
                savePersonalDPV(input);
            } else if (option.equals("5")) {
                loadPersonalDPV(input);
                return;
            } else if (option.equals("6")) {
                uiPanel.run("Help Main Menu", web_dao_clnt.readTextFromResources("HelpMenu4.txt"));
            } else if (option.equals("0")) {

                if (!save) {
                    saveBeforeExit(input);
                }
                exit = true;

                System.out.println("Bye Bye!");
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    private void originalDPVMenu(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Original DPV:");
            System.out.println();
            System.out.println("1. View");
            System.out.println("2. Search");
            System.out.println("3. Help");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                view(0);
            } else if (option.equals("2")) {
                searchDPVMenu(input, 0);
            } else if (option.equals("3")) {
                uiPanel.run("Help Original DPV Menu", web_dao_clnt.readTextFromResources("HelpOriginalDPVMenu.txt"));
            } else if (option.equals("0")) {
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    private void personalDPVMenu(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Personal DPV:");
            System.out.println();
            System.out.println("1. Edit");
            System.out.println("2. View");
            System.out.println("3. Search");
            System.out.println("4. Help");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                editPersonalDPVMenu(input);
            } else if (option.equals("2")) {
                view(1);
            } else if (option.equals("3")) {
                searchDPVMenu(input, 1);
            } else if (option.equals("4")) {
                uiPanel.run("Help Personal DPV Menu", web_dao_clnt.readTextFromResources("HelpPersonalDPVMenu.txt"));
            } else if (option.equals("0")) {
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    private void editPersonalDPVMenu(Scanner input){

        clearConsole();
        while (true){
            System.out.println("Edit:");
            System.out.println();
            System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("3. Help");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                add(input);
            } else if (option.equals("2")) {
                remove(input);
            } else if (option.equals("3")) {
                uiPanel.run("Help Edit DPV Menu", web_dao_clnt.readTextFromResources("HelpEditDPVMenu.txt"));
            } else if (option.equals("0")) {
                return;
            } else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    private void searchDPVMenu(Scanner input, Integer voc_id){

        clearConsole();
        while (true){
            System.out.println("Search:");
            System.out.println();
            System.out.println("1. Single Term (Subject Match-up)");
            System.out.println("2. All Terms (Subject Inclusion)");
            System.out.println("3. All Terms (Predicate Match-up)");
            System.out.println("4. All Terms (Object Match-up)");
            System.out.println("5. All Terms (Subject Inclusion & Predicate Match-up)");
            System.out.println("6. Help");
            System.out.println("0. Back");
            System.out.println();
            System.out.print("Please select an option: ");

            String option = input.nextLine();

            if (option.equals("1")){
                searchSingleTerm(input, voc_id);
            } else if (option.equals("2")) {
                searchAllTermsSubject(input, voc_id);
            }else if (option.equals("3")) {
                searchAllTermsPredicate(input, voc_id);
            }else if (option.equals("4")) {
                searchAllTermsObject(input, voc_id);
            } else if (option.equals("5")) {
                searchAllTermsSubjectPredicate(input, voc_id);
            } else if (option.equals("6")) {
                uiPanel.run("Help Search DPV Menu", web_dao_clnt.readTextFromResources("HelpSearchDPVMenu.txt"));
            } else if (option.equals("0")) {
                return;
            }else {
                System.out.print("Invalid option! Press [T/t] to try again: ");
                inputValidator.pressT(input);
            }

            clearConsole();
        }

    }

    private void createNewPersonalDPV(){
        createNew = true;
        save = false;
        String Url = "http://localhost:8080/api/createNewDPV";
        web_dao_clnt.getCreateNewDPV(Url);
    }

    private void add(Scanner input){
        String Url = "http://localhost:8080/api/editDPV/";
        String dpvSubject;

        while (true) {
            System.out.print("Please type the 'subject' of the term to add: ");
            dpvSubject = input.nextLine();
            dpvSubject = dpvSubject.replace(" ", "");

            if (inputValidator.validateTerm(dpvSubject)){
                // id = "/0".
                Url = Url + dpvSubject + "/0";
                web_dao_clnt.getEditDPV(Url);

                edit = true;
                save = false;
                break;
            }else {
                System.out.println("Error: Invalid 'subject' given for method 'add'.");
            }
        }

    }

    private void remove(Scanner input){
        String Url = "http://localhost:8080/api/editDPV/";
        String dpvSubject;

        while (true) {
            System.out.print("Please type the 'subject' of the term to remove: ");
            dpvSubject = input.nextLine();
            dpvSubject = dpvSubject.replace(" ", "");

            if (inputValidator.validateTerm(dpvSubject)){
                // id = "/1".
                Url = Url + dpvSubject + "/1";
                web_dao_clnt.getEditDPV(Url);

                edit = true;
                save = false;
                break;
            }else {
                System.out.println("Error: Invalid 'subject' given for method 'remove'.");
            }
        }

    }

    private void view(Integer voc_id){

        String Url = "http://localhost:8080/api/viewDPV/" + voc_id;
        String modelString = web_dao_clnt.getViewDPV(Url);

        if (voc_id.equals(0)){
            uiPanel.run("Original DPV View", modelString);
        }else {
            uiPanel.run("Personal DPV View", modelString);
        }

    }

    // First Search.
    private void searchSingleTerm(Scanner input, Integer voc_id){
        String Url = "http://localhost:8080/api/searchDPV/" + voc_id + "/";
        String dpvSubject;

        while (true){
            System.out.print("Please type the 'subject' of the term to search: ");
            dpvSubject = input.nextLine();
            dpvSubject = dpvSubject.replace(" ", "");

            if (inputValidator.validateTerm(dpvSubject)){
                // id = "/0".
                Url = Url + dpvSubject + "/0";
                String modelString = web_dao_clnt.getSearchDPV(Url);

                if (voc_id.equals(0)){
                    uiPanel.run("Original DPV Search - Single Term (Subject Match-up): " + dpvSubject, modelString);
                }else {
                    uiPanel.run("Personal DPV Search - Single Term (Subject Match-up): " + dpvSubject, modelString);
                }
                break;
            }else {
                System.out.println("Error: Invalid 'subject' given for method 'searchSingleTerm'.");
            }
        }
    }

    // Second Search.
    private void searchAllTermsSubject(Scanner input, Integer voc_id){
        String Url = "http://localhost:8080/api/searchDPV/" + voc_id + "/";
        String dpvSubject;

        while (true){
            System.out.print("Please type the 'subject' of the term to search: ");
            dpvSubject = input.nextLine();
            dpvSubject = dpvSubject.replace(" ", "");

            if (inputValidator.validateTerm(dpvSubject)){
                // id = "/1".
                Url = Url + dpvSubject + "/1";
                String modelString = web_dao_clnt.getSearchDPV(Url);

                if (voc_id.equals(0)){
                    uiPanel.run("Original DPV Search - All Terms (Subject Inclusion): " + dpvSubject, modelString);
                }else {
                    uiPanel.run("Personal DPV Search - All Terms (Subject Inclusion): " + dpvSubject, modelString);
                }
                break;
            }else {
                System.out.println("Error: Invalid 'subject' given for method 'searchAllTermsSubject'.");
            }
        }
    }

    // Third Search.
    private void searchAllTermsPredicate(Scanner input, Integer voc_id){
        String Url = "http://localhost:8080/api/searchDPV/" + voc_id + "/";
        String dpvPredicate;

        while (true){
            System.out.print("Please type the 'predicate' of the term to search: ");
            dpvPredicate = input.nextLine();
            dpvPredicate = dpvPredicate.replace(" ", "");

            if (inputValidator.validateTerm(dpvPredicate)){
                // id = "/2".
                Url = Url + dpvPredicate + "/2";
                String modelString = web_dao_clnt.getSearchDPV(Url);

                if (voc_id.equals(0)){
                    uiPanel.run("Original DPV Search - All Terms (Predicate Match-up): " + dpvPredicate, modelString);
                }else {
                    uiPanel.run("Personal DPV Search - All Terms (Predicate Match-up): " + dpvPredicate, modelString);
                }
                break;
            }else {
                System.out.println("Error: Invalid 'predicate' given for method 'searchAllTermsPredicate'.");
            }
        }
    }

    // Forth Search.
    private void searchAllTermsObject(Scanner input, Integer voc_id){
        String Url = "http://localhost:8080/api/searchDPV/" + voc_id + "/";
        String dpvObject;

        while (true){
            System.out.print("Please type the 'object' of the term to search: ");
            dpvObject = input.nextLine();
            // Never replace " " with "" for 'Objects'.

            if (inputValidator.validateTerm(dpvObject)){
                // id = "/3".
                Url = Url + dpvObject + "/3";
                String modelString = web_dao_clnt.getSearchDPV(Url);

                if (voc_id.equals(0)){
                    uiPanel.run("Original DPV Search - All Terms (Object Match-up): " + dpvObject, modelString);
                }else {
                    uiPanel.run("Personal DPV Search - All Terms (Object Match-up): " + dpvObject, modelString);
                }
                break;
            }else {
                System.out.println("Error: Invalid 'object' given for method 'searchAllTermsObject'.");
            }

        }
    }

    // Fifth Search.
    private void searchAllTermsSubjectPredicate(Scanner input, Integer voc_id){
        String Url = "http://localhost:8080/api/searchDPV/" + voc_id + "/";
        String dpvSubject;
        String dpvPredicate;

        while (true){
            System.out.print("Please type the 'subject' of the term to search: ");
            dpvSubject = input.nextLine();
            System.out.print("Please type the 'predicate' of the term to search: ");
            dpvPredicate = input.nextLine();

            dpvSubject = dpvSubject.replace(" ", "");
            dpvPredicate = dpvPredicate.replace(" ", "");

            if (inputValidator.validateTerm(dpvSubject) && inputValidator.validateTerm(dpvPredicate)){
                // id = "/4".
                Url = Url + dpvSubject + "/" + dpvPredicate + "/4";
                String modelString = web_dao_clnt.getSearchDPV(Url);

                if (voc_id.equals(0)){
                    uiPanel.run("Original DPV Search - All Terms (Subject Inclusion & Predicate Match-up): " + dpvSubject + ", " + dpvPredicate, modelString);
                }else {
                    uiPanel.run("Personal DPV Search - All Terms (Subject Inclusion & Predicate Match-up): " + dpvSubject + ", " + dpvPredicate, modelString);
                }
                break;
            }else if (!inputValidator.validateTerm(dpvSubject)){
                System.out.println("Error: Invalid 'subject' given for method 'searchAllTermsSubjectPredicate'.");
            }else {
                System.out.println("Error: Invalid 'predicate' given for method 'searchAllTermsSubjectPredicate'.");
            }
        }
    }

    /*
     No matter the pre-specified "filename" from the "Server", we as a "Client" can specify our own
     preferred name for the file when we save it. Using the name of an already existing file will
     overwrite that file with the one we will save.
    */
    private void savePersonalDPV(Scanner input){
        String Url = "http://localhost:8080/api/downloadDPVrdfFile";
        String folder_path;
        String filename;

        while (true) {
            System.out.print("Please type the 'absolute' folder path: ");
            folder_path = input.nextLine();
            System.out.print("Please type the 'name' of the file to save: ");
            filename = input.nextLine();

            if (inputValidator.validateFolder(folder_path) && inputValidator.validateFilename(filename)){

                web_dao_clnt.getDownloadDPVrdfFile(Url, folder_path, filename);
                System.out.println("File: '" + filename + "'");

                save = true;
                break;
            } else if (!inputValidator.validateFolder(folder_path)) {
                System.out.println("Error: Invalid 'folder path' given for method 'save'.");
            }else {
                System.out.println("Error: Invalid 'filename' given for method 'save'.");
            }
        }

    }

    private void loadPersonalDPV(Scanner input){

        String Url = "http://localhost:8080/api/uploadDPVrdfFile";
        String folder_path;
        String filename;

        while(true){

            System.out.print("Please type the 'absolute' folder path: ");
            folder_path = input.nextLine();
            System.out.print("Please type the 'name' of the file to load: ");
            filename = input.nextLine();

            if (inputValidator.validateFolder(folder_path) && inputValidator.validateFile(folder_path, filename)){

                web_dao_clnt.postUploadDPVrdfFile(Url, folder_path, filename);
                System.out.println("File: '" + filename + "'");

                load = true;
                break;
            } else if (!inputValidator.validateFolder(folder_path)) {
                System.out.println("Error: Invalid 'folder path' given for method 'load'.");
            }else {
                System.out.println("Error: Invalid 'filename' given for method 'load'.");
            }
        }

    }

    private void saveBeforeExit(Scanner input){

        System.out.println("You have unsaved changes!");
        System.out.print("Do you want to save? [Y/N][y/n]: ");
        String button = input.nextLine();

        while (!button.equals("Y") && !button.equals("y") && !button.equals("N") && !button.equals("n")){
            System.out.print("Do you want to save? [Y/N][y/n]: ");
            button = input.nextLine();
        }

        if (button.equals("Y") || button.equals("y")){
            savePersonalDPV(input);
        }
    }

    // Clears the console by printing multiple new lines.
    /*
    Note -> Many IDEs including 'IntelliJ IDEA', do not fully support ANSI escape codes in their
    built-in consoles.

    // ANSI escape code to clear the console
        System.out.print("\033[H\033[2J");
        System.out.flush();

    For a cross-environment solution, sticking with the simpler method of printing multiple new lines
    is often more reliable and simpler to understand.
    */
    private void clearConsole() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }

}
