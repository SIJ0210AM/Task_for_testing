package com.example.paralleldocuments;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.TimedSemaphore;

@Slf4j
public class DocumentCreatorThread implements Runnable {
    private TimedSemaphore semaphore;
    private String name;
    private Document document;

    public DocumentCreatorThread(TimedSemaphore semaphore, String name, Document document) {
        this.semaphore = semaphore;
        this.name = name;
        this.document = document;
    }

    public void run() {
        try {
            log.info(this.name + " waiting for permission");
            semaphore.acquire();
            log.info(this.name + " got permission");
            Thread.sleep(150);
            createDocument(document);
        } catch (InterruptedException e) {
            log.info(e.getMessage());
        }
    }

    private void createDocument(Document document) {
        log.info("Document created, document={}", document);
    }
}
