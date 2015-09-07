![](http://dl.iteye.com/upload/picture/pic/133973/d30fc066-9cb2-369e-bcae-5a729733c683.jpg)
### kratos简介
基于Java语言编写的轻量级分库分表(Sharding)中间件，丰富的Sharding算法支持(2类4种分片算法)，能够方便DBA实现库的极速扩容和降低数据迁移成本。Kratos站在巨人的肩膀上(SpringJdbc)，采用与应用集成架构，放弃通用性，只为换取更好的执行性能与降低分布式环境下外围系统的down机风险。<br>

- Kratos的使用手册：http://gao-xianglong.iteye.com/blog/2237277<br>
- Kratos的分片模型：http://gao-xianglong.iteye.com/blog/2238901<br>
- Kratos的注意事项：http://gao-xianglong.iteye.com/blog/2239007<br>

### kratos的优点：
- 动态数据源的无缝切换；<br>
- master/slave一主一从读写分离；<br>
- 单线程读重试(取决于的数据库连接池是否支持)；<br>
- 单独且友好支持Mysql数据库，不支持其它RDBMS库；<br>
- 非Proxy架构，与应用集成，应用直连数据库，降低外围系统依赖带来的down机风险；<br>
- 使用简单，侵入型低，站在巨人的肩膀上，依赖于Spring JDBC；<br>
- 不做真正意义上的Sql解析任务，规避Sql解析过程中由词法解析、语法解析、语义解析等操作所带来的性能延迟，仅用正则表达式解析    片名和Route条件，解析过程仅耗时约<=1ms；<br>
- 分库分表路由算法支持2类4种分片模式，库内分片/一库一片；<br>
- 提供自动生成sequenceId的API支持；<br>
- 提供自动生成配置文件的支持，降低配置出错率；<br>
- 目标和职责定位明确，仅专注于Sharding，不支持其它多余或鸡肋功能、无需兼容通用性，因此核心代码量少、易读易维护<br>

### kratos的使用注意事项：
- 不支持强一致性的分布式事务，但可以在业务层依赖MQ、异步操作的方式实现事物，保证最终一致性即可；
- 不支持多表查询，所有多表查询sql，务必全部打散为单条sql逐条执行；
- 不建议使用一些数据库统计函数、Order by语句等；
- sql语句的第一个参数务必是路由条件；
- 不支持sql语句中出现数据库别名；
- 路由条件必须是整数类型；
- 在连续分片模式下，子表后缀为符号"_"+4位整型，比如“tb_0001”——"tb_1024"；

### kratos的分片模型：
kratos支持2类4种分片模式。
- 库内分片类：片名连续的库内分片、非片名连续的库内分片；
- 一库一片类：片名连续的一库一片、非片名连续的一库一片；

#### 片名连续的库内分片：
假设dbSize是32，tbSize是1024，那么每一个库的片数为1024/32=32，而片名是按照0000-1023进行分布的，这就意味着片名是全局唯一的，不允许出现重复。
![](http://dl.iteye.com/upload/picture/pic/133891/f7d547a8-6d8e-3404-a3f0-6dce8b25042a.jpg)
