package 单例模式;

public class singleton {

    /**
     * 懒汉式，双重校验锁的实现
     */
    /**
     * uniqueInstance 采用 volatile 关键字修饰也是很有必要的。uniqueInstance = new Singleton();
     * 这段代码其实是分为三步执行。
     * 1.分配内存空间
     * 2.初始化对象
     * 3.将 uniqueInstance 指向分配的内存地址
     * 但是由于 JVM 具有指令重排的特性，有可能执行顺序变为了 1>3>2，这在单线程情况下自然是没有问题。
     * 但如果是多线程下，有可能获得是一个还没有被初始化的实例，以致于程序出错。
     * 使用 volatile 可以禁止 JVM 的指令重排，保证在多线程环境下也能正常运行。# 静态内部类实现
     */
    private static volatile singleton Instance;
    private singleton(){}

    public static singleton getInstance() {
        /**
         *  如果不加这个if那么多线程时都会去抢锁，导致性能下降，加了这个判断
         *  可以减少许多次无效竞争锁的情况
         */
        if (Instance == null) {
            //加锁保证线程安全
            synchronized (Instance) {
                //某个线程拿到锁以后还要判断是否实例生成了，没生成才会new
                if (Instance == null) {
                    Instance = new singleton();
                }
            }
        }
        return Instance;
    }
}
