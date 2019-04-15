- 代码：
  ```
  public class ReenterLockCondition implentments Runable{
    public static ReentrantLock lock = new ReentrantLock();
    //生成一个与lock绑定的Condition对象
    public static Condition condition = lock.newCondition();
    @Override
    public void run(){
      try{
        //线程持有锁对象
        lock.lock();
        //线程释放持有的锁资源，进入到condition对象的等待序列中
        condition.await();
        System.out.println("Thread is going on");
      }Catch(Throwable e){
        e.printStackTrace();
      }finaly{
        lock.unlock();
      }
    }
    public static void main(String[] args) throws Exception{
      ReenterLockCondition t = new ReenterLockCondition();
      Thread t1 = new Thread(t);
      t1.start();
      Thread.sleep(2000);
      lock.lock();
      //从当前condition对象的等待序列中唤醒一个线程
      condition.singnal();
      lock.unlock();
    }
  }
  ```
  - 过程：
    24行线程t1执行，主线程main睡眠2s，线程t1获取到重入锁lock的锁资源，之后执行condition.await(),释放持有的锁资源，并进入到condition对象的等待序列中，  
    此时，线程t1停止执行。2s过后，主线程main恢复执行，到28行condition.singnal()从condition对象的等待序列中将线程t1唤醒，但此时，线程t1因为并不拥有  
    重入锁对象，所以仍然得不到继续执行，`在29行主线程main执行unlock（）操作将重入锁对象释放后`，线程t1获取重入锁对象后继续执行，输出，释放锁资源。
  - 注意：1、线程在执行Condition.await()、Condition.signal()时都要求线程持有相关的重入锁。
          2、线程在执行Condition.signal()后，都要求释放持有的锁资源，谦让给被唤醒的线程。这也就是为什么如果没有29行代码，虽然唤醒了线程t1，  
          但t1却仍然得不到执行的原因。
