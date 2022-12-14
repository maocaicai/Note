[[Question\] - How to implement hierarchy based domain role model · Issue #1145 · casbin/casbin (github.com)](https://github.com/casbin/casbin/issues/1145)

### 问题：

Is there any possibility to get all domains for a user when I use `GetDomainsForuser('sujit')`?
Currently it's just giving me `[*]` instead of all associated domains `[* domain1.com domain2.com domain3.com]`

### 解决：您可以自己添加一层逻辑，如果输出是“*”那么调用e.GetAallDomains()来获得所有域名

You can add a layer of logic yourself and call e.GetAllDomains () to get all domains if the output is "*"