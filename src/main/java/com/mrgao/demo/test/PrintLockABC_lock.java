package com.mrgao.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.Gao
 * @date 2023/7/5 10:37
 * @apiNote:
 */
public class PrintLockABC_lock {

    volatile static int state = 0;

    private static final Lock lock = new ReentrantLock();


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ShareData shareData = new ShareData();
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printA();
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printB();
            }
        });
        Thread threadC = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printC();
            }
        });
        executorService.submit(threadA);
        executorService.submit(threadB);
        executorService.submit(threadC);

        executorService.shutdown();
    }


    public static class ShareData {

        private int state = 0;

        private final Lock lock = new ReentrantLock();

        private final Condition condition = lock.newCondition();


        public void printA() {
            lock.lock();
            try {
                while (state % 3 != 0) {
                    condition.await();
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": Print :" + "A" + " Num:" + state++);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printB() {
            lock.lock();
            try {
                while (state % 3 != 1) {
                    condition.await();
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": Print :" + "B" + " Num:" + state++);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void printC() {
            lock.lock();
            try {
                while (state % 3 != 2) {
                    condition.await();
                }
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + ": Print :" + "C" + " Num:" + state++);
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }

}
