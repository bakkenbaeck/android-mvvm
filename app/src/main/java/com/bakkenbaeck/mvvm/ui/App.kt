package com.bakkenbaeck.mvvm.ui

import android.app.Application
import android.content.Context
import com.bakkenbaeck.mvvm.di.Modules


class App : Application() {

    init {
        instance = this
        Modules.init()
    }

    companion object {
        private lateinit var instance: App

        fun context(): Context {
            return instance.applicationContext
        }
    }
}