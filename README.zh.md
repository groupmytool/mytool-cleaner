# Mytool Cleaner
Mytool Cleaner是一个软件卸载辅助工具，包含以下功能：
* 卸载应用程序
* 分析磁盘空间

# 运行

## 环境准备

* OpenJDK17+
* Maven3.8.x

## 运行
主函数入口:
```
com.mytool.cleaner.CleanerApp.java
```

## 打包
```shell
./mvnw clean package
```
运行文件: `target/mytool-cleaner.app`
