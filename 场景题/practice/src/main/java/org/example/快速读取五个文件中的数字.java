package org.example;

import java.util.concurrent.CountDownLatch;

public class 快速读取五个文件中的数字 {
    /**
     * 算法题目：有五个文件，每个文件里面有n行，请以最快的速度读取所有文件中的数字并输出top50.
     */
    /**
     * 使用工具：多线程加速文件读取；  所有文件读完后还要统一读取所有数字的top50，也就代表着主线程在子线程读完文件之前必须阻塞，然后才能输出，这就得用到CountDownLatch；
     * 输出top50使用的是PriorityQueue，但这是个共享变量，就代表着PriorityQueue得加锁，最后就是在I/O时也需要考虑用什么I/O类能更快
     */

    CountDownLatch c = new CountDownLatch(5);
    public static void sort() {

    }
}
