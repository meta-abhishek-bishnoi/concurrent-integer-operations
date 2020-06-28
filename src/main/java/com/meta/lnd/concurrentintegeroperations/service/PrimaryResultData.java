package com.meta.lnd.concurrentintegeroperations.service;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class PrimaryResultData implements Runnable{
  ConcurrentDataStructure concurrentDataStructure;
  public PrimaryResultData(ConcurrentDataStructure concurrentDataStructure) {
    this.concurrentDataStructure = concurrentDataStructure;
  }
  
  @Override
  public void run() {
    int i=0;
    while (i<100000){
      try{
        concurrentDataStructure.startGettingPrimaryData();
        getPrimeValues();
        concurrentDataStructure.doneGettingPrimaryData();
        i++;
      }catch (InterruptedException ie){
        ie.printStackTrace();
      }
    }
  }
  
  private void getPrimeValues() {
    Queue<Integer> queue = concurrentDataStructure.incomingDataList;
    Arrays.stream(queue.toArray())
        .forEach(s -> addPrimaryNumber(s));
    System.out.println(Thread.currentThread().getId()+"Primary Number List Is: ");
    Arrays.stream(concurrentDataStructure.primaryResultData.toArray())
        .forEach(s -> System.out.print(s+", "));
    System.out.println();
  }
  
  private void addPrimaryNumber(Object s) {
    Integer number = (Integer)s;
    concurrentDataStructure.incomingDataList.remove(s);
    if(isPrimary(number)){
      concurrentDataStructure.primaryResultData.add(number);
    }
  }
  
  private boolean isPrimary(Integer number) {
    for(int i=2;2*i<number;i++) {
      if(number%i==0)
        return false;
    }
    return true;
  }
}
