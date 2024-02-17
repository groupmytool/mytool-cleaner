# JVM runtime

## Prerequisites

### java

> Requirement: OpenJDK17+

```shell
# change to your path
export JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-17.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
```

> Check java version

```shell
java --version
```

```
openjdk version "17.0.9" 2023-10-17
OpenJDK Runtime Environment Homebrew (build 17.0.9+0)
OpenJDK 64-Bit Server VM Homebrew (build 17.0.9+0, mixed mode, sharing)
```

### maven

> Requirement：Maven wrapper

## Run

```shell
./mvnw clean compile javafx:run
```

## Build
use build shell to resolve javafx-maven-plugin issue (convert non module jar to module jar):
```shell
cd shell && ./build-jpackage.sh
```

# Native runtime

## Prerequisites

### Xcode

```
# config xcode
sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
```

### Java
* [GraalVM for JDK 17 Community 17.0.8](https://github.com/graalvm/graalvm-ce-builds/releases/tag/jdk-17.0.8)

> Requirement: GraalVM for JDK 17 Community 17.0.8

```shell
# change to your path
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-community-openjdk-17.0.8+7.1/Contents/Home
export PATH=$GRAALVM_HOME/lib/installer/bin:$PATH
export PATH=$GRAALVM_HOME/lib/svm/bin:$PATH

export JAVA_HOME=$GRAALVM_HOME
export PATH=$JAVA_HOME/bin:$PATH
```

> Check java version

```
java --version
gu --version
```

### maven

> Requirement：Maven wrapper


## build
```shell
./mvnw clean
./mvnw gluonfx:build
./mvnw gluonfx:nativerun
./mvnw gluonfx:package
```


