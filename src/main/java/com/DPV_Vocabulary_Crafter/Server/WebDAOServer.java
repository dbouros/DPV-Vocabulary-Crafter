package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.RDFFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class WebDAOServer {
    private final RestTemplate restTemplate;
    private final String dpv_Url;

    // Default Constructor!
    public WebDAOServer(){
        this.restTemplate = new RestTemplate();
        this.dpv_Url = "https://raw.githubusercontent.com/w3c/dpv/master/dpv/dpv.rdf";
    }

    public Model getDPVghb(){

        try {

            ResponseEntity<String> response = restTemplate.getForEntity(dpv_Url, String.class);

            if (response.getStatusCode() != HttpStatus.OK)
                throw new Exception();

            String rdfData = response.getBody();
            InputStream inputStream;

            if (rdfData != null) {
                inputStream = new ByteArrayInputStream(rdfData.getBytes(StandardCharsets.UTF_8));
            }else {
                throw new Exception("rdfData is null");
            }

            Model model = Rio.parse(inputStream, "", RDFFormat.RDFXML);

            return model;

        }catch (Exception e){
            System.out.println("GET request for the original DPV from github failed.\nFailed to fetch the original DPV from github!");
            throw new NullPointerException();
        }
    }
    // Convert "Model" to RDF/XML ByteArray.
    public byte[] convertModelToRDFXMLBtArr(Model model){
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            Rio.write(model, outputStream, RDFFormat.RDFXML);
            return outputStream.toByteArray();
        }catch (IOException e){
            System.out.println("IOException in method: 'convertModelToRDFXMLBtArr'.");
            return new byte[0];
        }
    }

    // Convert RDF/XML ByteArray to "Model".
    public Model convertRDFXMLBtArrToModel(byte[] fileBytes){
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes)){
            return Rio.parse(inputStream, "", RDFFormat.RDFXML);
        }catch (IOException e){
            System.out.println("IOException in method: 'convertRDFXMLBtArrToModel'.");
            return null;
        }
    }
}
