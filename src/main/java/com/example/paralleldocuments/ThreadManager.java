package com.example.paralleldocuments;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.TimedSemaphore;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadManager {
    private final TimedSemaphore semaphore;
    private int threadCounter;

    public ThreadManager(TimeUnit timeUnit, int requestLimit) {
        this.semaphore = new TimedSemaphore(1, timeUnit, requestLimit);//todo check
        this.threadCounter = 0;
    }

    public void manageDocumentCreation(Document document) {
        threadCounter++;
        new Thread(new DocumentCreatorThread(semaphore, "DocumentCreatorThread " + threadCounter, document)).start();
    }

}
