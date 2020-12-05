package com.jacktich.meetingnotifications.view.activitymeetings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jacktich.meetingnotifications.R
import com.jacktich.meetingnotifications.view.base.view.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MeetingsActivity : BaseActivity(), HasAndroidInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector as DispatchingAndroidInjector<Any>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meetings)
    }

}