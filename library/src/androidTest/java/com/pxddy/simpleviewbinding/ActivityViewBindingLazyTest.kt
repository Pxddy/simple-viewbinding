package com.pxddy.simpleviewbinding

import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pxddy.simpleviewbinding.databinding.ActivityTestBinding
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityViewBindingLazyTest {

    @Test
    fun activityWithView_bindingProperlyCreated() {
        launchActivity<TestActivity>().use { scenario ->
            scenario.onActivity { activity ->
                val binding by activity.viewBinding(ActivityTestBinding::bind)

                binding.root shouldNotBe null
                binding.textView shouldNotBe null
                binding.textView shouldBeSameInstanceAs activity.findViewById(R.id.textView)
                binding.textView.text shouldBe activity.getString(R.string.hello_viewbinding_test)
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun activityWithoutView_throws() {
        launchActivity<TestActivityWithoutView>().use { scenario ->
            scenario.onActivity { activity ->
                val binding by activity.viewBinding(ActivityTestBinding::bind)
                binding.root
            }
        }
    }
}