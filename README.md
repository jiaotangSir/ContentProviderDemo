# ContentProviderDemo
使用esayPermission封装了权限申请，并用它实现了简单的读取通讯录功能

使用esayPermission封装了权限申请，权限类叫PermissionX，一行代码实现权限申请


###  1、准备。
a、先下载并加载jar包mypermissionx.jar(在app->libs文件夹下)，

b、再在build.gradle添加easypermissions依赖
```
    implementation 'pub.devrel:easypermissions:3.0.0'

```
###  2、使用
```
    PermissionX.request(this,"请允许申请通讯录和摄像头权限",Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA){
        showContact()
    }
```


