package com.android.base.delegate.simpl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.UiThread
import androidx.fragment.app.DialogFragment
import com.android.base.delegate.fragment.FragmentDelegate
import com.android.base.delegate.fragment.FragmentDelegateOwner
import com.android.base.delegate.helper.FragmentDelegates

/**
 * @author Ztiany
 */
open class DelegateDialogFragment : DialogFragment(), FragmentDelegateOwner {

    private val fragmentDelegates by lazy { FragmentDelegates(this) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentDelegates.callOnAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentDelegates.callOnCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDelegates.callOnViewCreated(view, savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentDelegates.callOnActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        fragmentDelegates.callOnStart()
    }

    override fun onResume() {
        super.onResume()
        fragmentDelegates.callOnResume()
    }

    override fun onPause() {
        fragmentDelegates.callOnPause()
        super.onPause()
    }

    override fun onStop() {
        fragmentDelegates.callOnStop()
        super.onStop()
    }

    override fun onDestroyView() {
        fragmentDelegates.callOnDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        fragmentDelegates.callOnDestroy()
        super.onDestroy()
    }

    override fun onDetach() {
        fragmentDelegates.callOnDetach()
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        fragmentDelegates.callOnSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Java")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        fragmentDelegates.callSetUserVisibleHint(isVisibleToUser)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        fragmentDelegates.callOnHiddenChanged(hidden)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fragmentDelegates.callOnRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fragmentDelegates.callOnActivityResult(requestCode, resultCode, data)
    }

    @UiThread
    override fun addDelegate(fragmentDelegate: FragmentDelegate<*>) {
        fragmentDelegates.addDelegate(fragmentDelegate)
    }

    @UiThread
    override fun removeDelegate(fragmentDelegate: FragmentDelegate<*>): Boolean {
        return fragmentDelegates.removeDelegate(fragmentDelegate)
    }

    @UiThread
    override fun removeDelegateWhile(predicate: (FragmentDelegate<*>) -> Boolean) {
        fragmentDelegates.removeDelegateWhile(predicate)
    }

    @UiThread
    override fun findDelegate(predicate: (FragmentDelegate<*>) -> Boolean): FragmentDelegate<*>? {
        return fragmentDelegates.findDelegate(predicate)
    }

    override fun getStatus() = fragmentDelegates.getStatus()

}