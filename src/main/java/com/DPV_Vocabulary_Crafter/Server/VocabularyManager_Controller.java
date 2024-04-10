package com.DPV_Vocabulary_Crafter.Server;

import org.eclipse.rdf4j.model.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VocabularyManager_Controller {

    private final Web_DAO_Server web_dao_svr = new Web_DAO_Server();

}
