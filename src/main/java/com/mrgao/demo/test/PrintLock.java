package com.mrgao.demo.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.Gao
 * @date 2023/7/5 10:19
 * @apiNote:两个线程分别输出奇数和偶数
 */
public class PrintLock {

    public static void main(String[] args) {
        // 共享资源
        ShareData shareData = new ShareData();
        // T1: 输出1、3、5、7、9
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printEventNum();
            }
        }, "T1").start();
        // T2：输出2、4、6、8、10
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.printOddNum();
            }
        }, "T2").start();

    }


    /**
     * 共享数据
     */
    public static class ShareData {

        private int data = 1;
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        /**
         * 输出奇数
         */
        public void printEventNum() {
            lock.lock();
            try {
                while (data % 2 == 0) {
                    // 如果为偶数时 则继续等待 ,若为奇数时 则唤醒
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + "数字:" + data++);
                condition.signalAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

        /**
         * 输出偶数
         */
        public void printOddNum() {
            lock.lock();
            try {
                while (data % 2 != 0) {
                    // 如果为奇数时 则继续等待 ,若为偶数时 则唤醒
                    condition.await();
                }
                System.out.println(Thread.currentThread().getName() + "数字:" + data++);
                condition.signalAll();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }

    }
}