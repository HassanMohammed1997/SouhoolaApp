package com.hassanmohammed.souhoolaapp.utils

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentViewBindingDelegate<T : ViewDataBinding>(
    val fragment: Fragment,
    val factory: (View) -> T
) : ReadOnlyProperty<Fragment, T> {
    private var binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            val viewLifeCycleOwnerLiveData = Observer<LifecycleOwner?> {
                val viewLifeCycleOwner = it ?: return@Observer
                viewLifeCycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                    override fun onDestroy(owner: LifecycleOwner) {
                        super.onDestroy(owner)
                        binding = null
                    }
                })
            }

            override fun onCreate(owner: LifecycleOwner) {
                super.onCreate(owner)
                fragment.viewLifecycleOwnerLiveData.observeForever(viewLifeCycleOwnerLiveData)
            }

            override fun onDestroy(owner: LifecycleOwner) {
                super.onDestroy(owner)
                fragment.viewLifecycleOwnerLiveData.removeObserver(viewLifeCycleOwnerLiveData)
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return binding ?: initBinding(fragment)
    }

    private fun initBinding(fragment: Fragment): T {
        val lifeCycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifeCycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException("Should not attempt to get bindings when Fragment views are destroyed.")
        }

        return factory(fragment.requireView()).also { this.binding = it }
    }

}

fun <T : ViewDataBinding> Fragment.fragmentViewBinding(factory: (View) -> T) =
    FragmentViewBindingDelegate(this, factory)

fun <T : ViewDataBinding> AppCompatActivity.activityViewBinding(factory: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        factory(layoutInflater)
    }