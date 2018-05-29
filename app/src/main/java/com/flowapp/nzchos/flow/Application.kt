package com.flowapp.nzchos.flow


import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco


class Application : Application() {

        companion object {
            lateinit var instance: com.flowapp.nzchos.flow.Application
        }

        override fun onCreate() {
            super.onCreate()
            instance = this
            Fresco.initialize(this)
        }
    }
