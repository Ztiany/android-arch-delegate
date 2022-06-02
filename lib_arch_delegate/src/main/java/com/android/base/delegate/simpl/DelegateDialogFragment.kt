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
        fragmentDelegates.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentDelegates.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentDelegates.onViewCreated(view, savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentDelegates.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        fragmentDelegates.onStart()
    }

    override fun onResume() {
        super.onResume()
        fragmentDelegates.onResume()
    }

    override fun onPause() {
        fragmentDelegates.onPause()
        super.onPause()
    }

    override fun onStop() {
        fragmentDelegates.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        fragmentDelegates.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        fragmentDelegates.onDestroy()
        super.onDestroy()
    }

    override fun onDetach() {
        fragmentDelegates.onDetach()
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        fragmentDelegates.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Java")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        fragmentDelegates.setUserVisibleHint(isVisibleToUser)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        fragmentDelegates.onHiddenChanged(hidden)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        fragmentDelegates.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        fragmentDelegates.onActivityResult(requestCode, resultCode, data)
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
    override fun findDelegate(predicate: (FragmentDelegate<*>) -> Boolean): FragmentDelegate<*>? {
        return fragmentDelegates.findDelegate(predicate)
    }

}