- @Transaction失效的一些问题：  
   - 1、修饰的方法是否是public类型的  
   - 2、注解修饰的方式是否是在本类中被调用  
      -  原因：Spring之所以可以对开启@Transaction的方法进行事务管理，是因为Spring为当前类生成了一个代理类，在执行相关方法的时候会判断方法中有没有该注解，如果有的话，则会开启一个事务。但是在同一个类中调用@Transaction修饰的方法时，使用的并不是代理对象，从而导致@Transaction失效
- join（）方法：
   - 当前线程执行到a.join()方法时，会暂停直到a线程执行完
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
         threadA.join();
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
               *非静态同步方法，锁住的是当前对象实例
               *main线程执行到wait（）方法时，将释放发到threadA的锁，并使main线程进入到等待序列中
               */
                wait(0);
                /**
                *有个问题，线程的notify（）操作在哪里执行？
                *answer(待验证):  线程在执行完成后会执行exit()方法，该方法中有个group.threadTerminated()方法，进去后里面有对线程进行  notifyAll()的操作
                **/
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
   - 隔离性：事务之间不受影响，一个事务不应该影响其他事务（隔离级别）
      - 读未提交：最低的隔离级别，一个事务可以读到另外一个事务未提交的数据
      - 已提交读：事务对数据的修改只有事务提交后才能够被其他事务读到，能避免脏读
      - 可重复读（Mysql默认级别）：在同一个事务中对同一个数据的读取结果总是相同的，无论其他事务是否修改数据。能避免脏读、不可重复读
      - 可串行读：串行化执行，隔离级别最高，但牺牲了系统的并发性
   - 持久性：事务完成之后，对数据的修改都将持久化到磁盘中，并不会被回滚
   - 可能导致问题
      - 脏读：读其他事务未提交的数据
      - 不可重复读：在同一事务的查询中，查询结果出现不一致的情况。以操作同一行数据为例，读事务禁止其他写事务（但允许读事务），未提交的写事务禁止其他的读事务和写事务
      - 幻读：事务在操作过程中进行了两次查询，第二次查询结果包含了第一次查询结果中没有的或者多的数据
         - 不可重复读与幻读区别：
            - 不可重复读是由于delete或者update造成的，而幻读则是由于insert了新数据
- mybatis的mapper文件问题
   - 一般情况下，mapper文件对值的set有`#`和`$`两种，`#`将传入的数据都当成一个字符串，会对数据自动加入双引号，如`order by #{orderClause}`，如果传入的值是`created`，则会变成`order by "created"`，出现数据库异常，所以在mapper文件中出现`order by`等时应使用$
   - mapper文件:
   ```
      <resultMap id='JoinMap' type="com.jd.yao.*.*.*.ActApplyNum">
         <result column="id" property="id"/>
         <result column="num" property="num"/>
      </resultMap>
      <select id="queryNum" parameterType="java.util.List" resultMap="JoinMap">
         select count(pin) as num, act_id as is from test_acr where yn=1
         and act_id in
         <foreach collection="list" iten="id" open="(" close=")" separator=",">
            #{id}
         </foreach>
         group by act_id
       </select>
   ```
   - 返回结果类型有`resultMap`、`resultType`两张，resultType更偏向于一个具体的返回类型，如java.lang.Integer、Map等。而resultMap偏向于结果集的映射，在需要返回一个实体对象时应使用resultMap
   
   - 接口设置为post请求方式，参数为一个Integer类型参数，前无任何注解。。。但适用postman进行接口测试时，content-Type设置为application/json，后端无法获取到参数值
      - @RequestParam
         用来处理Content-Type为application/x-www-form-urlencode编码的内容
      - @RequestBody
         用来处理Content-Type不是application/x-www-form-urlencode编码的内容，例如application/json、application/xml等。他是通过使用HandlerAdapter配置的HttpMessageConverts来解析post data body，之后绑定到相应的bean上
         
- ```
   List<Person> list;
   List<Object> new;
   list.stream().foreach(o -> {
      new.add(funcA(o));
      new.add(funB(o));
   });
   new = list.parallelStream.flatMap(o -> Stream.of(funcA(o), funcB(o))).collect(Collectors.toList())
  ```
- 弱引用（WeakReference）:
   ```
   1、Person person = new Person(11);
   2、WeakReference weak = new WeakReference(person);
   3、person = null;
   4、System.out.println(Json.parse(weak.get()));
   5、System.gc();
   6、System.out.println(Json.parse(weak.get()));
   
   output:{"age":11}、null
   如果注释掉第3行，则两个输出返回的都是{"age":11}
   ```
   解释：1、声明一个强引用person，此时heap中将分配一块内存区域给Person对象，stack中也讲会有一个强引用person指向这块内存区域  
        2、声明一个弱引用对象weak，此弱引用对象指向的也是person所指向的内存区域（`weak与person指向的是堆中的同一块区域`）  
        3、强引用person被置为null，只是就只有弱引用waek还指向着堆内存，所以第4行的输出会是Person对象  
        5、显式的进行一次gc操作，此时，堆内存Person区域只有一个弱引用weak引用着，根据弱引用的特性，该块内存区域将会被jvm回收，所以在第六行输出堆内存数据时返回的结果将为空
   - 延申：对于强引用，可以去看ArrayList类源码中的remove（）方法，他对元素删除的操作基于将对对像的引用置为null，等下一次GC操作的时候就会将内存进行回收。`elementData[--size] = null; // clear to let GC do its work`
- 主站登陆后会往浏览器中写cookie，因为cookie一旦创建无法修改，修改在退出登陆时需要获取到原有cookie的name属性，新建该name的cookie，写入浏览器中覆盖原有cookie    
   - cookie过期时间设置为正数x时，cookie将在x秒后过期。如果设置为0，则并不会往浏览器中写cookie，去获取的话也无法获取到。  设置成负数时，cookie将在关闭浏览器后过期
   - maxAge默认是关闭浏览器清楚cookie
- 枚举match
   ```
   public enum HttpMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private static final Map<String, HttpMethod> mappings = new HashMap(8);

    private HttpMethod() {
    }

    public static HttpMethod resolve(String method) {
        return method != null ? (HttpMethod)mappings.get(method) : null;
    }

    public boolean matches(String method) {
        return this == resolve(method);
    }

    static {
        HttpMethod[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            HttpMethod httpMethod = var0[var2];
            mappings.put(httpMethod.name(), httpMethod);
        }

    }
   ```
   
     
- 跨域
   a.jd.com跨域访问b.jd.com，为了b能够获取到a的cookie数据，需要额外配置属性
   ```
   $.ajax({
            url: 'http://yao.jd.com/shopPage/price',
            data:'43890183136',
            dataType:"json",
            type:"POST",
            contentType: "text/plain",
            xhrFields:{withCredentials: true},
            success:function(data){
                alert(data)
                $("#sssss").html("ddddddddd");
            }
        })
   ```
设置withCredentials为true即可让该跨域请求携带 Cookie。 注意携带的是目标页面所在域的 Cookie。

- sql优化：
   - limit m,n 其实是先执行 limit m + n，然后从第 m 行取 n 行，这样当 limit 翻页越往后越大，性能越低，如`select * from table_name limit 10000, 10`建议改成 `select * from table_name where id> (select * from table_name limit 10000, 1) limit 10`
