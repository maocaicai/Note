**SQL23** **统计每个学校各难度的用户平均刷题数**

## 描述

题目：运营想要计算一些**参加了答题**的不同学校、不同难度的用户平均答题量，请你写SQL取出相应数据

用户信息表：user_profile

| id   | device_id | gender | age  | university | gpa  | active_days_within_30 | question_cnt | answer_cnt |
| ---- | --------- | ------ | ---- | ---------- | ---- | --------------------- | ------------ | ---------- |
| 1    | 2138      | male   | 21   | 北京大学   | 3.4  | 7                     | 2            | 12         |
| 2    | 3214      | male   | NULL | 复旦大学   | 4    | 15                    | 5            | 25         |
| 3    | 6543      | female | 20   | 北京大学   | 3.2  | 12                    | 3            | 30         |
| 4    | 2315      | female | 23   | 浙江大学   | 3.6  | 5                     | 1            | 2          |
| 5    | 5432      | male   | 25   | 山东大学   | 3.8  | 20                    | 15           | 70         |
| 6    | 2131      | male   | 28   | 山东大学   | 3.3  | 15                    | 7            | 13         |
| 7    | 4321      | male   | 28   | 复旦大学   | 3.6  | 9                     | 6            | 52         |

第一行表示:id为1的用户的常用信息为使用的设备id为2138，性别为男，年龄21岁，北京大学，gpa为3.4，在过去的30天里面活跃了7天，发帖数量为2，回答数量为12

最后一行表示:id为7的用户的常用信息为使用的设备id为4321，性别为男，年龄28岁，复旦大学，gpa为3.6，在过去的30天里面活跃了9天，发帖数量为6，回答数量为52

题库练习明细表：question_practice_detail

| id   | device_id | question_id | result |
| ---- | --------- | ----------- | ------ |
| 1    | 2138      | 111         | wrong  |
| 2    | 3214      | 112         | wrong  |
| 3    | 3214      | 113         | wrong  |
| 4    | 6534      | 111         | right  |
| 5    | 2315      | 115         | right  |
| 6    | 2315      | 116         | right  |
| 7    | 2315      | 117         | wrong  |
| 8    | 5432      | 117         | wrong  |
| 9    | 5432      | 112         | wrong  |
| 10   | 2131      | 113         | right  |
| 11   | 5432      | 113         | wrong  |
| 12   | 2315      | 115         | right  |
| 13   | 2315      | 116         | right  |
| 14   | 2315      | 117         | wrong  |
| 15   | 5432      | 117         | wrong  |
| 16   | 5432      | 112         | wrong  |
| 17   | 2131      | 113         | right  |
| 18   | 5432      | 113         | wrong  |
| 19   | 2315      | 117         | wrong  |
| 20   | 5432      | 117         | wrong  |
| 21   | 5432      | 112         | wrong  |
| 22   | 2131      | 113         | right  |
| 23   | 5432      | 113         | wrong  |



第一行表示:id为1的用户的常用信息为使用的设备id为2138，在question_id为111的题目上，回答错误

......

最后一行表示:id为23的用户的常用信息为使用的设备id为5432，在question_id为113的题目上，回答错误

表：question_detail

| id   | question_id | difficult_level |
| ---- | ----------- | --------------- |
| 1    | 111         | hard            |
| 2    | 112         | medium          |
| 3    | 113         | easy            |
| 4    | 115         | easy            |
| 5    | 116         | medium          |
| 6    | 117         | easy            |

第一行表示: 题目id为111的难度为hard

....

第一行表示: 题目id为117的难度为easy

请你写一个SQL查询，计算不同学校、不同难度的用户平均答题量，根据示例，你的查询应返回以下结果(结果在小数点位数保留4位，4位之后四舍五入)：

| university | difficult_level | avg_answer_cnt |
| ---------- | --------------- | -------------- |
| 北京大学   | hard            | 1.0000         |
| 复旦大学   | easy            | 1.0000         |
| 复旦大学   | medium          | 1.0000         |
| 山东大学   | easy            | 4.5000         |
| 山东大学   | medium          | 3.0000         |
| 浙江大学   | easy            | 5.0000         |
| 浙江大学   | medium          | 2.0000         |

## 完整分析+代码

### 问题分解：

- 限定条件：无；
- 每个学校：按学校分组`group by university`
- 不同难度：按难度分组`group by difficult_level`
- 平均答题数：总答题数除以总人数`count(qpd.question_id) / count(distinct qpd.device_id)`
- 来自上面信息三个表，需要联表，up与qpd用device_id连接，qd与qpd用question_id连接。

------

### 细节问题：

- 表头重命名：as
- 平均值精度：保留4位小数round(x, 4)

### 完整代码：

```
select``  ``university,``  ``difficult_level,``  ``round(count(qpd.question_id) / count(distinct qpd.device_id), ``4``) as avg_answer_cnt``from question_practice_detail as qpd` `left join user_profile as up``on up.device_id=qpd.device_id` `left join question_detail as qd``on qd.question_id=qpd.question_id` `group by university, difficult_level
```

我的做法：

``` mysql
select t1.university, t3.difficult_level, count(t2.question_id)/count(distinct t2.device_id) avg_answer_cnt 
from user_profile t1 
right join question_practice_detail t2 
on t1.device_id = t2.device_id 
left join question_detail t3 
on t2.question_id = t3.question_id 
group by t1.university,t3.difficult_level
```

