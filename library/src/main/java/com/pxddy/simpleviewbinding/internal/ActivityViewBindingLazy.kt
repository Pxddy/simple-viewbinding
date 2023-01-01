package com.pxddy.simpleviewbinding.internal

import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.viewbinding.ViewBinding

internal class ActivityViewBindingLazy<ViewBindingT : ViewBinding>(
    private val activity: ComponentActivity,
    private val viewBindingFactory: ViewBindingFactory<ViewBindingT>
) : ViewBindingLazy<ViewBindingT>() {

    override fun createBinding(): ViewBindingT {
        val view = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)
        checkNotNull(view) {
            buildString {
                appendLine("Activity $activity did not return a View!")
                appendLine("Forgot to provide a layout resource to the activity constructor?")
            }
        }
        return viewBindingFactory(view)
    }
}