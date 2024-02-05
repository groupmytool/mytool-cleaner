# Convert commons-lang jar to a module

in this section, we pretend to be authors of the library

## commons-lang3-3.5.jar

```shell
cd convert
./build.sh commons-lang3-3.5.jar
jdeps --list-deps ../commons-lang3-3.5.jar
```

## dd-plist-1.27.jar

```shell
cd convert
./build.sh dd-plist-1.27.jar
jdeps --list-deps ../dd-plist-1.27.jar
```


