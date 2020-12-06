package com.jacktich.meetingnotifications.utils.supportclasses

import android.content.Context
import android.os.Handler
import android.widget.Toast
import com.jacktich.meetingnotifications.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

class DoubleBackPressExit(val context: Context) {

    companion object{
        const val DELAY_VALUE = 3000
    }

    private var doubleBackToExitPressedOnce = false
    fun press() {
        if (doubleBackToExitPressedOnce) {
            exitProcess(0)
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(
            context,
            context.getString(R.string.press_twice_for_closing_app),
            Toast.LENGTH_SHORT
        )
            .show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, DELAY_VALUE.toLong())

    }
}