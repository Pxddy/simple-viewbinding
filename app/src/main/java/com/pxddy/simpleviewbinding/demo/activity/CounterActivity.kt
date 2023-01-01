package com.pxddy.simpleviewbinding.demo.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.pxddy.simpleviewbinding.demo.R
import com.pxddy.simpleviewbinding.demo.common.CounterViewModel
import com.pxddy.simpleviewbinding.demo.databinding.CounterViewBinding
import com.pxddy.simpleviewbinding.viewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CounterActivity : AppCompatActivity(R.layout.counter_view) {

    private val binding by viewBinding(CounterViewBinding::bind)
    private val vm: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            vm.counter
                .flowWithLifecycle(lifecycle)
                .onEach { updateCounterTextView(count = it) }
                .launchIn(this)
        }
    }

    private fun updateCounterTextView(count: Int) = with(binding) {
        countTextView.text = count.toString()
    }
}