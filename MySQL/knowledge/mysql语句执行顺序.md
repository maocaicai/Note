准备工作

SQL逻辑查询语句执行顺序

执行FROM语句

执行ON过滤

添加外部行

执行WHERE过滤

执行GROUP BY分组

执行HAVING过滤

SELECT列表

执行DISTINCT子句

执行ORDER BY子句

![img](https://upload-images.jianshu.io/upload_images/2648722-dec59a88ccfe8e88.png?imageMogr2/auto-orient/strip|imageView2/2/w/439/format/webp)



### 一个查询语句的执行顺序

``` mysql
(7)     SELECT 
(8)     DISTINCT <select_list>
(1)     FROM <left_table>
(3)     <join_type> JOIN <right_table>
(2)     ON <join_condition>
(4)     WHERE <where_condition>
(5)     GROUP BY <group_by_list>
(6)     HAVING <having_condition>
(9)     ORDER BY <order_by_condition>
(10)    LIMIT <limit_number>
```



## 执行DISTINCT子句时

如果在查询中指定了`DISTINCT`子句，则会创建一张内存临时表（如果内存放不下，就需要存放在硬盘了）。这张临时表的表结构和上一步产生的虚拟表VT7是一样的，不同的是对进行DISTINCT操作的列增加了一个唯一索引，以此来除重复数据。



更多详细来源博客：https://www.jianshu.com/p/c271e43bb49e