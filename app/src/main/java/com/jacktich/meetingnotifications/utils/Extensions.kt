package com.jacktich.meetingnotifications.utils

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

fun View.makeVisible() {
    if (this.visibility == View.GONE || this.visibility == View.INVISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    if (this.visibility == View.VISIBLE || this.visibility == View.INVISIBLE) {
        visibility = View.GONE
    }
}

fun Fragment.navFragment(destination: Int, bundle: Bundle? = null) {
    if (bundle != null) {
        view?.findNavController()?.navigate(destination, bundle)
    } else {
        view?.findNavController()?.navigate(destination)
    }
}