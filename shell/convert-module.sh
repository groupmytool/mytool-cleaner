#!/bin/bash

JDK_HOME=$1
JAR_PATH=$2
JAR_NAME=$3
echo "Converting $JAR_NAME to module by $JDK_HOME ..."

# 判断$JAR_PATH/$JAR_NAME文件是否存在，不存在的话直接返回
if [ ! -f "$JAR_PATH/$JAR_NAME" ]; then
    echo "File $JAR_PATH/$JAR_NAME not found."
    exit 1
fi

TEMP_DIR=$JAR_PATH/temp

# 解压jar包，编译的时候需要
mkdir -p $TEMP_DIR/classes
cd $TEMP_DIR/classes
$JDK_HOME/bin/jar xf $JAR_PATH/$JAR_NAME

# 判断 $TEMP_DIR/classes/module-info.class 文件是否存在，不存在直接返回
if [ -f "$TEMP_DIR/classes/module-info.class" ]; then
    echo "$JAR_PATH/$JAR_NAME is module jar!"
    rm -rf $TEMP_DIR
    exit 0
fi

# 生成module-info文件
mkdir -p $TEMP_DIR/work
$JDK_HOME/bin/jdeps --generate-module-info $TEMP_DIR/work $JAR_PATH/$JAR_NAME
MODULE_NAME=$(ls -l $TEMP_DIR/work | awk '{print $NF}' | grep -v 0)

# 编译module-info文件
cd $TEMP_DIR/work/$MODULE_NAME
$JDK_HOME/bin/javac -p $MODULE_NAME -d $TEMP_DIR/classes module-info.java

# 更新并生成新jar包
mkdir -p $TEMP_DIR/out
# 准备
cp $JAR_PATH/$JAR_NAME $TEMP_DIR/out/$MODULE_NAME.jar
# 更新
$JDK_HOME/bin/jar uf $TEMP_DIR/out/$MODULE_NAME.jar -C $TEMP_DIR/classes module-info.class
# 替换原jar包
cp -rf $TEMP_DIR/out/$MODULE_NAME.jar $JAR_PATH/$JAR_NAME

rm -rf $TEMP_DIR

echo "Converted $JAR_NAME to module done."