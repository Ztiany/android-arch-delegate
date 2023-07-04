package com.android.base.delegate.activity

import androidx.annotation.UiThread
import com.android.base.delegate.State

interface ActivityDelegateOwner {

    @UiThread
    fun addDelegate(activityDelegate: ActivityDelegate<*>)

    @UiThread
    fun removeDelegate(activityDelegate: ActivityDelegate<*>): Boolean

    @UiThread
    fun removeDelegateWhile(predicate: (ActivityDelegate<*>) -> Boolean)

    @UiThread
    fun findDelegate(predicate: (ActivityDelegate<*>) -> Boolean): ActivityDelegate<*>?

    fun getCurrentState(): State

}