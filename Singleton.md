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
         instance存在于栈中，要创建的单例对象则存放在堆内存中
      上述三条指令中，1、2存在先后因果关系，但3与1、2不存在依赖关系。又因为JVM为优化代码效率而实行的指令重排机制，执行顺序可能是1、3、2，这种情况下  
      instance指向的仍是未经初始化的内存空间。如果这时候有一线程进入，判断instance == null，返回true，得以进入。这时候得到的对象就不再是单例了。

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
