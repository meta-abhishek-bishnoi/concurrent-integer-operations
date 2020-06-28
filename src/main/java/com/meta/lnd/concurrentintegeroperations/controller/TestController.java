package com.meta.lnd.concurrentintegeroperations.controller;

import com.meta.lnd.concurrentintegeroperations.service.ConcurrentDataStructure;
import com.meta.lnd.concurrentintegeroperations.service.IncomingDataList;
import com.meta.lnd.concurrentintegeroperations.service.PrimaryResultData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
  * TestController
  *
  * @author Abhishek Bishnoi
  * 28-Jun-2020, 9:11 PM
*/
@RestController
@RequestMapping("/test")
public class TestController {
  @GetMapping
  public ResponseEntity<Object> getAllAdmissionMode() {
    try {
      ConcurrentDataStructure concurrentDataStructure = new ConcurrentDataStructure();
      IncomingDataList incomingDataList = new IncomingDataList(concurrentDataStructure);
      PrimaryResultData primaryResultData = new PrimaryResultData(concurrentDataStructure);
      Thread threadIncomingDataList = new Thread(incomingDataList);
      Thread threadPrimaryDataList = new Thread(primaryResultData);
      threadIncomingDataList.start();
      threadPrimaryDataList.start();
      return new ResponseEntity<>("true", new HttpHeaders(), HttpStatus.OK);
    }catch (Exception e){
      e.printStackTrace();
      return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
