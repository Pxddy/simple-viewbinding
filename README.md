# Simple ViewBinding

- Simplify ViewBinding in Fragments and Activities with Kotlin
- Automatically clears the binding to avoid memory leaks
- Extremely lightweight and simple library

## Usage

### 1. Activity

```kotlin
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
```

### 2. Fragment

```kotlin
class CounterFragment : Fragment(R.layout.counter_view) {

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
```

### 3. DialogFragment

```kotlin
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
```

## Getting Started

### 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

- Kotlin

```kotlin
allprojects {
    repositories {
        ...
        maven(url = "https://jitpack.io")
    }
}
```

### 2. Add the dependency

[![](https://jitpack.io/v/Pxddy/simple-viewbinding.svg)](https://jitpack.io/#Pxddy/simple-viewbinding)

```kotlin
dependencies {
    implementation("com.github.Pxddy:simple-viewbinding:v1.0.0")
}
```

### 3. Enable ViewBinding

https://developer.android.com/topic/libraries/view-binding#setup

## License

See the [LICENSE](LICENSE) file for license rights and limitations.