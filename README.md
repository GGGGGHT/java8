# java8
[![Build Status](https://github.com/GGGGGHT/java8/workflows/CI/badge.svg?branch=master)](https://github.com/GGGGGHT/java8/actions)<br/>
学习java的使用包括java及其他工具类的使用 

- [x] Stream
- [x] lambda lambda的局限性： 只有函数式接口才可以使用lambda 非函数式则不可以 lambda无法获取自身引用 即没有this对象 在lambda中this指的是外围实例 在匿名类中this则指的是匿名类实例
- [x] Optional
- [x] 线程池
- [x] java.time
- [x] guava 
- [x] 使用ASM动态生成class
- [x] mybatis
- [x] btrace
- [ ] HSDB
- [x] javac 
- [ ] GraphQL 
- [x] asm
- [ ] javassist
- [x] MXBeang
- [x] jfr
- [ ] minio
- [ ] graalvm
- [x] kotlin
- [ ] maven plugin
- [ ] gradle
- [ ] classloader
- [ ] G1GC 
- [ ] fence
- [ ] java agent
- [ ] jmh
- [x] StackWalker[^2]
- [ ] kotlin
- [ ] skywalking
- [x] eclipse collection 
- [ ] jetbrains annotation
- [x] idea Profiler
- [ ] JProfiler
- [ ] RocketMQ
- [ ] Annotation Processor
- [ ] Spring retry
- [ ] tidb
- [x] netty 
- [ ] jcstress
- [ ] ognl
- [x] arthas
- [ ] micrometer
- [ ] Valhalla 
- [ ] prometheus

[^2]: https://github.com/spring-projects/spring-boot/pull/31701
## 方法引用

方法引用与匿名类相比的优势在于 语法更加简洁。方法引用比Lambda更简洁 

**什么时候使用方法引用?**
> 只要方法引用更加简洁，清晰，就使用方法引用，如果方法引用不简洁，就坚持使用Lambda


## Stream

`Stream`代表数据元素有限或无限的顺序.`Stream pipeline`代表这些元素的一个多级计算. 
`Stream`中的元素可以来自任何位置.

Lambda 只能读取final或者有效的final变量  不能修改任何local变量 


### JVMTI

`JVMTI`[^1] JVM TOOL Interface,jvm工具接口，是JVM提供的一套对JVM进行操作的工具接口。通过JVMTI，可以实现对JVM的多种操作 
通过接口注册各种事件勾子，在JVM事件触发时，同时触发预定义的勾子，以实现对各个JVM事件的响应。
事件包括： 
* 类文件加载
* 异常产生与捕获
* 线程启动和结束
* 进入和退出临界区
* 成员变量修改
* GC开始和结束
* 方法调用进入和退出
* 临界区竞争与等待
* VM启动与退出

[^1]: https://docs.oracle.com/javase/8/docs/platform/jvmti/jvmti.html



#### 启动方式

- 随Java进程启动而启动 eg: java -agentlib: xxx
- 运行时载入，通过attach API 将模块（jar包)动态attach到指定进程id的java进程内 eg: arthas







###  javac  [名称检查器](https://github.com/GGGGGHT/java8/tree/master/java8/src/main/java/com/ggggght/learningjava8/compiler)

使用: 

```shell 
$ javac com/ggggght/learningjava8/compiler/NameChecker.java
$ javac com/ggggght/learningjava8/compiler/NameCheckProcessor.java
$ javac -g -processor com.ggggght.learningjava8.compiler.NameCheckProcessor com/ggggght/learningjava8/compiler/BADLY_NAMED_CODE.java
```

### [Java agent](https://github.com/GGGGGHT/java8/tree/master/java8/src/main/java/com/ggggght/learningjava8/agent)

使用java agent 增强方法

```shell
$ echo 'Premain-Class: com.ggggght.agent.HelloAgent' >> mainfest.mf
$ echo 'Can-Retransform-Classes: true' >> mainfest.mf
$ echo 'Agent-Class: com.ggggght.learningjava8.agent.Bootstrap' >> mainfest.mf
$ jar cvmf mainfest.mf trace.jar com/ggggght/learningjava8/agent
$ java com/ggggght/learningjava8/agent/start


--- 
测试 action运行





# HSDB
jdk8之前
```shell
java -cp $JAVA_HOME/lib/sa-jdi.jar sun.jvm.hotspot.HSDB
```

jdk9之后
```shell
jhsdb hsdb 
```


---
# javac 
语法分析简单来说就是接收读书活动分析的Token序列，然后根据文法生成一棵抽象语法树。
根据建立语法树的不同方式将语法分析的过程分为自顶向下分析和自底向上分析
Javac在具体的实现过程中，主要使用了自顶向下的LL(1)分析法，而自底向上则采用了算符优先分析法。

LL(1)分析时， Javac的语法分析程序将从左到右顺序获取Token序列，每次只获取一个Token对象，然后产生一个兔子的最左推导。
LL(1)分析法中第一个L表示从左到右处理输入的Token对象；第二个L表示会产生一个最左的领导，括号中的1表示每次只读取一个Token对象即可。


---
# jfr  [StartFlightRecording配置参数](https://docs.oracle.com/javase/7/docs/technotes/tools/windows/java.html#:~:text=%2DXX%3AStartFlightRecording%3Dparameter%3Dvalue)
|配置key | 默认值 | 说明|
|---|---|---|
|delay | 0 | 延迟多久后启动 JFR 记录，支持带单位配置， 例如 delay=60s（秒）， delay=20m（分钟）， delay=1h（小时）， delay=1d（天），不带单位就是秒， 0就是没有延迟直接开始记录。一般为了避免框架初始化等影响，我们会延迟 1 分钟开始记录（例如Spring cloud应用，可以看下日志中应用启动耗时，来决定下这个时间）|
|disk | true | 是否写入磁盘，这个就是上文提到的， global buffer 满了之后，是直接丢弃还是写入磁盘文件。
|dumponexit | false | 程序退出时，是否要dump出 .jfr文件
|duration | 0 | JFR 记录持续时间，同样支持单位配置，不带单位就是秒，0代表不限制持续时间，一直记录。
|filename | 启动目录/hotspot-pid-26732-id-1-2020_03_12_10_07_22.jfr，pid 后面就是 pid， id 后面是第几个 JFR 记录，可以启动多个 JFR 记录。最后就是时间。 | dump的输出文件
|name | 无 | 记录名称，由于可以启动多个 JFR 记录，这个名称用于区分，否则只能看到一个记录 id，不好区分。
|maxage | 0 | 这个参数只有在 disk 为 true 的情况下才有效。最大文件记录保存时间，就是 global buffer 满了需要刷入本地临时目录下保存，这些文件最多保留多久的。也可以通过单位配置，没有单位就是秒，默认是0，就是不限制
|maxsize | 250MB | 这个参数只有在 disk 为 true 的情况下才有效。最大文件大小，支持单位配置， 不带单位是字节，m或者M代表MB，g或者G代表GB。设置为0代表不限制大小**。虽然官网说默认就是0，但是实际用的时候，不设置会有提示**： No limit specified, using maxsize=250MB as default. 注意，这个配置不能小于后面将会提到的 maxchunksize 这个参数。
|path-to-gc-roots| false | 是否记录GC根节点到活动对象的路径，一般不打开这个，首先这个在我个人定位问题的时候，很难用到，只要你的编程习惯好。还有就是打开这个，性能损耗比较大，会导致FullGC一般是在怀疑有内存泄漏的时候热启动这种采集，并且通过产生对象堆栈无法定位的时候，动态打开即可。一般通过产生这个对象的堆栈就能定位，如果定位不到，怀疑有其他引用，例如 ThreadLocal 没有释放这样的，可以在 dump 的时候采集 gc roots
|compress|false |指定是否使用 gzip 文件压缩实用程序压缩磁盘上的 JFR 记录日志文件（JFR 类型）。 此参数仅在指定文件名参数时有效。 默认情况下，它设置为 false（记录未压缩)。|

JDK Flight Recorder (JFR) 是一个基于事件的工具，用于监视和分析内置在JDK中的工具。 使用默认设置，JFR 的开销非常低, 基本上<1%，因此可以在生产中使用。 
通常从 JFR 中提取信息需要执行转储； 但是，在 JDK 14 中添加的包 jdk.jfr.consumer 提供了用于使用 JFR 事件的 API，而无需执行 JFR 转储。

```shell
java -XX:StartFlightRecording=disk=true,dumponexit=true,filename=recording.jfr,maxsize=1024m,maxage=1d,settings=profile,path-to-gc-roots=true test.Main
```


## Question
- [x] Spring-batch 设置taskExecutor后jackson解析json失败  [See Also](https://stackoverflow.com/questions/73915795/is-there-a-thread-safe-jacksonjsonobjectreader-class-in-spring-batch) 原因是多线程读取json不安全导致
- [x] SpringBoot Web项目没有 main线程 [See Also](https://stackoverflow.com/questions/73896394/there-is-no-main-thread-in-the-springboot-web-project) 原因是main线程的工作已经结束 但是由于进程中有其他非守护线程还在继续工作 所以进程不会退出
- [x] 解决btrace的bug [See Also](https://github.com/btraceio/btrace/pull/584)
- [x] Java8之前打印SafePoint的日志 [See Also](https://stackoverflow.com/questions/62819904/how-can-i-output-the-safepoint-log-in-a-specified-file) 使用`-XX:+LogVMOutput` `-XX:LogFile=logs/hotspot_pid%p.log`这两个参数
- [x] Java 反射 [See Also](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/jdk/internal/reflect/NativeMethodAccessorImpl.java#L55) 经过15次调用之后会生成直接调用的方法
- [x] 测试环境服务经常下线的问题
- [x] Kotlin字节码比Java字节码输出多一条指令[See Also](https://youtrack.jetbrains.com/issue/KT-54614)


## MISC
### google hacking
Syntax Words:
- intitle: restricts your search to the titles of web pages
- inurl: restricts your search to the URLs of web pages.
- intext: searches only body text (i.e., ignores link text, URLs, and titles).
- inanchor: searches for text in a page's link anchors. A link anchor is the descriptive text of a link.
- site: allows you to narrow your search by either a site or a top-level domain.
- link: returns a list of pages linking to the specified URL. 
- cache: finds a copy of the page that Google indexed even if that page is no longer available at its original URL or has since changed its content completely.
- daterange 
- filetype: searches the suffixes or filename extensions.  eg(pdf,ppt,asp)
- related: as you might expect, finds pages that are related to the specified page. Not all pages are related to other pages. This is a good way to find categories of pages; a search for related:google.com would return a variety of search engines, including HotBot, Yahoo!, and Northern Light.
- info: provides a page of links to more information about a specified URL. 
- phonebook

## how to download java 
Using Script Friendly JDK Download URLS <br/>
e.g. https://download.oracle.com/java/[java-version]/latest/jdk-[java-version]_[os]-[cpu-architecture]_bin.[file-type]

Linux ARM 64 JDK 19:
```shell
curl https://download.oracle.com/java/19/latest/jdk-19_linux-aarch_bin.tar.gz
curl https://download.oracle.com/java/19/latest/jdk-19_linux-aarch_bin.rpm
```

Windows MSI installer for JDK 17:
```shell
curl https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.msi
```



### ognl
```sh
vmtool --action getInstances  --className org.springframework.context.ApplicationContext --express 'instances[0].getBean("httpService").list'
```
