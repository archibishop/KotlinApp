package com.example.wagubibrian.kotlinapp.dagger.application

import android.app.Activity
import android.app.Application
import com.example.wagubibrian.kotlinapp.dagger.component.DaggerAppComponent
import com.example.wagubibrian.kotlinapp.dagger.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication: Application(), HasActivityInjector {
    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}