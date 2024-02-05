# 调用系统命令

## 编译

```shell
./native/build.sh
```

## java.library.path

```jvm
-Djava.library.path=/Users/adolphor/IdeaProjects/javafx/mytool-cleaner/lib/native
```

## pom

```xml
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.22.2</version>
    <configuration>
      <argLine>-Djava.library.path=${project.basedir}/native/</argLine>
    </configuration>
  </plugin>
```






