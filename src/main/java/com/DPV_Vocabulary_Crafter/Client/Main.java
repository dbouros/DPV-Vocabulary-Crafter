package com.DPV_Vocabulary_Crafter.Client;

public class Main {
    public static void main(String[] args) {


        // Client's DAO object!
        WebDAOClient web_dao_clnt = new WebDAOClient();

        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.run();

        // Get a "Hello" response from "Server".
        //System.out.println(web_dao_clnt.getHello("http://localhost:8080/api/hello"));

        // View original DPV from "Server".
        //System.out.println("Original DPV View:");
        //System.out.println(web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/0"));

//======================================================================================================================
// Upload, View, Download Personal DPV from "Server".
        String folderPath = "C:\\Users\\lenovo\\Desktop";
        String filename = "dpv.rdf";
        // Upload personal DPV to "Server".
        //web_dao_clnt.postUploadDPVrdfFile("http://localhost:8080/api/uploadDPVrdfFile", folderPath, filename);

        // View uploaded personal DPV from "Server".
        //System.out.println("Personal DPV View:");
        //System.out.println(web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/1"));

        // Download personal DPV from "Server".
        //web_dao_clnt.getDownloadDPVrdfFile("http://localhost:8080/api/downloadDPVrdfFile", folderPath, filename);

//======================================================================================================================
// Create or Upload, Edit, View, Download Personal DPV (new_dpv.rdf) DPV from "Server".
        String filename2 = "new_dpv.rdf";

        // Create new empty personal DPV in "Server".
        //web_dao_clnt.getCreateNewDPV("http://localhost:8080/api/createNewDPV");

        // Upload personal DPV to "Server".
        //web_dao_clnt.postUploadDPVrdfFile("http://localhost:8080/api/uploadDPVrdfFile", folderPath, filename2);

        // Edit new empty personal DPV in "Server".
        String dpvSubject = "Compliance Unknown";

        // Removing(Trim) all possible spaces from "dpvSubject".
        dpvSubject = dpvSubject.replace(" ", "");


        // id == 0 (Method: add), id == 1 (Method: remove).
        //web_dao_clnt.getEditDPV("http://localhost:8080/api/editDPV/" + dpvSubject + "/0");

        // View new personal DPV from "Server".
        //System.out.println("Personal DPV View:");
        //System.out.println(web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/1"));

        // Download new personal DPV from "Server".
        //web_dao_clnt.getDownloadDPVrdfFile("http://localhost:8080/api/downloadDPVrdfFile", folderPath, filename2);

//======================================================================================================================
// Search Original DPV from "Server", Search Personal DPV from "Server". (Kept uploaded Personal DPV from above!)

    // First search - Single Term (Subject Match-up).

        // SOS!! -->> Make sure that the user doesn't input as a dpv term: "" or " ". Catch this in UI !!
        String dpvSubject2 = "Data Processor";

        // Removing(Trim) all possible spaces from "dpvSubject".
        dpvSubject2 = dpvSubject2.replace(" ", "");

        // Original DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 0, id == 0)
        //System.out.println("Original DPV Search - Single Term (Subject Match-up): " + dpvSubject2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/0/" + dpvSubject2 + "/0"));

        // Personal DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 1, id == 0)
        //System.out.println("Personal DPV Search - Single Term (Subject Match-up): " + dpvSubject2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/1/" + dpvSubject2 + "/0"));

    // Second search - All Terms (Subject Inclusion).

        // SOS!! -->> Make sure that the user doesn't input as a dpv term: "" or " ". Catch this in UI !!
        String dpvSubject3 = "Compliance";
        String dpvSubject4 = "Data";

        // Removing(Trim) all possible spaces from "dpvSubject".
        dpvSubject3 = dpvSubject3.replace(" ", "");
        dpvSubject4 = dpvSubject4.replace(" ", "");

        // Original DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 0, id == 1)
        //System.out.println("Original DPV Search - All Terms (Subject Inclusion): " + dpvSubject4);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/0/" + dpvSubject4 + "/1"));

        // Personal DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 1, id == 1)
        //System.out.println("Personal DPV Search - All Terms (Subject Inclusion): " + dpvSubject4);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/1/" + dpvSubject4 + "/1"));


    // Third search - All Terms (Predicate Match-up).

        // SOS!! -->> Make sure that the user doesn't input as a dpv term: "" or " ". Catch this in UI !!
        String dpvPredicate = "created";
        String dpvPredicate2 = "subClassOf";

        // Removing(Trim) all possible spaces from "dpvSubject".
        dpvPredicate = dpvPredicate.replace(" ", "");
        dpvPredicate2 = dpvPredicate2.replace(" ", "");

        // Original DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 0, id == 2)
        //System.out.println("Original DPV Search - All Terms (Predicate Match-up): " + dpvPredicate2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/0/" + dpvPredicate2 + "/2"));

        // Personal DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 1, id == 2)
        //System.out.println("Personal DPV Search - All Terms (Predicate Match-up): " + dpvPredicate2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/1/" + dpvPredicate2 + "/2"));

    // Forth search - All Terms (Object Match-up).

        // SOS!! -->> Make sure that the user doesn't input as a dpv term: "" or " ". Catch this in UI !!
        String dpvObject = "2022-09-07";
        String dpvObject2 = "2021-04-07";

        // SOS!! -->> DO NOT TRIM THE 'OBJECT' OF THE SPACES(" ").

        // Original DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 0, id == 3)
        //System.out.println("Original DPV Search - All Terms (Object Match-up): " + dpvObject2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/0/" + dpvObject2 + "/3"));

        // Personal DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 1, id == 3)
        //System.out.println("Personal DPV Search - All Terms (Object Match-up): " + dpvObject2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/1/" + dpvObject2 + "/3"));

    // Fifth search - All Terms (Subject Inclusion & Predicate Match-up).

        // SOS!! -->> Make sure that the user doesn't input as a dpv term: "" or " ". Catch this in UI !!
        // Two tests with previously used variables.
        // 1) dpvSubject3 + dpvPredicate.
        // 2) dpvSubject4 + dpvPredicate2.

        // SOS!! -->> VARIABLES ARE ALREADY TRIMMED OF THE SPACES(" ").

        // Original DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 0, id == 4)
        //System.out.println("Original DPV Search - All Terms (Subject Inclusion & Predicate Match-up): " + dpvSubject4 + ", " + dpvPredicate2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/0/" + dpvSubject4 + "/" + dpvPredicate2 + "/4"));
        // Personal DPV.
        // Note: The message below can be a good 'Panel title'. (voc_id == 1, id == 4)
        //System.out.println("Personal DPV Search - All Terms (Subject Inclusion & Predicate Match-up): " + dpvSubject4 + ", " + dpvPredicate2);
        //System.out.println(web_dao_clnt.getSearchDPV("http://localhost:8080/api/searchDPV/1/" + dpvSubject4 + "/" + dpvPredicate2 + "/4"));


    }
}
