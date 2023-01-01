package com.pxddy.simpleviewbinding.internal

import androidx.annotation.MainThread
import androidx.viewbinding.ViewBinding

internal abstract class ViewBindingLazy<out ViewBindingT : ViewBinding> : Lazy<ViewBindingT> {
    private var binding: ViewBindingT? = null

    override val value: ViewBindingT
        get() = getBinding()

    override fun isInitialized(): Boolean = binding != null

    fun clearBinding() {
        binding = null
    }

    @MainThread
    private fun getBinding(): ViewBindingT = binding ?: createBinding().also {
        binding = it
    }

    abstract fun createBinding(): ViewBindingT
}