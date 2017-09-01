package meiTuan;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tianxianglan on 2017/8/31.
 */
public class ThreadsPritnReetranLock {
    private static Lock lock = new ReentrantLock();
    private static int state = 0;

    static class ThreadA extends Thread{

        @Override
        public void run() {
            for (int i = 0; i< 10;){
                lock.lock();
                if (state%3 == 0){
                    System.out.println("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }
    static class ThreadB extends Thread{

        @Override
        public void run() {
            for (int i = 0; i< 10;){
                lock.lock();
                if (state%3 == 1){
                    System.out.println("B");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }
    static class ThreadC extends Thread{

        @Override
        public void run() {
            for (int i = 0; i< 10;){
                lock.lock();
                if (state%3 == 2){
                    System.out.println("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();
        ThreadC c = new ThreadC();

        a.start();
        b.start();
        c.start();
    }
}


