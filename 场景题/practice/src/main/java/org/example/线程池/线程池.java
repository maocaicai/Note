package org.example.线程池;

import org.example.快速读取五个文件中的数字;
import sun.java2d.pipe.AAShapePipe;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class 线程池 {
    /**
     * 引用美团文章：https://tech.meituan.com/2020/04/02/java-pooling-pratice-in-meituan.html
     */
    /**
     * ThreadPoolExecutor.md
     *
     * 线程过多会带来额外的开销，其中包括创建销毁线程的开销、调度线程的开销等等，同时也降低了计算机的整体性能。线程池维护多个线程，等待监督管理者分配可并发执行的任务。
     * 这种做法，一方面避免了处理任务时创建销毁线程开销的代价，另一方面避免了线程数量膨胀导致的过分调度问题，保证了对内核的充分利用。
     *
     * 使用线程池的好处：
     * 降低资源消耗：通过池化技术重复利用已创建的线程，降低线程创建和销毁造成的损耗。
     * 提高响应速度：任务到达时，无需等待线程创建即可立即执行。
     * 提高线程的可管理性：线程是稀缺资源，如果无限制创建，不仅会消耗系统资源，还会因为线程的不合理分布导致资源调度失衡，降低系统的稳定性。使用线程池可以进行统一的分配、调优和监控。
     * 提供更多更强大的功能：线程池具备可拓展性，允许开发人员向其中增加更多的功能。比如延时定时线程池ScheduledThreadPoolExecutor，就允许任务延期执行或定期执行。
     */
    /**
     * 除去线程池，还有其他比较典型的几种使用策略包括：
     *
     * 内存池(Memory Pooling)：预先申请内存，提升申请内存速度，减少内存碎片。
     * 连接池(Connection Pooling)：预先申请数据库连接，提升申请连接的速度，降低系统的开销。
     * 实例池(Object Pooling)：循环使用对象，减少资源在初始化和释放时的昂贵损耗。
     */

    public static void main(String[] args) {
        BlockingQueue<Runnable> b = new ArrayBlockingQueue<>(3);
         ThreadPoolExecutor e = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, b);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    快速读取五个文件中的数字.readFile("1");
                } catch (IOException ex) {
                    System.out.println("读取错误");
                }
            }
        };
        e.execute(r);
    }


}
