package com.DPV_Vocabulary_Crafter.Client;

public class Main {
    public static void main(String[] args) {


        // Client's DAO object!
        WebDAOClient web_dao_clnt = new WebDAOClient();

        // Get a "Hello" response from "Server".
        System.out.println(web_dao_clnt.getHello("http://localhost:8080/api/hello"));

        // View original DPV from "Server".
        System.out.println("Original DPV:");
        web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/0");

        String folder_Path = "C:\\Users\\lenovo\\Desktop";
        String filename = "dpv.rdf";

        // Upload our personal DPV to "Server".
        web_dao_clnt.postUploadDPVrdfFile("http://localhost:8080/api/uploadDPVrdfFile", folder_Path, filename);

        // View our uploaded personal DPV from Server.
        System.out.println("Personal DPV:");
        web_dao_clnt.getViewDPV("http://localhost:8080/api/viewDPV/1");

        // Download our personal DPV from "Server".
        /*
			 No matter the specified "filename" from the "Server" we as a "Client" can specify our own
			 preferred name for the file when we download it. Keeping the name of an already existing
			 model will overwrite that model with the one we will download.
			*/
        web_dao_clnt.getDownloadDPVrdfFile("http://localhost:8080/api/downloadDPVrdfFile", folder_Path, filename);

    }
}