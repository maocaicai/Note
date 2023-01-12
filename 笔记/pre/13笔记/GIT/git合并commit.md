# Git合并多个commit

1. 问题：在提交(https://github.com/casbin/casbin/pull/1141)时，需要符合pr规范，即每个pr只能有一个commit，而我在本地commit多达12次了，那么此时直接提pr会不符合规范，此时就需要合并多个commit

   ## 步骤：

   1. 查看提交历史：看前20个

   ```git
   git log --oneline -20
   ```

​		![image-20221128172049283](C:\Users\mao'cai'cai\AppData\Roaming\Typora\typora-user-images\image-20221128172049283.png)

2. 合并提交历史，可以合并最近几个历史，也可以制定合并某几个历史，我这直接合并了最近的12个commit

```git
#合并前5个提交
git rebase -i HEAD~5

#或者：合并到某个提交
git rebase -i 0b26a0f775
```

在goland里执行代码后会跳出一个文件，可以在文件中编辑合并规则

3. 编辑合并规则
4. 根据Commands说明修改合并规则

```
pick 0b064204 最终修改
s c1ad1218 修改文件2
s e4e4034f 创建文件1
s 6e492400 删除文件3

# Rebase be4a2d33..6e492400 onto 6e492400 (4 commands) 【将be4a2d33..6e492400重新设定为6e492400（4个命令）】

# Commands: 
# p, pick <commit> = use commit【使用commit】
# r, reword <commit> = use commit, but edit the commit message【使用commit，但编辑commit消息】
# e, edit <commit> = use commit, but stop for amending【使用commit，但停止修改】
# s, squash <commit> = use commit, but meld into previous commit【使用commit，但合并到上一个commit中】
# f, fixup <commit> = like "squash", but discard this commit's log message【和squash一样，只是丢弃commit的日志消息】
# x, exec <command> = run command (the rest of the line) using shell【run command（行的其余部分）using shell】
# b, break = stop here (continue rebase later with 'git rebase —continue')【在此处停止（稍后使用“git rebase --continue”继续重新base）】
# d, drop <commit> = remove commit【删除commit】
# l, label <label> = label current HEAD with a name【用名称标记当前头部】
# t, reset <label> = reset HEAD to a label【将头部重置为标签】
# m, merge [-C <commit> | -c <commit>] <label> [# <oneline>] create a merge commit using the original merge commit's message (or the oneline, if no original merge commit was ). Use -c <commit> to reword the commit message.【使用原始合并提交的消息创建合并提交（如果没有原始合并提交，则为oneline）。使用-c<commit>重新编写commit消息。】
# These lines can be re-ordered; they are executed from top to bottom.
# If you remove a line here THAT COMMIT WILL BE LOST.
# However, if you remove everything, the rebase will be aborted.
# Note that empty commits are commented out
#这些行可以重新排序；它们从上到下执行。如果在此处删除一行，则提交操作将丢失。但是，如果删除所有内容，则将中止重新平衡。注意，空提交被注释掉了
```

4. 修改后可能会有一些冲突，此时在goland右下角显示正在变基字样，然后选择需要保存的版本
5. 解决冲突后，直接强制推送合并后的commit到远程分支

```
#强制推送（注意覆盖问题）
git push origin -f

```

