
```shell
app_name="MyTool CLeaner"
main_module="com.mytool.cleaner"
main_class="com.mytool.cleaner.CleanerApp"
runtime_image="runtime-image"
app_version="1.0.1"

rm -rf ./target/${app_name}.app
jpackage --type app-image \
         --name ${app_name} \
         --app-version ${app_version} \
         -m ${main_module}/${main_class} \
         --runtime-image ./target/${runtime_image} \
         --dest ./target/
# 安装包
rm -rf ./target/${app_name}.dmg
jpackage --type dmg \
         --name ${app_name} \
         --app-version ${app_version} \
         -m ${main_module}/${main_class} \
         --runtime-image ./target/${runtime_image} \
         --dest ./target/
```


