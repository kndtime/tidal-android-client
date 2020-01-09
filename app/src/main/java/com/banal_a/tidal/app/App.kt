package com.banal_a.tidal.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.banal_a.tidal.app.di.AppComponent
import com.banal_a.tidal.app.di.DaggerAppComponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        //Instantiate Dagger
         DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        //Register activity lifeCycle callback listener for automatically injecting every activity
        //that launches
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
            override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
                p0?.let { AndroidInjection.inject(p0) }
            }
        })
    }

    abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(p0: Activity?) {
        }

        override fun onActivityResumed(p0: Activity?) {
        }

        override fun onActivityStarted(p0: Activity?) {
        }

        override fun onActivityDestroyed(p0: Activity?) {
        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        }

        override fun onActivityStopped(p0: Activity?) {
        }

        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}