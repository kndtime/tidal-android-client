package com.banal_a.tidal.app.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.banal_a.tidal.app.App
import javax.inject.Inject

open class BaseViewModel : ViewModel(), LifecycleObserver {
    //@Inject
    //lateinit var symplRepository: Repository
    /*@Inject
    lateinit var accountManager: AccountManager*/

    init {
        //initializeDagger()
    }

    //private fun initializeDagger() = App.appComponent.inject(this)
}