package com.bakkenbaeck.mvvm.util

import android.app.Activity
import android.content.Intent

data class Navigation<T : Activity> (
        val clazz: Class<T>,
        val intent: Intent
)