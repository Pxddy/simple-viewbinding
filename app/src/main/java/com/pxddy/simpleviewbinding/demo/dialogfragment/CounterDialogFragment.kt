package com.pxddy.simpleviewbinding.demo.dialogfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.pxddy.simpleviewbinding.demo.R
import com.pxddy.simpleviewbinding.demo.common.CounterViewModel
import com.pxddy.simpleviewbinding.demo.databinding.CounterViewBinding
import com.pxddy.simpleviewbinding.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber

class CounterDialogFragment : DialogFragment(R.layout.counter_view) {

    private val binding by viewBinding(CounterViewBinding::bind)
    private val vm: CounterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            vm.counter
                .flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .onEach { updateCounterTextView(count = it) }
                .launchIn(this)
        }
    }

    private fun updateCounterTextView(count: Int) = with(binding) {
        countTextView.text = count.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("binding=%s is still accessible in onDestroyView()", binding)
    }
}