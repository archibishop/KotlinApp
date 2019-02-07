package com.example.wagubibrian.kotlinapp.dagger.modules

import com.example.wagubibrian.kotlinapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

}