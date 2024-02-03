package com.example.paralleldocuments;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController ()
@Slf4j
public class CrptApi {
    private static final TimeUnit DEFAULT_TIME_UNIT = TimeUnit.SECONDS;
    private static final int DEFAULT_REQUEST_LIMIT = 10;
    private final ThreadManager threadManager;

    public CrptApi() {
        this.threadManager = new ThreadManager(DEFAULT_TIME_UNIT, DEFAULT_REQUEST_LIMIT);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> postDocument(@RequestBody Document document) {
        log.info("post Document request accepted, document={}", document);
        threadManager.manageDocumentCreation(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
