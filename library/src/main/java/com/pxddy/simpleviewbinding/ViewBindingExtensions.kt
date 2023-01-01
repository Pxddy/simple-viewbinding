package com.pxddy.simpleviewbinding

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.pxddy.simpleviewbinding.internal.ActivityViewBindingLazy
import com.pxddy.simpleviewbinding.internal.FragmentViewBindingLazy
import com.pxddy.simpleviewbinding.internal.ViewBindingFactory

// View binding for Activity
fun <ViewBindingT : ViewBinding> ComponentActivity.viewBinding(
    viewBindingFactory: ViewBindingFactory<ViewBindingT>
): Lazy<ViewBindingT> = ActivityViewBindingLazy(
    activity = this,
    viewBindingFactory = viewBindingFactory
)

// View binding for Fragment
fun <ViewBindingT : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: ViewBindingFactory<ViewBindingT>
): Lazy<ViewBindingT> = FragmentViewBindingLazy(
    fragment = this,
    viewBindingFactory = viewBindingFactory
)