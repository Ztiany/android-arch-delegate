package com.android.base.delegate.fragment

import androidx.annotation.UiThread
import com.android.base.delegate.State

@UiThread
interface FragmentDelegateOwner {

    fun addDelegate(fragmentDelegate: FragmentDelegate<*>)

    fun removeDelegate(fragmentDelegate: FragmentDelegate<*>): Boolean

    fun removeDelegateWhile(predicate: (FragmentDelegate<*>) -> Boolean)

    fun findDelegate(predicate: (FragmentDelegate<*>) -> Boolean): FragmentDelegate<*>?

    fun getStatus(): State

}