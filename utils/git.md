# Git常用操作

## 远程创建仓库后，与本地某个仓库关联

本地操作：

1. git init
2. git remote add origin https://coding.jd.com/congyutao1/test.git   //与远程仓库关联
3. git add .
4.  git commit -m "Initial commit"  
5.  git push -u origin master     //将代码推送到远程主分支



## 设置本地用户名和邮箱

1. 设置全局用户名和邮箱：

   git config --global user.name "congyutao1"       
   git config --global user.email "congyutao1@jd.com"   

2. 设置某个仓库的用户名和邮箱：适用于公司电脑上需要向个人git账户提交代码时的情况

git config user.name "congyutao1"       
git config user.email "congyutao1@jd.com"   

3. 查看当前全局配置：git config --list