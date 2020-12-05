package com.jacktich.meetingnotifications.view.base.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection

abstract class BaseActivity: AppCompatActivity(), MVPView {

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
    }

    private fun performDependencyInjection()  = AndroidInjection.inject(this)

}