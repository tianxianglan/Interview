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

    ```
- Spring-boot mybatis将sql信息打印控制台  
   - 将`mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl`添加到`application.properties`
   
 - WeakReference（弱引用）：
   -  考虑下面一种情况：现在有一个Product类代表一种产品，这个类被设计成了不可拓展的，现在我们想给每个Product添加一个编号id，一种解决方式就是使用HashMap<Product, Integer>。于是问题来了，如果此时我们已经不需要一个Product对象存在于内存中了（比如已经卖出），假设指向它的引用为productA，这时我们可能会将productA置为null，然而这是productA指向的Product对象并不会被回收，因为他显然还被HashMap引用着。所以在这种情况下，我们i想要真正回收一个Product对象，仅仅把他的强引用置为null是不够的，还要把相应的条目从HashMap中删除，但从HashMap中删除这个工作显示不是我们想自己完成的，我们希望告诉垃圾收集器：在只有HashMap中的key在引用着Product对象的情况下，就可以回收相应的Product对象了。这个时候我们使用弱引用就能达到这个目的，我们只需要用一个指向Product对象的弱引用来作为HashMap的key就可以了
- 链接下载的问题
   - 问题描述：在文件上传jss后，会返回一个url供用户点击下载，但在实际情况中，在将url放入`<a href = 'url'>`点击后，有的url是下载，有的确实预览
   - 原因：浏览器原因，对于浏览器能够识别的文件，将预览，对于无法识别的，则出现下载框
   - 解决办法： 为了能够实现全部的url都为下载，采用的方案为后端经服务器处理，具体方法为：
      - 点击下载标签后，将请求传往后端，后端在拿到url后，通过`URL`类，设置header头后发起url请求，获取到输入流`InputStream`
      - 将`HttpServletResponse`设置header头后将输入流信息写入到`HttpServletResponse`中
      - 具体代码：
      ```
      InputStream input = null;
        try{
                //文件url
                String filePath;
                String fileName = findFile.getFileName();
                URL obj = new URL(filePath);
                HttpURLConnection con = (HttpURLConnection)obj.openConnection();
                con.setRequestMethod("GET");
                con.setInstanceFollowRedirects(true);
                input = con.getInputStream();
                String disposition = new StringBuilder("attachment;filename=").append(URLEncoder.encode(fileName,"UTF-8")).append(".pdf").toString();


                response.reset();
                //设置文件头，关键所在
                response.addHeader("Content-Disposition", disposition);
                ServletOutputStream out = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length=input.read(buffer))> 0){
                    out.write(buffer, 0, length);
                }
                out.close();
            }
        } catch (Exception e) {
            logger.error("转换下载文件: exception = {}", e);
        }
      ```
- 数据库事务：
   - 原子性：数据库的事务要么全都执行成功，要么全都不执行
   - 一致性：事务使得系统从一个一致性的状态转移到另一个一致性的状态，就是说数据库不能破坏关系数据的完整性以及业务逻辑的完整性（对于银行转账，不管是否成功两个账户间的总额都应该为定值）
   - 隔离性：事务之间不受影响，一个事务不应该影响其他事务
      - 读未提交：最低的隔离级别，一个事务可以读到另外一个事务未提交的数据
      - 已提交读：事务对数据的修改只有事务提交后才能够被其他事务读到，能避免脏读
      - 可重复读：在同一个事务中对同一个数据的读取结果总是相同的，无论其他事务是否修改数据。能避免脏读、不可重复读
      - 可串行读：串行化执行，隔离级别最高，但牺牲了系统的并发性
   - 持久性：事务完成之后，对数据的修改都将持久化到磁盘中，并不会被回滚
- mybatis的mapper文件问题
   - 一般情况下，mapper文件对值的set有`#`和`$`两种，`#`将传入的数据都当成一个字符串，会对数据自动加入双引号，如`order by #{orderClause}`，如果传入的值是`created`，则会变成`order by "created"`，出现数据库异常，所以在mapper文件中出现`order by`等时应使用$
