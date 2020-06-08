package com.hangtian.contentproviderdemo

import android.Manifest
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.provider.ContactsContract
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.AnimationSet
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.hangtian.permissionxlibrary.PermissionX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{

    lateinit var mAdapter:ArrayAdapter<String>
    private val mList = mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,mList)
        mListView.adapter = mAdapter

        btnShow.setOnClickListener {
            PermissionX.request(this,"请允许申请通讯录和摄像头权限",Manifest.permission.READ_CONTACTS, Manifest.permission.CAMERA){
                showContact()
            }

        }

    }

    private fun showContact() {
        mList.clear()
        val cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null)
        cursor?.apply {
            while (moveToNext()) {
                val name = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number = getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val sum = "$name：$number"

                mList.add(sum)
            }
            mAdapter.notifyDataSetChanged()
            close()
        }
    }
}
