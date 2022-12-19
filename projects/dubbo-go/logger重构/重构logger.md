# 重构dubbogo的logger

这是dubbo-go-boot的配置，通过config.go生产接口动态选择不同的配置

![image-20221213172704911](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213172704911.png)



应用到dubbo-go的logger中，动态选择不同的logger



## 首先logger_config.go不变，作为接口![image-20221213173003794](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213173003794.png)



作为动态变化的配置map：设置set和get方法

``` 
var configs = make(map[string]Config)
```

![image-20221213173107649](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213173107649.png)





## 创建zap日志类继承接口

type结构和原来相同，init中设置zap结构为配置结构![image-20221213174520509](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213174520509.png)





本质上使用的是url机制去做的

![image-20221213214705359](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213214705359.png)

zap初始化的时候，设置了logger的名字和结构体

![image-20221213214715850](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213214715850.png)

然后在配置时通过getlogger获取![image-20221213214755180](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213214755180.png)

![image-20221213214812902](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213214812902.png)





他的想法：在extension包中新建logger类，这个类利用url机制去做动态扩展功能。

然后，dubbogo中的这个结构体不要用，因为最后没有用到，把Lumberjack中的属性拿出来放在一个结构体体中去使用，并实现lumberjack![image-20221213215115245](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213215115245.png)







## 原来的日志流程

1. 设置ZapConfig、EncoderConfig、LoggerConfig结构体，并自带defalut配置
2. Init方法中：初始化loger的config结构![image-20221215152956197](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221215152956197.png)

把配置文件中的信息读入logger.Config结构中

setEncoderConfig()，将配置设置到lc的zapconfig中![image-20221215153232236](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221215153232236.png)

```
setZapConfig(logConf.ZapConfig)
```

初始化logger：logger.InitLogger(logConf)





## dubbo-go-boot的日志流程
