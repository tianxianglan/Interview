### 1、安装软件：  
  - 在线安装：
    - 可以使用sudo apt-get install softWareName，该指令会解决软件的依赖问题，apt是建立在dpkg之上的软件管理工具
  - deb包：
    - sudo dpkg -i,其中`i`为`install`的简写，可以用于安装本地的deb包，但该指令不会解决依赖问题，所以我们在使用dpkg进行安装时会遇到依赖出错情况，这时候我们就需要用到`sudo apt-get install -f`来修复依赖关系，其中`-f`参数为`-fix-broken`的简写。
  - tar.gz
    - 类似与windows下的zip压缩包。同样的道理，我们需先使用`tar zxvf gzName`解压，之后cd进入到bin目录，执行`./***.sh`明来进行安装即可。  

#### 2、安装Oracle JDK环境：   
 - Linux存储库提供的JDK是open-JDK，当需要Oracle-JDK时就需要手动将库添加到apt中
 - 通过以下命令添加webupd8team存储库
    - `sudo apt-get install python-software-properties`、`sudo add-apt-repository ppa:webupd8team/java`、`sudo apt-get update`
 - 安装java
    - `sudo apt-get install oracle-java8-installer`
 - 将Oracle java设置为默认
    - `sudo update-alternatives --config java`
 - 验证JDK安装是否成功
    - `java -version`
