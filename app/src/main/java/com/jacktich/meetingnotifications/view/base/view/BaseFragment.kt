package com.jacktich.meetingnotifications.view.base.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment: Fragment(), MVPView {

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    private fun performDependencyInjection()  = AndroidSupportInjection.inject(this)
}