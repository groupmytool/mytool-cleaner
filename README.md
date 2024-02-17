# Mytool Cleaner
Mytool Cleaner is a software uninstallation auxiliary tool, which includes the following features:
* Uninstall applications
* Analyze disk space

# Run

## Prerequisites
* OpenJDK17+
* Maven3.8.x

## Running by maven command
```shell
./mvnw clean compile javafx:run
```

## Build
Use build shell to resolve javafx-maven-plugin issue (convert non module jar to module jar):
```shell
cd shell && ./build-jpackage.sh
```

> Please note: When using the build script, you need to confirm whether the `JAVA_HOME` in the script is correct. 
> If it is not correct, you need to modify it to the correct `JAVA_HOME`.

# Other languages
* [中文](./README.zh.md)