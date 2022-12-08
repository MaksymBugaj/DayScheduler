package com.example.dayscheduler.util.livedata

import androidx.lifecycle.LiveData

@Suppress("UNCHECKED_CAST")
open class SafeLiveData<T> : LiveData<T>() {

    override fun getValue(): T = super.getValue() as T
}