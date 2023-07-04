package com.android.base.delegate.helper

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.android.base.delegate.State
import com.android.base.delegate.activity.ActivityDelegate
import com.android.base.delegate.activity.ActivityDelegateOwner

/**
 * @author Ztiany
 */
class ActivityDelegates(private val activity: AppCompatActivity) : ActivityDelegateOwner {

    private val delegates: MutableList<ActivityDelegate<*>> = ArrayList(4)

    @Volatile private var activityState = State.INITIAL

    fun callOnCreateBeforeSetContentView(savedInstanceState: Bundle?) {
        for (activityDelegate in delegates) {
            activityDelegate.onCreateBeforeSetContentView(savedInstanceState)
        }
    }

    fun callOnCreateAfterSetContentView(savedInstanceState: Bundle?) {
        activityState = State.CREATE
        for (activityDelegate in delegates) {
            activityDelegate.onCreateAfterSetContentView(savedInstanceState)
        }
    }

    fun callOnRestoreInstanceState(savedInstanceState: Bundle?) {
        for (activityDelegate in delegates) {
            activityDelegate.onRestoreInstanceState(savedInstanceState!!)
        }
    }

    fun callOnPostCreate(savedInstanceState: Bundle?) {
        for (activityDelegate in delegates) {
            activityDelegate.onPostCreate(savedInstanceState)
        }
    }

    fun callOnSaveInstanceState(outState: Bundle) {
        for (activityDelegate in delegates) {
            activityDelegate.onSaveInstanceState(outState)
        }
    }

    fun callOnDestroy() {
        activityState = State.DESTROY
        for (activityDelegate in delegates) {
            activityDelegate.onDestroy()
        }
    }

    fun callOnStop() {
        activityState = State.STOP
        for (activityDelegate in delegates) {
            activityDelegate.onStop()
        }
    }

    fun callOnPause() {
        activityState = State.PAUSE
        for (activityDelegate in delegates) {
            activityDelegate.onPause()
        }
    }

    fun callOnResume() {
        activityState = State.RESUME
        for (activityDelegate in delegates) {
            activityDelegate.onResume()
        }
    }

    fun callOnStart() {
        activityState = State.START
        for (activityDelegate in delegates) {
            activityDelegate.onStart()
        }
    }

    fun callOnRestart() {
        for (activityDelegate in delegates) {
            activityDelegate.onRestart()
        }
    }

    fun callOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (activityDelegate in delegates) {
            activityDelegate.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun callOnRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        for (activityDelegate in delegates) {
            activityDelegate.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    fun callOnResumeFragments() {
        for (activityDelegate in delegates) {
            activityDelegate.onResumeFragments()
        }
    }

    @UiThread
    override fun addDelegate(activityDelegate: ActivityDelegate<*>) {
        if (delegates.contains(activityDelegate)) {
            Log.w("ActivityDelegates", "addDelegate: $activityDelegate has already been added.")
            return
        }
        delegates.add(activityDelegate)
        @Suppress("UNCHECKED_CAST")
        (activityDelegate as ActivityDelegate<Activity>).onAttachedToActivity(activity)
    }

    @UiThread
    override fun removeDelegate(activityDelegate: ActivityDelegate<*>): Boolean {
        val remove = delegates.remove(activityDelegate)
        if (remove) {
            activityDelegate.onDetachedFromActivity()
        }
        return remove
    }

    @UiThread
    override fun removeDelegateWhile(predicate: (ActivityDelegate<*>) -> Boolean) {
        val iterator = delegates.iterator()
        while (iterator.hasNext()) {
            val delegate = iterator.next()
            if (predicate(delegate)) {
                iterator.remove()
                delegate.onDetachedFromActivity()
            }
        }
    }

    @UiThread
    override fun findDelegate(predicate: (ActivityDelegate<*>) -> Boolean): ActivityDelegate<*>? {
        for (delegate in delegates) {
            if (predicate(delegate)) {
                return delegate
            }
        }
        return null
    }

    override fun getCurrentState(): State {
        return activityState
    }

}