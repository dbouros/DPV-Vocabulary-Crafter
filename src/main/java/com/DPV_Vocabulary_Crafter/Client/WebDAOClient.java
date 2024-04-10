package com.DPV_Vocabulary_Crafter.Client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class WebDAOClient {

    private final RestTemplate restTemplate;
    private final LocalDAOClient lcl_dao_clnt;

    public WebDAOClient(){
        this.restTemplate = new RestTemplate();
        this.lcl_dao_clnt = new LocalDAOClient();
    }

    public String get_view_DPV(String Url){

        ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
        String model_String = response.getBody();

        if (model_String != null) {
            return model_String;
        }else {
            System.out.println("String representation of model is null!");
            return null;
        }

    }

    public void get_download_DPV_RDFFile(String Url, String folder_Path, String filename){

        ResponseEntity<byte[]> response = restTemplate.getForEntity(Url, byte[].class);

        if (response.getStatusCode().is2xxSuccessful())
            lcl_dao_clnt.write_VocabularyToFile(response.getBody(), folder_Path, filename);
        else
            System.out.println("Failed to download file! Status code: " + response.getStatusCode());
    }

    public void get_upload_DPV_RDFFile(String Url, String folder_Path, String filename){

        try {

            byte[] fileBytes = lcl_dao_clnt.read_VocabularyFromFile(folder_Path, filename);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);

            // Set the byte array as the request body and the content type header
            HttpEntity<byte[]> request = new HttpEntity<>(fileBytes, headers);

            // POST request
            ResponseEntity<byte[]> response = restTemplate.exchange(Url, HttpMethod.POST, request, byte[].class);

            if (response.getStatusCode().is2xxSuccessful()) {
                // byte[] responseBody = response.getBody();
                System.out.println("File uploaded successfully!");
            } else {
                throw new Exception("File failed to upload! Status code: " + response.getStatusCode());
            }

        }catch (Exception e){
            System.out.println("Upload Failed!, Exception occurred!");
        }
    }

    public String get_hello(String Url){

        ResponseEntity<String> response = restTemplate.getForEntity(Url, String.class);
        if (response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else{
            return "Internal Server Error! Status code: " + response.getStatusCode();
        }
    }

}