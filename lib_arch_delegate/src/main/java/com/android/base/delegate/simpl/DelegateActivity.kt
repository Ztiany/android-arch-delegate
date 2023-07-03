package com.android.base.delegate.simpl

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.android.base.delegate.activity.ActivityDelegate
import com.android.base.delegate.activity.ActivityDelegateOwner
import com.android.base.delegate.State
import com.android.base.delegate.helper.ActivityDelegates

/**
 * @author Ztiany
 */
abstract class DelegateActivity : AppCompatActivity(), ActivityDelegateOwner {

    private val activityDelegates by lazy { ActivityDelegates(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        initialize(savedInstanceState)
        activityDelegates.callOnCreateBeforeSetContentView(savedInstanceState)
        super.onCreate(savedInstanceState)

        when (val layout = provideLayout()) {
            is View -> setContentView(layout)
            is Int -> setContentView(layout)
            null -> {
                /*no op*/
            }

            else -> throw IllegalArgumentException("the returned layout = $layout, which is not supported.")
        }

        activityDelegates.callOnCreateAfterSetContentView(savedInstanceState)
        setUpLayout(savedInstanceState)
    }

    protected open fun initialize(savedInstanceState: Bundle?) {}

    protected open fun provideLayout(): Any? = null

    protected abstract fun setUpLayout(savedInstanceState: Bundle?)

    override fun onRestart() {
        super.onRestart()
        activityDelegates.callOnRestart()
    }

    override fun onStart() {
        super.onStart()
        activityDelegates.callOnStart()
    }

    override fun onResume() {
        super.onResume()
        activityDelegates.callOnResume()
    }

    override fun onPause() {
        activityDelegates.callOnPause()
        super.onPause()
    }

    override fun onStop() {
        activityDelegates.callOnStop()
        super.onStop()
    }

    override fun onDestroy() {
        activityDelegates.callOnDestroy()
        super.onDestroy()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        activityDelegates.callOnPostCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        activityDelegates.callOnSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        activityDelegates.callOnRestoreInstanceState(savedInstanceState)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityDelegates.callOnActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        activityDelegates.callOnRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        activityDelegates.callOnResumeFragments()
    }

    @UiThread
    final override fun addDelegate(activityDelegate: ActivityDelegate<*>) {
        activityDelegates.addDelegate(activityDelegate)
    }

    @UiThread
    final override fun removeDelegate(activityDelegate: ActivityDelegate<*>): Boolean {
        return activityDelegates.removeDelegate(activityDelegate)
    }

    @UiThread
    final override fun findDelegate(predicate: (ActivityDelegate<*>) -> Boolean): ActivityDelegate<*>? {
        return activityDelegates.findDelegate(predicate)
    }

    override fun getStatus(): State {
        return activityDelegates.getStatus()
    }

}