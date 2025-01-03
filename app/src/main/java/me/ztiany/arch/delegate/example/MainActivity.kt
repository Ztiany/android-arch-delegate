package me.ztiany.arch.delegate.example

import android.os.Bundle
import com.android.base.delegate.simpl.DelegateActivity
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : DelegateActivity() {

    init {
        Timber.plant(DebugTree())
    }

    override fun provideLayout() = R.layout.activity_main

    override fun setUpLayout(savedInstanceState: Bundle?) {
    }

}