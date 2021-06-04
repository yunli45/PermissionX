package com.permissionx.ws

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

//Kotlin中的一个小技巧，typealias关键字可以用于给任意类型指定一个别名
typealias PermissionCallback = (Boolean, List<String>) -> Unit

class InvisibleFragment : Fragment() {

    private var callback: PermissionCallback? = null
    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        //来记录所有被用户拒绝的权限
        val deniedList = ArrayList<String>()
        for ((index, result) in grantResults.withIndex()) {
            if (result != PackageManager.PERMISSION_GRANTED) {

                deniedList.add(permissions[index])
            }

        }

        val allGranted = deniedList.isEmpty()

        callback?.let {
            it(allGranted, deniedList)
        }
    }
}