# java8
学习java8新特性如lambda表达式,Optional以及stream流的使用 

- [x] Stream
- [ ] lambda lambda的局限性： 只有函数式接口才可以使用lambda 非函数式则不可以 lambda无法获取自身引用 即没有this对象 在lambda中this指的是外围实例 在匿名类中this则指的是匿名类实例1
- [x] Optional
- [ ] 线程池
- [x] java.time
- [ ] guava 


## 方法引用

方法引用与匿名类相比的优势在于 语法更加简洁。方法引用比Lambda更简洁 

**什么时候使用方法引用?**
> 只要方法引用更加简洁，清晰，就使用方法引用，如果方法引用不简洁，就坚持使用Lambda