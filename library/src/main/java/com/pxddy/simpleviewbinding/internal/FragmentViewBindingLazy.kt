package com.pxddy.simpleviewbinding.internal

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

internal class FragmentViewBindingLazy<ViewBindingT : ViewBinding>(
    private val fragment: Fragment,
    private val viewBindingFactory: ViewBindingFactory<ViewBindingT>
) : ViewBindingLazy<ViewBindingT>() {

    override fun createBinding(): ViewBindingT {
        val view = fragment.requireView()

        fragment.parentFragmentManager.registerFragmentLifecycleCallbacks(
            FragmentViewDestroyedListener(),
            false
        )

        return viewBindingFactory(view)
    }

    private inner class FragmentViewDestroyedListener :
        FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            super.onFragmentViewDestroyed(fm, f)
            if (fragment === f) {
                clearBinding()
                fm.unregisterFragmentLifecycleCallbacks(this)
            }
        }
    }
}