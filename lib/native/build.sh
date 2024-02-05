#!/bin/bash

# com.mytool.cleaner.utils.FileMetaData.java 生成 com_mytool_cleaner_utils_FileMetaData.h
javac -h . ../src/main/java/com/mytool/cleaner/utils/FileMetaData.java
rm ../src/main/java/com/mytool/cleaner/utils/FileMetaData.class
# FileMetaData.cpp 中引用 com_mytool_cleaner_utils_FileMetaData.h，并生成 libFileMetaData.dylib
g++ -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/darwin" -dynamiclib -o libFileMetaData.dylib -framework CoreServices FileMetaData.cpp

