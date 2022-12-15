[[Question\] - How to implement hierarchy based domain role model · Issue #1145 · casbin/casbin (github.com)](https://github.com/casbin/casbin/issues/1145)

### 问题：

Is there any possibility to get all domains for a user when I use `GetDomainsForuser('sujit')`?
Currently it's just giving me `[*]` instead of all associated domains `[* domain1.com domain2.com domain3.com]`

### 解决：您可以自己添加一层逻辑，如果输出是“*”那么调用e.GetAallDomains()来获得所有域名

You can add a layer of logic yourself and call e.GetAllDomains () to get all domains if the output is "*"





## 问题2

Also same goes with the roles for user, I wanted to get all roles associated with user.

For e.g. `alice` is `admin` for `domain1.com` and `domain1.com` has `domain2.com`. So `alice` should also be `admin` to `domain2.com` and `enforcer.GetRolesForUserInDomain("alice", "domain2.com")` should have given me `[admin]` but it's empty



## 解决：您可以尝试使用一下这个api：e. GetImplicitRolesForUser() .与GetRolesForUserInDomain() 相比，该函数除了直接角色外还检索间接角色。

You can try this api: e.GetImplicitRolesForUser().In contrast to GetRolesForUserInDomain(), this function retrieves indirect roles in addition to direct roles.