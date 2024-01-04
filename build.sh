#!/bin/bash

graal21

app_name="MyTool CLeaner"
main_module="com.mytool.cleaner"
main_class="com.mytool.cleaner.CleanerApp"
runtime_image="runtime-image"
app_version="1.0.1"
app_type="app-image"
# app_type="dmg"

mvn clean javafx:jlink
jpackage --type ${app_type} \
         --name "${app_name}" \
         --app-version ${app_version} \
         --module ${main_module}/${main_class} \
         --runtime-image ./target/${runtime_image} \
         --dest ./target/

