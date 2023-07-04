package com.android.base.delegate.fragment

import androidx.annotation.UiThread
import com.android.base.delegate.State

interface FragmentDelegateOwner {

    @UiThread
    fun addDelegate(fragmentDelegate: FragmentDelegate<*>)

    @UiThread
    fun removeDelegate(fragmentDelegate: FragmentDelegate<*>): Boolean

    @UiThread
    fun removeDelegateWhile(predicate: (FragmentDelegate<*>) -> Boolean)

    @UiThread
    fun findDelegate(predicate: (FragmentDelegate<*>) -> Boolean): FragmentDelegate<*>?

    fun getCurrentState(): State

}