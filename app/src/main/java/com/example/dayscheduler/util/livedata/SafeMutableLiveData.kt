package com.example.dayscheduler.util.livedata

@Suppress("UNCHECKED_CAST")
open class SafeMutableLiveData<T>() : SafeLiveData<T>() {
    constructor(value: T) : this() {
        super.setValue(value)
    }

    public override fun setValue(value: T) = super.setValue(value)
    public override fun postValue(value: T) = super.postValue(value)
}