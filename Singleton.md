- 单例模式：
  - 一般形式代码：
    ```
    private state Singleton instence = null;
    public Singleton getSingleton(){
      if(null == instance){                     //1
        Synchronized(Singleton.class){          //2
          if(null == instance){                 //3
            instance = new Singleton();         //4
          }
        }
      }
      return instance;
    }
    ```
    - 解释：
      以上代码看似没有问题，双重空判断，外加同步锁。但仍然可能会存在问题。第四步中  `instance = new Singleton()`  并不是原子操作，这一行代码可以分成以下三步：
         - 1、为Singleton分配内存空间
         - 2、调用Singleton的构造函数来初始化成员变量
         - 3、将instance指向刚分配的内存地址（此时，instance ！= null）  
         instance存在于栈中，要创建的单例对象则存放在堆内存中,instance = new Singleton()整个过程类似于在JVM的对内存中分配一块内存区域并调用构造函数初始化，之后将栈中的instance指向堆内存中刚分配的内存区域.  
      上述三条指令中，1、2存在先后因果关系，但3与1、2不存在依赖关系。又因为JVM为优化代码效率而实行的指令重排机制，执行顺序可能是1、3、2，这种情况下instance指向的是未经初始化的对象。如果执行完3、未执行2时，有另外一个线程t2执行了这段代码，判断instance不为空，直接返回未经初始化的对象。将会在使用过程中报错。

  - 解决办法：
    - 上述问题的原因在于指令的重排序导致，所以只要禁止了指令的重排序也就能得到我们想要的单例对象了。正确代码：
      ```
      private `volatile` state Singleton instence = null;
      public Singleton getSingleton(){
      if(null == instance){                     //1
        Synchronized(Singleton.class){          //2
          if(null == instance){                 //3
            instance = new Singleton();         //4
          }
        }
      }
      return instance;
    }
      ```

- 一种较为简单的单例模式实现：
  ```
  public class Singleton{
    private static class SingletonHolder{
      private static final Singleton INSTANCE = new Singleton();
    } 
    
    private Singleton(){}
    
    public static final Singleton getSingleton(){
      return SingletonHolder.INSTANCE;
    }
  }
  ```
  - 这种写法仍然使用JVM本身机制保证了线程安全问题；由于 SingletonHolder 是私有的，除了 getInstance() 之外没有办法访问它，因此它是懒汉式的；同时读取实例的时候不会进行同步，没有性能缺陷；也不依赖 JDK 版本。
