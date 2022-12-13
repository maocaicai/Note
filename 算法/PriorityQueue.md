# PriorityQueue自定义排序：

```java
PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                int sumO1 = o1.get(0) + o1.get(1);
                int sumO2 = o2.get(0) + o2.get(1);
                return sumO1-sumO2;
            }
        });


PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> o1 - o2)
```

