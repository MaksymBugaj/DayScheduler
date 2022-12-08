package com.example.dayscheduler.util.livedata

class EmptySingleLiveEvent : SingleLiveEvent<Unit>() {
    fun emitEvent() {
        value = Unit
    }
}