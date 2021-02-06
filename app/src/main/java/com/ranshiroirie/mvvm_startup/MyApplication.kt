package com.ranshiroirie.mvvm_startup

import android.app.Application

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        RealmRepository.initRealm(this)
    }
}