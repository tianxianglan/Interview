- 1、静态工厂方法与构造方法
  - 一般情况下我们获取一个实体的方式都是调用其提供的构造方法，其实还有另外一种方法可以为我们创建实例，这便是静态工厂方法（一个返回实例对象的静态方法），JDK中就有很多这种例子：
  ```
  public static Boolean valueof(boolean b){
    return b ? Boolean.TRUE : Boolean.FALSE;
  }
  ```
  有了这种方式后，类可以为其客户端提供静态工厂方法，而不是构造方法。两者的优缺点有：
    - 静态工厂方法与构造方法相比，它是有名字的：
       一个类只能有一个给定签名的构造函数，为了解决这个限制，我们不得不提供两个甚至更多的构造函数。这几个构造函数唯一的区别就是参数个数以及参数的顺序不一致。这样的API用户将永远不会记得哪个构造函数是哪个，可能会错误的调用，为了接口能被正确的时候不得不书写接口文档来保证。
       而静态构造方法因为有名字，所以静态工厂方法不会受到上面所说的限制。在类中似乎需要具有相同签名的多个构造方法的情况下，可以用静态工厂方法替换构造方法，并选择用名称来突出他们的差异。
    - 静态工厂方法与构造方法相比，它不需要每次都新建一个对象：
    - 静态工厂方法与构造方法相比，它可以返回任何子类型的对象：
    - 静态工厂方法与构造方法相比，它可以根据入参的不同而返回不同的对象：
- 2、创建对象使用builder模式
  - 不可变对象一定是线程安全的
- 3、私有构造方法或者枚举实现单例
  - 可以通过 `AccessibleObject.setAccessible` 去修改一个类的私有属性
  ```
  public class A{
    private Integer data = 0;
  }
  
  public class B{
    public static void main(String[] args){
      A a = new A();
      Field[] fields = a.getClass().getDeclaredFields();
      AccessibleOgject.setAccessible(fields, true);
      try{
        //修改私有属性值
        fields[0].setInt(a, 150);
        System.out.println(fields[0].get(a));
      }catch(Throwable e){
        //solve exception
      }
    }
  }
  ```
