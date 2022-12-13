Get all permission：您的使用是正确的：policies := e.GetPolicy()

Get all permission for users in the domain：您可以根据名字和域名去匹配：![image-20221213104503031](C:\Users\congyutao1\AppData\Roaming\Typora\typora-user-images\image-20221213104503031.png)比如，根据alice， oms匹配查找相应策略

Get all roles in the domain/Get all roles for user in domain：利用这个去匹配字段索引和名字，可以满足需求：filteredGroupingPolicy := e.GetFilteredGroupingPolicy(0, "alice")

Get all users/Get all users in domain：可以利用这个api去获得所有domain的user，res := e.GetAllUsersByDomain("domain1")



分页数据需要您自己去设置，暂时不支持分页功能



分成角色和权限是有必要的，通常我们不会直接把权限赋予给用户，而是通过角色来赋予给用户，因为用户拥有某一种权限是因为用户扮演着某一种角色。不管使用角色还是权限去过滤策略，都要根据您实际使用需求决定。



您可以尝试一下使用适配器存储到数据库中，Casbin用户可以使用adapter从存储中加载策略规则 (aka `LoadPolicy()`) 或者将策略规则保存到其中 (aka `SavePolicy()`)	[使用指南 | Casbin](https://casbin.org/zh/docs/adapters)







英文回复：

Get all permission：You are right           **policies := e.GetPolicy()**


Get all permission for users in the domain：You can match by name and domain name：
![image](https://user-images.githubusercontent.com/96519760/207239988-21610ddd-5946-434c-b641-72c9587adc30.png)For example, find the corresponding policy for alice and oms


Get all roles in the domain/Get all roles for user in domain：Using this api to match the field index and name can satisfy the requirement                 **filteredGroupingPolicy := e.GetFilteredGroupingPolicy(0, "alice")**


Get all users/Get all users in domain：You can use this api to get users for all domains        **res := e.GetAllUsersByDomain("domain1")**


You need to set the paging data by yourself. The paging function is not supported for the time being


The separation of roles and permissions is necessary, and usually we do not assign permissions directly to the user, but rather to the user through the role, because the user has certain permissions because the user plays a certain role. Whether you use roles or permissions to filter policies, you need to decide whether to use them.


You can try using the adapter to store to the database. The Casbin user can use the Adapter to load policy rules from the storage (aka LoadPolicy()) or save policy rules to it (aka SavePolicy()).[User Guide | Casbin](https://casbin.org/zh/docs/adapters)