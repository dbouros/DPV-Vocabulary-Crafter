package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VocabularyManagerController {

    private final WebDAOServer web_dao_svr = new WebDAOServer();
    private final VocabularyManipulation vocabularyManipulation = new VocabularyManipulation();
    private final Model origDPV = web_dao_svr.getDPVghb();
    private Model tempDPV;

    // GET Command: "curl -X GET http://localhost:8080/api/viewDPV/id"
    @GetMapping("/viewDPV/{id}")
    public String viewDPV(@PathVariable("id") Integer id){

        String modelString;

        if (id.equals(0)){
            modelString = vocabularyManipulation.View(origDPV);
        }else if (id.equals(1)){
            modelString = vocabularyManipulation.View(tempDPV);
        }else{
            System.out.println("Non-valid 'id' for model given! Only two models exist that can be printed");
            // modelString = "" == modelString.isEmpty()
            modelString = "";
        }

        if (modelString.isEmpty()) {
            System.out.println("String representation of model is null!");
            return null;
        }

        return modelString;
    }

    /*
    1) ResponseEntity: Represents an HTTP response.
    2) <byte[]>: Specifies the type of the response body, in this case, it's a ByteArray "byte[]".
    Allows for more control over the HTTP response sent back to the client. You can set the response
    status, headers, and body content as needed.
    */

    // GET Command: "curl -X GET http://localhost:8080/api/downloadDPVrdfFile"
    @GetMapping("/downloadDPVrdfFile")
    public ResponseEntity<byte[]> downloadDPVrdfFile(){

        // For github's "update" purposes, change from "tempDPV" to "origDPV".
        Model model = tempDPV;

        // Convert RDF Model to RDF/XML format.
        byte[] modelBytes = web_dao_svr.convertModelToRDFXMLBtArr(model);

        // Object "headers" is an "HttpHeaders" object that specifies the content type of the response.
        // Set HTTP headers.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        headers.setContentLength(modelBytes.length);
        headers.setContentDispositionFormData("filename", "myDPV.rdf");
        /*
         The filename: "?.rdf" will be the suggested name by the "Server" when we try to access
         the API from the browser, but when the code of the "Client" runs, the specified file name
         will be the one that will be downloaded.
        */

        return new ResponseEntity<>(modelBytes, headers, HttpStatus.OK);

    }

    // POST Command: "curl -X POST -H "Content-Type: ..." -d @tempDPV.rdf http://localhost:8080/api/uploadDPVrdfFile"
    @PostMapping("/uploadDPVrdfFile")
    public ResponseEntity<byte[]> uploadDPVrdfFile(@RequestBody byte[] fileBytes){

        try {
            tempDPV = web_dao_svr.convertRDFXMLBtArrToModel(fileBytes);

            // Checking "tempDPV" to see if it uploaded successfully from "Client" to "Server".
            if (tempDPV.isEmpty()) {
                System.out.println("Temporary model upload failed! There is something wrong with the data given from the Client!");
                throw new NullPointerException("model is empty!");
            }else {
                System.out.println("Server: File Uploaded Successfully!");
            }

            // 1) ResponseEntity.ok() creates a builder with the HTTP status set to 200 (OK).
            // 2) .build() builds the ResponseEntity object using the settings configured with the builder. In this
            // case, it sets the HTTP status to 200 (OK) and empty body.
            return ResponseEntity.ok().build();
        }catch (Exception e){
            System.out.println("Server: Upload Failed!, Exception occurred! INTERNAL_SERVER_ERROR");
            // 1) HttpStatus.INTERNAL_SERVER_ERROR = HTTP status of 500 (Internal Server Error).
            // 2) .build() empty body will be returned here as well.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from REST controller!";
    }
}
