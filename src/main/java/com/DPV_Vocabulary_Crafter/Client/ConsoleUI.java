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
    public void mainMenu1(Scanner input){

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
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        }

    }

    // Second Menu. (User chose option 2!)
    public void mainMenu2(Scanner input){

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

            return;
        }

    }

    // Third Menu. (User chose option 3 without editing!)
    public void mainMenu3(Scanner input){

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

        }
    }

    // Forth Menu. (User chose option 3 and edited as well!)
    public void mainMenu4(Scanner input){

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

        }

    }

    public void originalDPVMenu(Scanner input){

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
            }else {
                System.out.println("Invalid option! Please try again.");
            }
        }

    }

    public void personalDPVMenu(Scanner input){

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

        }

    }

    public void editPersonalDPVMenu(Scanner input){

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

        }

    }

    public void searchDPVMenu(Scanner input){

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

        }

    }

    public void view(Integer voc_id){
        if (voc_id.equals(0) || voc_id.equals(1)){
            String Url = "http://localhost:8080/api/viewDPV/" + voc_id;
            String modelString = web_dao_clnt.getViewDPV(Url);
            if (voc_id.equals(0)){
                uiPanel.run("Original DPV View", modelString);
            }else {
                uiPanel.run("Personal DPV View", modelString);
            }
        }else {
            System.out.println("Error: Invalid 'vocabulary id' given in URL for method 'view'.");
        }
    }

    public void createNewPersonalDPV(){
        createNew = true;
        String Url = "http://localhost:8080/api/createNewDPV";
        web_dao_clnt.getCreateNewDPV(Url);
    }

    public void loadPersonalDPV(){}

}
