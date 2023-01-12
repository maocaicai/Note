# Java序列化库protostuff介绍



**一、什么是protostuff**

protostuff是一个开箱即用的序列化库，使用非常简单，相对其它序列化库，protostuff优势在于：

1、使用简单

Protobuf每次要编写接口定义文件，然后还要编译，操作太繁琐；

2、高性能

相对json等文本序列化库，protostuff是二进制的，因此性能比json等方式高；

可以说protostuff在高性能和使用成本上做了很好的取舍。

以下是其相关资料：

github：https://codechina.csdn.net/mirrors/protostuff/protostuff

官方文档：https://protostuff.github.io/docs/protostuff-runtime/

总的来说文档还是比较少的，原理需要自己结合代码看下。 

**二、使用**

要使用protostuff非常简单，只要在需要序列化的成员上加上Tag注解，并写明顺序就行了，接下来我们看具体怎么使用；

1、加入依赖

```xml
<dependency>
        <groupId>io.protostuff</groupId>
        <artifactId>protostuff-core</artifactId>
        <version>1.4.0</version>
      </dependency>

      <dependency>
        <groupId>io.protostuff</groupId>
        <artifactId>protostuff-runtime</artifactId>
        <version>1.4.0</version>
      </dependency>
```



2、编写Demo

假设我们要序列化一个Student的类，定义如下：

```java
public class Student {
    @Tag(1)
    private String name;
    @Tag(2)
    private String studentNo;
    @Tag(3)
    private int age;
    @Tag(4)
    private String schoolName;
    @Tag(5)
    private Address address;

    public Student(){
        this.address= new Address();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
```



Address类定义如下：

```java
ublic class Address {
    @Tag(1)
    private String province;
    @Tag(2)
    private String city;

    public Address(){

    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
```



可以看到，我们只需要在类的属性上加个Tag的注解，并写明顺序，注意Tag的值必须从1开始。

然后就可以编写测试了：

```javascript
 Student student = new Student();
  student.setName("lance");
  student.setAge(28);
  student.setStudentNo("2011070122");
  student.setSchoolName("BJUT");

  student.setAddress(new Address());
  student.getAddress().setProvince("address");
  student.getAddress().setCity("hunan");
  //序列化
  Schema schema = RuntimeSchema.getSchema(student.getClass());
  byte[] bytes = ProtobufIOUtil.toByteArray(o, schema, LinkedBuffer.allocate());

  Student deSerializerResult = Student.class.newInstance();
  ProtostuffIOUtil.mergeFrom(bytes, deSerializerResult, schema);

  System.out.println("deSerializerResult:" + deSerializerResult.toString());
}
```



正常的话可以输出Student对象了；

**三、其它**

1、Dubbo中使用

可以通过设置serialization属性为protostuff；

2、返回/接收客户端内容为protostuff协议

在Controller上加上相应的@Consumes和@Produces注解：

@Consumes({ MediaTypeExt.APPLICATION_PROTOSTUFF_V2})

@Produces({ MediaTypeExt.APPLICATION_PROTOSTUFF_V2})

**四、注意事项**

1、Tag值在父类和子类中唯一；

如果父类已经定义了Tag(11)，则子类则不能用11这个Tag了；

2、成员为对象的，Tag号可以从1开始

像上面的Student有个Address成员，它的成员的Tag号从1开始，这个不会造成数据覆盖；