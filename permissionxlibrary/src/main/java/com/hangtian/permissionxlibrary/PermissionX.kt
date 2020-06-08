package com.hangtian.permissionxlibrary

import androidx.fragment.app.FragmentActivity

/**
 * Author:         刘叶波
 * CreateDate:     2020/5/20 11:50
 * Description:    TODO
 *
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"
    fun request(activity: FragmentActivity,note:String="请允许申请相关权限",vararg permissions:String,callback: ()->Unit) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment!=null) {
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.request(callback,note,*permissions)
    }
}