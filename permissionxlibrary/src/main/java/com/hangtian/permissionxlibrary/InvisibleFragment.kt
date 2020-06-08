package com.hangtian.permissionxlibrary

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.Size
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * Author:         刘叶波
 * CreateDate:     2020/5/20 9:45
 * Description:    TODO
 *
 */
private const val permissionRequestCode = 1001
class InvisibleFragment:Fragment(),EasyPermissions.PermissionCallbacks {

    private var callback:()->Unit={}
    lateinit var note:String
    lateinit var perms : Array<out String>


    fun request(block: ()->Unit, note: String, @Size(min = 1) vararg perms: String) {
        callback = block
        this.note = note
        this.perms = perms
        initPermission()
    }


    @AfterPermissionGranted(permissionRequestCode)
    private fun initPermission() {
//        val mPermission = Manifest.permission.READ_CONTACTS

        if (EasyPermissions.hasPermissions(context?:return, *perms)) {
            callback()
        } else {
            EasyPermissions.requestPermissions(this,note,permissionRequestCode,*perms)
        }
    }


    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        // 若用户点击了禁止后不再询问，则调用一下弹出框，跳转到应用程序权限设置页面
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this)
                .setTitle("请求被拒绝")
                .setRationale("没有请求的权限，此应用程序可能无法正常工作。打开应用程序设置页面以修改应用程序权限")
                .build().show()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, pers: MutableList<String>) {
        if (EasyPermissions.hasPermissions(context ?: return, *perms)) {
            callback()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,this)
    }

}