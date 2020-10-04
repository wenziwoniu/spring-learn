package com.woniu.jdbc.jdbctemplate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class MyServer{
    private ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();
    public Boolean flag = false;
    public void set() {
        try {
            lock.lock();
            while(flag == true) {
                condition.await();
            }
            System.out.println("当前线程名："+Thread.currentThread().getName()+" hello");
            flag = true;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void get() {
        try {
            lock.lock();
            while(flag == false) {
                condition.await();
            }
            System.out.println("当前线程名："+Thread.currentThread().getName()+" lemon");
            flag = false;
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
class MyCondition1 extends Thread{
    private MyServer myServer;
    public MyCondition1(MyServer myServer) {
        super();
        this.myServer = myServer;
    }
    public void run() {
        for(int i = 0 ;i < 10;i++) {
            myServer.set();
        }
    }
}
class MyCondition2 extends Thread{
    private MyServer myServer;
    public MyCondition2(MyServer myServer) {
        super();
        this.myServer = myServer;
    }
    public void run() {
        for(int i = 0 ;i < 10;i++) {
            myServer.get();
        }
    }
}
public class MyLock{
    public static void main(String[] args) throws InterruptedException {
        MyServer myServer = new MyServer();
        MyCondition1  myCondition1 = new MyCondition1(myServer);
        MyCondition2  myCondition2 = new MyCondition2(myServer);
        myCondition1.start();
        myCondition2.start();
    }
}