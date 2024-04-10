package com.DPV_Vocabulary_Crafter.Client;

public class Main {
    public static void main(String[] args) {


        // Client's DAO object!
        Web_DAO_Client web_dao_clnt = new Web_DAO_Client();

        // Get a "Hello" response from "Server".
        System.out.println(web_dao_clnt.get_hello("http://localhost:8080/api/hello"));

        // View original DPV from "Server".
        System.out.println("Original DPV:");
        System.out.println(web_dao_clnt.get_view_DPV("http://localhost:8080/api/view_DPV/0"));

        String folder_Path = "C:\\Users\\lenovo\\Desktop";
        String filename = "dpv.rdf";

        // Upload our personal DPV to "Server".
        web_dao_clnt.get_upload_DPV_RDFFile("http://localhost:8080/api/upload_DPV_RDFFile", folder_Path, filename);

        // View our uploaded personal DPV from Server.
        System.out.println("Personal DPV:");
        System.out.println(web_dao_clnt.get_view_DPV("http://localhost:8080/api/view_DPV/1"));

        // Download our personal DPV from "Server".
			/*
			 No matter the specified "filename" from the "Server" we as a "Client" can specify our own
			 preferred name for the file when we download it.
			*/
        web_dao_clnt.get_download_DPV_RDFFile("http://localhost:8080/api/download_DPV_RDFFile", folder_Path, filename);

    }
}