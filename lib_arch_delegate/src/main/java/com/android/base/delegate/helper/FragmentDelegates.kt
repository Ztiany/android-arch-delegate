package com.android.base.delegate.helper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.UiThread
import androidx.fragment.app.Fragment
import com.android.base.delegate.State
import com.android.base.delegate.fragment.FragmentDelegate
import com.android.base.delegate.fragment.FragmentDelegateOwner

/**
 * @author Ztiany
 */
@UiThread
class FragmentDelegates(private val fragment: Fragment) : FragmentDelegateOwner {

    private val delegates: MutableList<FragmentDelegate<*>> = ArrayList(4)

    @Volatile internal var fragmentState = State.INITIAL

    fun callOnAttach(context: Context) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onAttach(context)
        }
    }

    fun callOnCreate(savedInstanceState: Bundle?) {
        fragmentState = State.CREATE
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onCreate(savedInstanceState)
        }
    }

    fun callOnViewCreated(view: View, savedInstanceState: Bundle?) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onViewCreated(view, savedInstanceState)
        }
    }

    fun callOnActivityCreated(savedInstanceState: Bundle?) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onActivityCreated(savedInstanceState)
        }
    }

    fun callOnStart() {
        fragmentState = State.START
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onStart()
        }
    }

    fun callOnResume() {
        fragmentState = State.RESUME
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onResume()
        }
    }

    fun callOnPause() {
        fragmentState = State.PAUSE
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onPause()
        }
    }

    fun callOnStop() {
        fragmentState = State.STOP
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onStop()
        }
    }

    fun callOnDestroy() {
        fragmentState = State.DESTROY
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onDestroy()
        }
    }

    fun callOnDestroyView() {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onDestroyView()
        }
    }

    fun callOnDetach() {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onDetach()
        }
    }

    fun callSetUserVisibleHint(isVisibleToUser: Boolean) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.setUserVisibleHint(isVisibleToUser)
        }
    }

    fun callOnSaveInstanceState(savedInstanceState: Bundle) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onSaveInstanceState(savedInstanceState)
        }
    }

    fun callOnHiddenChanged(hidden: Boolean) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onHiddenChanged(hidden)
        }
    }

    fun callOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun callOnRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        for (fragmentDelegate in delegates) {
            fragmentDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun addDelegate(fragmentDelegate: FragmentDelegate<*>) {
        delegates.add(fragmentDelegate)
        @Suppress("UNCHECKED_CAST")
        (fragmentDelegate as FragmentDelegate<Fragment>).onAttachToFragment(fragment)
    }

    override fun removeDelegate(fragmentDelegate: FragmentDelegate<*>): Boolean {
        val remove = delegates.remove(fragmentDelegate)
        if (remove) {
            fragmentDelegate.onDetachFromFragment()
        }
        return remove
    }

    override fun findDelegate(predicate: (FragmentDelegate<*>) -> Boolean): FragmentDelegate<*>? {
        for (delegate in delegates) {
            if (predicate(delegate)) {
                return delegate
            }
        }
        return null
    }

    override fun getStatus(): State {
        return fragmentState
    }

}