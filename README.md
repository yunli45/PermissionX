# PermissionX
安卓第一行代码第三版第16章-- 编写并发布一个开源库， PermissionX； 实际就是一个SDK供别人调用
PermissionX是一个用于简化Android运行时权限用法的开源库。

依赖库的格式：

```c
groupId:artifactId:publishVersion
```



添加如下配置将PermissionX引入到你的项目当中：



```groovy
dependencies {
    ...
    implementation 'io.github.yunli45:PermissionX:1.0.1'
}
```

然后就可以使用如下语法结构来申请运行时权限了：

```kotlin
PermissionX.request(this,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS) { allGranted, deniedList ->
    if (allGranted) {
        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
    }
}
```

