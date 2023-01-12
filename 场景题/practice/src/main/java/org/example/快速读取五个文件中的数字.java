package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class 快速读取五个文件中的数字 {
    /**
     * 算法题目：有五个文件，每个文件里面有n行，请以最快的速度读取所有文件中的数字并输出top50.
     */
    /**
     * 使用工具：多线程加速文件读取；  所有文件读完后还要统一读取所有数字的top50，也就代表着主线程在子线程读完文件之前必须阻塞，然后才能输出，这就得用到CountDownLatch；
     * 输出top50使用的是PriorityQueue，但这是个共享变量，就代表着PriorityQueue得加锁，最后就是在I/O时也需要考虑用什么I/O类能更快
     */
    /**
     * 使用线程池执行读取，用list存五个文件名，然后每次从list取一个文件名用线程池执行读取方法
     * 也用不上countdownlatch
     * 核心方法，读取文件
     */

    CountDownLatch c = new CountDownLatch(5);
    public static void sort() {

    }
    public static void readFile(String name) throws IOException {
        String content = "";
        StringBuilder builder = new StringBuilder();
        int num = 1;
        while (num != 5) {
            File file = new File("D:\\congyutao1\\Note\\场景题\\practice\\src\\main\\java\\org\\example\\test\\" + num ++  + ".txt");
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader b = new BufferedReader(streamReader);
            while ((content = b.readLine()) != null) {
                builder.append(content);
            }
        }
        System.out.println(builder.toString());
    }
}
