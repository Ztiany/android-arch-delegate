package com.android.base.delegate.activity

import androidx.annotation.UiThread
import com.android.base.delegate.State

@UiThread
interface ActivityDelegateOwner {

    fun addDelegate(activityDelegate: ActivityDelegate<*>)

    fun removeDelegate(activityDelegate: ActivityDelegate<*>): Boolean

    fun removeDelegateWhile(predicate: (ActivityDelegate<*>) -> Boolean)

    fun findDelegate(predicate: (ActivityDelegate<*>) -> Boolean): ActivityDelegate<*>?

    fun getCurrentState(): State

}