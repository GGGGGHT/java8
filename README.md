# java8
学习java的使用包括java及其他工具类的使用 

- [x] Stream
- [x] lambda lambda的局限性： 只有函数式接口才可以使用lambda 非函数式则不可以 lambda无法获取自身引用 即没有this对象 在lambda中this指的是外围实例 在匿名类中this则指的是匿名类实例1
- [x] Optional
- [ ] 线程池
- [x] java.time
- [ ] guava 
- [x] 使用ASM动态生成class
- [ ] mybatis

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
$ javac -g -processor com.ggggght.learningjava8.compiler.NameCheckProcessorcom/ggggght/learningjava8/compiler/BADLY_NAMED_CODE.java
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
测试action运行
