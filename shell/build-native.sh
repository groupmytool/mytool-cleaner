#!/bin/bash

WORK_DIR=$(pwd)

cd ..

# change to your path
export GRAALVM_HOME=/Library/Java/JavaVirtualMachines/graalvm-community-openjdk-17.0.8+7.1/Contents/Home
export PATH=$GRAALVM_HOME/lib/installer/bin:$PATH
export PATH=$GRAALVM_HOME/lib/svm/bin:$PATH

export JAVA_HOME=$GRAALVM_HOME
export PATH=$JAVA_HOME/bin:$PATH

./mvnw clean
./mvnw gluonfx:build
./mvnw gluonfx:package

cd "$WORK_DIR" || return
