package com.mrgao.demo.test;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.Gao
 * @date 2023/7/5 11:24
 * @apiNote:
 */
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("进入阻塞...");
                TimeUnit.SECONDS.sleep(3);
                System.out.println("阻塞完成...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("A");
        }, "T1");
        thread.start();
        // --阻塞当前主线程，若该线程执行完之后 会自主唤醒 继续执行
        thread.join();
        // --阻塞当前线程 需要搭配notify()、notifyAll()等方法使用
        //thread.wait();
        //thread.notify();

    }
}
