package com.example.wagubibrian.kotlinapp.dagger.component

import android.app.Application
import com.example.wagubibrian.kotlinapp.dagger.modules.AppModule
import com.example.wagubibrian.kotlinapp.dagger.modules.BuildersModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class)
)
interface AppComponent {
    fun inject(app: Application)
}
