# Mytool Cleaner
Mytool Cleaner是一个软件卸载辅助工具，包含以下功能：
* 卸载应用程序
* 分析磁盘空间

# 运行

## 环境准备

* OpenJDK17+
* Maven3.8.x

## maven命令方式启动
```shell
./mvnw clean compile javafx:run
```

## 打包
使用构建脚本解决javafx-maven-plugin问题（将非模块jar更改为模块jar）：
```shell
cd shell && ./build-jpackage.sh
```

> 请注意：使用构建脚本的时候，需要确认一下脚本中的`JAVA_HOME`是否正确，如果不正确，需要修改为正确的`JAVA_HOME`。


