项目中的protostuff并没有用@tag去标记，因为项目中需要序列化的数据data是object类型，只需要针对这一个数据序列化反序列化即可

```java
public class RpcMessage {
    private byte messageType;
    private byte codec;
    private byte compress;
    private int requestId;
    //只需要序列化此数据
    private Object data;
}
```

因此项目中有一个protostuffserializer序列化类，在此类中直接编写序列化反序列化方法，在编解码类中拿到消息后直接对消息执行该方法进行序列化反序列化即可。