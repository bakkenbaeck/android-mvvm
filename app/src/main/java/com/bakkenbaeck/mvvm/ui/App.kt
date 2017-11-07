package com.bakkenbaeck.mvvm.ui

import android.app.Application
import android.content.Context


class App : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: App

        fun context(): Context {
            return instance.applicationContext
        }
    }
}