package com.pxddy.simpleviewbinding

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pxddy.simpleviewbinding.databinding.FragmentTestBinding
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeSameInstanceAs
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentViewBindingLazyTest {

    @Test
    fun fragmentWithView_bindingProperlyCreated() {
        launchFragmentInContainer<TestFragment>().use { scenario ->
            scenario.onFragment { fragment ->
                val binding = fragment.binding

                binding.root shouldNotBe null
                binding.editText shouldNotBe null
                binding.root shouldBeSameInstanceAs fragment.requireView()
                binding.editText shouldBeSameInstanceAs fragment.requireView()
                    .findViewById(R.id.editText)
                binding.editText.text?.toString() shouldBe fragment.getString(R.string.hello_viewbinding_test)
            }
        }
    }

    @Test
    fun fragmentWithView_doesNotThrow_whenAccessBindingBeforeDestroyed() {
        launchFragmentInContainer<TestFragment>().use { scenario ->
            scenario.onFragment { fragment ->
                scenario.moveToState(Lifecycle.State.DESTROYED)
                fragment.text shouldBe TestFragment.textOnDestroyView
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun fragmentWithView_throws_whenAccessBindingAfterDestroyed() {
        launchFragmentInContainer<TestFragment>().use { scenario ->
            scenario.onFragment { fragment ->
                scenario.moveToState(Lifecycle.State.DESTROYED)
                fragment.binding.root
            }
        }
    }

    @Test(expected = IllegalStateException::class)
    fun fragmentWithoutView_throws() {
        launchFragmentInContainer<TestFragmentWithoutView>().use { scenario ->
            scenario.onFragment { fragment ->
                val binding = fragment.binding
                binding.root
            }
        }
    }
}

class TestFragment : Fragment(R.layout.fragment_test) {

    val binding by viewBinding(FragmentTestBinding::bind)
    var text: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.doAfterTextChanged {
            text = it?.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.editText.setText(textOnDestroyView)
    }

    companion object {
        const val textOnDestroyView = "Changed text in onDestroyView without crashing :)"
    }
}

class TestFragmentWithoutView : Fragment() {

    val binding by viewBinding(FragmentTestBinding::bind)
}