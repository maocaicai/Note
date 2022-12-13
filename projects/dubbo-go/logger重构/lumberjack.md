# Golang 语言三方库 lumberjack 日志切割组件怎么使用？

**01** 

介绍

Golang 语言标准库的 log 包不支持日志切割，然而如果我们业务每天产生海量日志，日志文件就会越来越大，甚至会触发磁盘空间不足的报警，此时如果我们移动或者删除日志文件，需要先将业务停止写日志，很不方便。

而且大日志文件也不方便查询，多少有点失去日志的意义。在实际业务开发中，我们通常会按照日志文件大小或者日期进行日志切割。

常见的日志切割方式是由系统 Crontab 计划任务执行 Shell 文件进行日志切割或者由业务本身进行日志切割。

Golang 语言三方库 lumberjack 的作用就是进行日志切割，下面我们来介绍一下 lumberjack。

**02** 

lumberjack 使用

lumberjack 提供了一个滚动记录器 logger，它是一个控制写入日志的文件的日志组件，目前最新版本是 v2.0，需要使用 `gopkg.in` 导入。

安装方式：

```javascript
go get -u github.com/natefinch/lumberjack
```

复制

导入方式：

```javascript
import "gopkg.in/natefinch/lumberjack.v2"
```

复制

要将 lumberjack 与标准库的 log 包一起使用，只需在应用程序启动时将它传递到 SetOutput 函数。

示例代码：

```go
log.SetOutput(&lumberjack.Logger{
    Filename:   "/var/log/myapp/foo.log",
    MaxSize:    500, // megabytes
    MaxBackups: 3,
    MaxAge:     28, //days
    Compress:   true, // disabled by default
})
```

复制

lumberjack 的 Logger 结构体，是一个写入指定文件的 `io.WriteCloser`。Logger 在第一次写入时打开或创建日志文件。如果文件存在且小于 MaxSize 的值，lumberjack 将打开并追加到该文件。如果文件存在且其 size 的值为大于等于 MaxSize，文件通过将当前时间作为文件名的一部分进行重命名文件，然后使用原始文件名创建新的日志文件。

每当写入会导致当前日志文件超过 MaxSize 的值时，当前文件将关闭和重命名，并且使用原始名称创建的新日志文件。因此，您给 Logger 的文件名始终是当前日志文件。

备份使用给定给 Logger 的日志文件名，其中名称是没有扩展名的文件名，时间戳是日志与时间一起旋转的时间。时间格式是 `2006-01-02T15-04-05.000`，扩展是原始扩展。例如，如果您的 Logger.Filename 是 `/var/log/foo/server.log`，那么 2016 年 11 月 11 日下午 6：30 创建的备份将使用 filenam 是 `/var/log/foo/server-2016-11-04T18-30-00.000.log`。

**03** 

清理旧日志文件

每当创建新的日志文件时，旧日志文件可能会被删除。将保留根据编码时间戳保存的最新文件，文件数量最多等于 Maxbackups 的值，如果 Maxbackups 是 0，将删除所有文件。

无论 MaxBackups 值是什么，任何编码时间戳超过 MaxAge 值的文件都将被删除。

请注意，在时间戳中编码的时间是旋转时间，可能与上次写入该文件的时间不同。

如果 MaxBackups 和 MaxAge 都是 0，则不会删除任何旧日志文件。

**04** 

Logger 方法

- func (l *Logger) Close() error
- func (l *Logger) Rotate() error
- func (l *Logger) Write(p []byte) (n int, err error)

其中 Close 替代 `io.Closer`，关闭当前日志文件。

其中 Rotate 会导致记录器关闭现有日志文件并立即创建新日志文件。对于想要在正常 rotation 规则之外启动 rotation 的应用程序，这是一个 helper 函数，例如对 SIGHUP 的回应。

执行 Rotate 方法后，这将根据正常规则启动旧日志文件的清理。

示例代码：如何执行 Rotate 方法后，以响应 SIGHUP 的示例。

```javascript
l := &lumberjack.Logger{}
log.SetOutput(l)
c := make(chan os.Signal, 1)
signal.Notify(c, syscall.SIGHUP)

go func() {
    for {
        <-c
        l.Rotate()
    }
}()
```

复制

其中 Write 替代 `io.Writer`，如果写入会导致日志文件大于 MaxSize 的值，将关闭文件，重命名文件为包含当前时间的时间戳，并使用原始日志文件名创建新的日志文件。

如果写入长度大于 MaxSize 的值，则返回错误。

**05** 

总结

本文主要是介绍三方库 lumberjack 日志切割组件，包括安装、导入和使用方法。通常在实际项目开发中，用于替代 `io.Writer`

**参考资料：**

https://github.com/natefinch/lumberjack