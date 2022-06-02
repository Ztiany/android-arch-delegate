package com.android.base.delegate.simpl

import android.content.Intent
import android.os.Bundle
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.android.base.delegate.activity.ActivityDelegate
import com.android.base.delegate.activity.ActivityDelegateOwner
import com.android.base.delegate.activity.ActivityState
import com.android.base.delegate.helper.ActivityDelegates

/**
 * @author Ztiany
 */
abstract class DelegateActivity : AppCompatActivity(), ActivityDelegateOwner {

    private val activityDelegates by lazy { ActivityDelegates(this) }

    private var activityState = ActivityState.INITIALIZED

    override fun onCreate(savedInstanceState: Bundle?) {
        activityDelegates.callOnCreateBeforeSetContentView(savedInstanceState)
        super.onCreate(savedInstanceState)
        activityDelegates.callOnCreateAfterSetContentView(savedInstanceState)
    }

    override fun onRestart() {
        super.onRestart()
        activityDelegates.callOnRestart()
    }

    override fun onStart() {
        super.onStart()
        activityState = ActivityState.START
        activityDelegates.callOnStart()
    }

    override fun onResume() {
        super.onResume()
        activityState = ActivityState.RESUME
        activityDelegates.callOnResume()
    }

    override fun onPause() {
        activityState = ActivityState.PAUSE
        activityDelegates.callOnPause()
        super.onPause()
    }

    override fun onStop() {
        activityState = ActivityState.STOP
        activityDelegates.callOnStop()
        super.onStop()
    }

    override fun onDestroy() {
        activityState = ActivityState.DESTROY
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
        grantResults: IntArray
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
        activityDelegates.addActivityDelegate(activityDelegate)
    }

    @UiThread
    final override fun removeDelegate(activityDelegate: ActivityDelegate<*>): Boolean {
        return activityDelegates.removeActivityDelegate(activityDelegate)
    }

    @UiThread
    final override fun findDelegate(predicate: (ActivityDelegate<*>) -> Boolean): ActivityDelegate<*>? {
        return activityDelegates.findDelegate(predicate)
    }

    override fun getStatus(): ActivityState {
        return activityState
    }

}