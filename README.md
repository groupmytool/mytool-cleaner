

# Prerequisites

## java

> Requirement: OpenJDK21

> Check java version
```shell
java -version
```
```
openjdk version "21.0.1" 2023-10-17
OpenJDK Runtime Environment Homebrew (build 21.0.1)
OpenJDK 64-Bit Server VM Homebrew (build 21.0.1, mixed mode, sharing)
```
## maven
> Requirement：Maven 3.8.x 

> Check maven version
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


# Run
```shell
mvn clean compile javafx:run
```

# Build
```shell
./build.sh
```

