package com.DPV_Vocabulary_Crafter.Client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class WebDAOClient extends LocalDAOClient{

    private final RestTemplate restTemplate;

    public WebDAOClient(){
        this.restTemplate = new RestTemplate();
    }

    public void getCreateNewDPV(String Url){
        ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
        if (response.getStatusCode().is2xxSuccessful()){
            System.out.println(response.getBody());
        }else{
            System.out.println("Internal Server Error! Status code: " + response.getStatusCode());
        }
    }

    public void getEditDPV(String Url){
        ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
        if (response.getStatusCode().is2xxSuccessful()){
            System.out.println(response.getBody());
        }else{
            System.out.println("Internal Server Error! Status code: " + response.getStatusCode());
        }
    }

    public String getViewDPV(String Url){

        ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
        String model_String = response.getBody();

        if (response.getStatusCode().is2xxSuccessful()){
            if (model_String != null) {
                return model_String;
            }else {
                return "Model is empty! String representation of model is null.";
            }
        }else{
            return "Internal Server Error! Status code: " + response.getStatusCode();
        }
    }

    public String getSearchDPV(String Url){

        ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
        String model_String = response.getBody();

        if (response.getStatusCode().is2xxSuccessful()){
            if (model_String != null) {
                return model_String;
            }else {
                return "Search is empty! String representation of model is null.";
            }
        }else{
            return "Internal Server Error! Status code: " + response.getStatusCode();
        }
    }

    public void getDownloadDPVrdfFile(String Url, String folder_Path, String filename){

        ResponseEntity<byte[]> response = restTemplate.getForEntity(Url, byte[].class);

        if (response.getStatusCode().is2xxSuccessful()) {
            writeVocabularyToFile(response.getBody(), folder_Path, filename);
            System.out.print("File Saved Successfully! ");
        } else {
            System.out.println("Failed to save file! Status code: " + response.getStatusCode());
        }
    }

    public void postUploadDPVrdfFile(String Url, String folder_Path, String filename){

        try {

            byte[] fileBytes = readVocabularyFromFile(folder_Path, filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            // Set the byte array as the request body and the content type header
            HttpEntity<byte[]> request = new HttpEntity<>(fileBytes, headers);

            // POST request
            ResponseEntity<byte[]> response = restTemplate.exchange(Url, HttpMethod.POST, request, byte[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // byte[] responseBody = response.getBody();
                System.out.print("File Loaded Successfully! ");
            } else {
                throw new Exception();
            }

        }catch (Exception e){
            System.out.println("Error: Failed to load file!");
        }
    }

}