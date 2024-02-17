#!/bin/bash

WORK_DIR=$(pwd)

# change to your path
export JAVA_HOME=/Library/Java/JavaVirtualMachines/openjdk-17.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH

cd ..
./mvnw clean install
cd "$WORK_DIR" || return

source ./convert-module.sh dd-plist-1.27.jar
cd "$WORK_DIR" || return

source ./convert-module.sh java-semver-0.10.2.jar
cd "$WORK_DIR" || return

app_name="MyTool CLeaner"
main_module="com.mytool.cleaner"
main_class="com.mytool.cleaner.CleanerApp"
runtime_image="runtime-image"
app_version="1.0.1"
app_type="app-image"
# app_type="dmg"

# 裁切
jlink --module-path ../target/libs/launch:../target/libs/javafx \
      --add-modules ${main_module} \
      --output ../target/${runtime_image} \
      --no-header-files \
      --no-man-pages
# 打包
jpackage --type ${app_type} \
         --name "${app_name}" \
         --app-version ${app_version} \
         --module ${main_module}/${main_class} \
         --runtime-image ../target/${runtime_image} \
         --dest ../target/

