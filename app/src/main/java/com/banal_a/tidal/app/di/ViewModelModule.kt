package com.banal_a.tidal.app.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.banal_a.tidal.app.viewmodel.BaseViewModel
import com.banal_a.tidal.app.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(BaseViewModel::class)
    internal abstract fun bindRepoViewModel(baseViewModel: BaseViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}