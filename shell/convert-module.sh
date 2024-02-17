#!/bin/bash

JAR=$1
echo "Converting $JAR to module"

CONVERT_WORK_DIR=$(pwd)/..
TARGET_DIR=$CONVERT_WORK_DIR/target

TEMP_DIR=$TARGET_DIR/temp

mkdir -p $TEMP_DIR/work

# 生成module-info文件
jdeps --generate-module-info $TEMP_DIR/work $TARGET_DIR/libs/javafx/$JAR
# 获取module名称
MODULE=$(ls -l $TEMP_DIR/work | awk '{print $NF}' | grep -v 0)

# 解压jar包，编译的时候需要
mkdir $TEMP_DIR/classes
cd $TEMP_DIR/classes
jar xf $TARGET_DIR/libs/javafx/$JAR
# 编译module-info文件
cd $TEMP_DIR/work/$MODULE
javac -p $MODULE -d $TEMP_DIR/classes module-info.java

# 更新并生成新jar包
mkdir -p $TEMP_DIR/out
# 准备
cp $TARGET_DIR/libs/javafx/$JAR $TEMP_DIR/out/$MODULE.jar
# 更新
jar uf $TEMP_DIR/out/$MODULE.jar -C $TEMP_DIR/classes module-info.class
# 替换原jar包
cp -rf $TEMP_DIR/out/$MODULE.jar $TARGET_DIR/libs/javafx/$JAR

cd $CONVERT_WORK_DIR

rm -rf $TEMP_DIR

echo "Converting $JAR to module done."