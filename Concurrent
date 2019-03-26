- 代码：
    ```
    try{
      int a = 1;             //1
      int b = 1/ 0;          //2
    }catch(Exception e){
      System.out.println(b);//3    
    }
    ```
    这段代码中虚拟机是否会对1跟2段代码进行重排序呢？如果2先于1执行，2抛出异常，代码将不会执行对`赋值`的操作。答案是肯定的，即便存在之前所说风险，但  
    虚拟  机仍会对这两行代码进行重排序。但重排序的结果不会受到异常的影响，`这是因为JIT在重排序时会在catch语句中插入相应的补偿代码（a = 1），这样做  
    的代价是会使的  catch里面的逻辑变得复杂，但是JIT优化原则是：尽可能地优化程序正常运行下的逻辑，哪怕以catch块逻辑变得复杂为代价。`
- 代码：
    ```
    class ThreadA extends Thread{

        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName()+" call notify()");
                // 唤醒当前的wait线程
                //但其实在这段代码中，有没有notify（）影响不大，因为只有两个线程（main、t1），t1在获取锁执行完这段代码后自然会释放掉锁资源，而处于  
                //等待队列的主线程也就会被唤醒得以继续执行
                notify();
            }
        }
    }

    public class TestJoin {

        public static void main(String[] args) {

            ThreadA t1 = new ThreadA("t1");

            synchronized(t1) {
                try {
                    // 启动“线程t1”
                    System.out.println(Thread.currentThread().getName()+" start t1");
                    t1.start();

                    // 主线程等待t1通过notify()唤醒。
                    System.out.println(Thread.currentThread().getName()+" wait()");
                    //执行线程（主线程）释放锁资源（t1）
                    t1.wait();

                    System.out.println(Thread.currentThread().getName()+" continue");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    ```
    - 代码解析：
        首先，主线程获取到了对象t1的对象锁并启动线程t1，但是线程t1进入同步代码块试图获取对象t1的锁资源，此时t1的锁资源被主线程占有，因此线程t1并未  
        进入运行状态，而是在等待对象t1的锁资源。
        之后主线程在执行到t1.wait（）时将释放掉t1的对象锁资源进入阻塞状态。但是线程t1在获取到对象锁资源之后开始进入运行状态，进入run（）方法的同步  
        代码块中，执行notify()，将主线程进行唤醒操作，主线程得以继续执行。
        ![运行时序图](https://images0.cnblogs.com/blog/497634/201312/18183712-f04899f92aaa43b6a33a85fecfa60a9d.png)
        from https://www.cnblogs.com/skywang12345/p/3479224.html 注意看评论

- 代码：
    ```
        public void testSeq() {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj){
                System.out.println("t1 possess the monitor");
                try {
                    obj.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (obj){
                System.out.println("t2 possess the monitor");
                try {
                    Thread.sleep(3000);
                    obj.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            synchronized (obj){
                System.out.println("t3 possess the monitor");
                try {
                    obj.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        synchronized (obj){
            try{
                t1.start();
                t2.start();
                t3.start();
                System.out.println("main");
                obj.wait();
                System.out.println("end");
            }catch (Throwable e){
                e.printStackTrace();
            }
        }
    }
    ```
    - 解释：
        上述代码运行中，main线程先获取到obj对象的锁资源，但随后执行wait（）操作将锁资源释放掉，但当其他线程将锁资源释放时，main线程也不能马上  
        执行，仍要先获取到obj对象的锁资源才得以继续执行
