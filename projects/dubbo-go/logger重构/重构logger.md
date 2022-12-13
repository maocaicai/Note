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





