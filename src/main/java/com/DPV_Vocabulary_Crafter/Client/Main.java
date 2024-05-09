package com.DPV_Vocabulary_Crafter.Client;

public class Main {
    public static void main(String[] args) {


        // Client's DAO object!
        WebDAOClient web_dao_clnt = new WebDAOClient();

        // Get a "Hello" response from "Server".
        System.out.println(web_dao_clnt.getHello("http://localhost:8080/api/hello"));

        // View original DPV from "Server".
        //System.out.println("Original DPV:");
        //System.out.println(web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/0"));

// Upload, View, Download Personal DPV from "Server".
        String folderPath = "C:\\Users\\lenovo\\Desktop";
        String filename = "dpv.rdf";
        // Upload personal DPV to "Server".
        //web_dao_clnt.postUploadDPVrdfFile("http://localhost:8080/api/uploadDPVrdfFile", folderPath, filename);

        // View uploaded personal DPV from "Server".
        //System.out.println("Personal DPV:");
        //System.out.println(web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/1"));

        // Download personal DPV from "Server".
        /*
			 No matter the specified "filename" from the "Server" we as a "Client" can specify our own
			 preferred name for the file when we download it. Keeping the name of an already existing
			 model will overwrite that model with the one we will download.
			*/
        //web_dao_clnt.getDownloadDPVrdfFile("http://localhost:8080/api/downloadDPVrdfFile", folderPath, filename);

// Create or Upload, Edit, View, Download new DPV from "Server".
        String filename2 = "new_dpv.rdf";

        // Create new empty personal DPV in "Server".
        //web_dao_clnt.getCreateNewDPV("http://localhost:8080/api/createNewDPV");

        // Upload personal DPV to "Server".
        web_dao_clnt.postUploadDPVrdfFile("http://localhost:8080/api/uploadDPVrdfFile", folderPath, filename2);

        // Edit new empty personal DPV in "Server".
        String dpvSubject = "Compliance Unknown";
        // Removing all possible spaces from "dpvTerm".
        dpvSubject = dpvSubject.replace(" ", "");
        // id == 0 (Method: add), id == 1 (Method: remove).
        web_dao_clnt.getEditDPV("http://localhost:8080/api/editDPV/" + dpvSubject + "/0");

        // View new personal DPV from "Server".
        System.out.println("Personal DPV:");
        System.out.println(web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/1"));

        // Download new personal DPV from "Server".
        web_dao_clnt.getDownloadDPVrdfFile("http://localhost:8080/api/downloadDPVrdfFile", folderPath, filename2);
    }
}


// UI Panel checking code. (Method: "main()") !!
/*
    UIPanel uiPanel = new UIPanel();
        Scanner scanner = new Scanner(System.in);
        int command;
        blankLines15();
        while (true){
            System.out.println("Press '>1' to keep looping.");
            System.out.println("Press '1' to launch panel.");
            System.out.println("Press '0' to exit the program.");
            System.out.print("Command: ");

            command = scanner.nextInt();

            if ( command == 0) break;
            if (command == 1) uiPanel.run();
            blankLines15();
        }

        scanner.nextLine(); // Clear the scanner's buffer!
        System.out.print("Please give a Folder path: ");
        String folder_Path = scanner.nextLine();
        System.out.println("Folder Path: " + folder_Path);

        System.out.println("Bye bye!");

    }

    public static void blankLines15(){
        for (int i=0; i<15; i++) System.out.println();
    }
*/
