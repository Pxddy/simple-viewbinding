package com.pxddy.simpleviewbinding.demo.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.pxddy.simpleviewbinding.demo.R
import com.pxddy.simpleviewbinding.demo.common.navigateTo
import com.pxddy.simpleviewbinding.demo.databinding.FragmentMainBinding
import com.pxddy.simpleviewbinding.viewBinding
import timber.log.Timber

class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() = with(binding) {
        openActivityButton.setOnClickListener {
            navigateTo(navDirections = MainFragmentDirections.actionMainFragmentToCounterActivity())
        }

        openFragmentButton.setOnClickListener {
            navigateTo(navDirections = MainFragmentDirections.actionMainFragmentToCounterFragment())
        }

        openDialogButton.setOnClickListener {
            navigateTo(
                navDirections = MainFragmentDirections.actionMainFragmentToCounterDialogFragment()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("binding=%s is still accessible in onDestroyView()", binding)
    }
}