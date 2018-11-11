- @Transaction失效的一些问题：  
   - 1、修饰的方法是否是public类型的  
   - 2、注解修饰的方式是否是在本类中被调用  
      -  原因：Spring之所以可以对开启@Transaction的方法进行事务管理，是因为Spring为当前类生成了一个代理类，在执行相关方法的时候会判断方法中有没有该注解，如果有的话，则会开启一个事务。但是在同一个类中调用@Transaction修饰的方法时，使用的并不是代理对象，从而导致@Transaction失效
- join（）方法：
   - 当前线程执行到a.join()方法时，会暂停知道a线程执行完
   - 源码解析：
     - Thread类的join构造方法有三个，当调用无参方法join（）时，默认调用join（0）.
     - 样例代码：
     ```
     public static void main(String args[]){
         Thread threadA = new Thread(new Runnable(){
            @Override
            public void run(){
               Thread.sleep(1000);
               System.out.println("ThreadA is running");
            }
         });
         threadA.run();
         System.out.println("continue");
     }
     ```
     - 执行结果为：continue永远在ThreadA is running之后打印出
     - join(0)源码：
       ```
       public final synchronized void join(long millis) throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            while (isAlive()) {//判断threadA线程是否存活
               /**
               *main线程执行到wait（）方法时，将释放发到threadA的锁，并使main线程进入到等待序列中
               */
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }
    ``
