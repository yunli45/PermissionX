package com.permissionx.ws

import androidx.fragment.app.FragmentActivity

//将PermissionX指定成单例类，是为了让PermissionX中的接口能够更加方便地 被调用。
object PermissionX {
    private const val TAG = "InvisibleFragment"
    fun request(
        activity: FragmentActivity,
        vararg permissions: String,
        callback: PermissionCallback
    ) {
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)

        /**
         * 先获取FragmentManager的实例，然后调用
        findFragmentByTag()方法来判断传入的Activity参数中是否已经包含了指定TAG的 Fragment，也就是我们刚才编写的InvisibleFragment。如果已经包含则直接使用该
        Fragment，否则就创建一个新的InvisibleFragment实例，并将它添加到Activity中，同时
        指定一个TAG。注意，在添加结束后一定要调用commitNow()方法，而不能调用commit()方
        法，因为commit()方法并不会立即执行添加操作，因而无法保证下一行代码执行时
        InvisibleFragment已经被添加到Activity中了。
         */
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment

        }
        // * :表示将一个 数组转换成可变长度参数传递过去。
        fragment.requestNow(callback, *permissions)
    }
}