package com.meta.lnd.concurrentintegeroperations.service;
/**
  * AddPublisher
  *
  * @author Abhishek Bishnoi
  * 28-Jun-2020, 8:12 PM
*/
public class IncomingDataList implements Runnable{
  ConcurrentDataStructure concurrentDataStructure;
  public IncomingDataList(ConcurrentDataStructure concurrentDataStructure) {
    this.concurrentDataStructure = concurrentDataStructure;
  }
  
  @Override
  public void run() {
    int i=0;
    while (i<100000){
      try{
        concurrentDataStructure.startAddingDataToList();
        addDataToList();
        concurrentDataStructure.doneAddingDataToList();
        i++;
      }catch (InterruptedException ie){
        ie.printStackTrace();
      }
    }
  }
  
  private void addDataToList() {
    int number = getNumber();
    concurrentDataStructure.incomingDataList.add(number);
    System.out.println(Thread.currentThread().getId()+" Added Number is:  "+number);
  }
  
  private int getNumber() {
    return (int)(Math.random() * (10000 - 0 + 1) + 0);
  }
}
