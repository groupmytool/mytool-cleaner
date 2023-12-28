

# 构建
## 准备

### java

> 要求：OpenJDK21

> 环境验证
```shell
java -version
```
```
openjdk version "21.0.1" 2023-10-17
OpenJDK Runtime Environment Homebrew (build 21.0.1)
OpenJDK 64-Bit Server VM Homebrew (build 21.0.1, mixed mode, sharing)
```
### maven
> 要求：Maven 3.8.x 
 
```shell
mvn -version
```
```
Apache Maven 3.8.8 (4c87b05d9aedce574290d1acc98575ed5eb6cd39)
Maven home: /Users/adolphor/Applications/maven/apache-maven-3.8.8
Java version: 21.0.1, vendor: Homebrew, runtime: /opt/homebrew/Cellar/openjdk/21.0.1/libexec/openjdk.jdk/Contents/Home
Default locale: zh_CN_#Hans, platform encoding: UTF-8
OS name: "mac os x", version: "14.2.1", arch: "aarch64", family: "mac"
```

## 运行
```shell
mvn clean compile javafx:run
```

## 构建

```shell
# 裁切编译
mvn clean compile javafx:jlink
# 手动打包
app_name="MyTool CLeaner"
main_module="com.mytool.cleaner"
main_class="com.mytool.cleaner.CleanerApp"
runtime_image="runtime-image"
app_version="1.0.1"
app_type="app-image"
# app_type="dmg"
jpackage --type ${app_type} \
         --name "${app_name}" \
         --app-version ${app_version} \
         --module ${main_module}/${main_class} \
         --runtime-image ./target/${runtime_image} \
         --dest ./target/
```


