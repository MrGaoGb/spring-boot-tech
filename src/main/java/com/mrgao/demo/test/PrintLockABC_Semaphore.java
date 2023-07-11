package com.mrgao.demo.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2023/7/5 10:37
 * @apiNote:
 */
public class PrintLockABC_Semaphore {

    private static Semaphore A = new Semaphore(1);
    private static Semaphore B = new Semaphore(1);
    private static Semaphore C = new Semaphore(1);


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // TODO 一开始，线程B/C阻塞
        try {
            B.acquire();
            C.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.submit(new ThreadA());
        executorService.submit(new ThreadB());
        executorService.submit(new ThreadC());

        executorService.shutdown();
    }
    /**
     * 3个线程，分别输出A、B、C。循环10次
     */
    /**
     * 线程A
     */
    public static class ThreadA implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    A.acquire();
                    System.out.println(Thread.currentThread().getName() + ": Print" + " A");
                    TimeUnit.SECONDS.sleep(1);
                    B.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class ThreadB implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    B.acquire();
                    System.out.println(Thread.currentThread().getName() + ": Print" + " B");
                    TimeUnit.SECONDS.sleep(1);
                    C.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class ThreadC implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    C.acquire();
                    System.out.println(Thread.currentThread().getName() + ": Print" + " C");
                    TimeUnit.SECONDS.sleep(1);
                    A.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
