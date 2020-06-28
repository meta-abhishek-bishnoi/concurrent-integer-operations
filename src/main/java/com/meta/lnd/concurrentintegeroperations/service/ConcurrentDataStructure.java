package com.meta.lnd.concurrentintegeroperations.service;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentDataStructure {
  public static Queue<Integer> incomingDataList;
  public static Set<Integer> primaryResultData;
  ReentrantLock lock;
  Condition noIncomingData;
  Condition noPrimaryResultData;
  
  public ConcurrentDataStructure() {
    incomingDataList = new LinkedList<>();
    primaryResultData = new LinkedHashSet<>();
    lock = new ReentrantLock();
    noIncomingData = lock.newCondition();
    noPrimaryResultData = lock.newCondition();
  }
  
  public void startAddingDataToList() throws InterruptedException{
    lock.lock();
    lock.unlock();
  }
  public void doneAddingDataToList() throws InterruptedException{
    lock.lock();
    lock.unlock();
  }
  public void startGettingPrimaryData() throws InterruptedException{
    lock.lock();
    lock.unlock();
  }
  public void doneGettingPrimaryData() throws InterruptedException{
    lock.lock();
    lock.unlock();
  }
}
