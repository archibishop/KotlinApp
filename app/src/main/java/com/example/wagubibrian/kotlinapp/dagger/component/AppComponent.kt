package com.example.wagubibrian.kotlinapp.dagger.component

import com.example.wagubibrian.kotlinapp.dagger.application.MyApplication
import com.example.wagubibrian.kotlinapp.dagger.modules.AppModule
import com.example.wagubibrian.kotlinapp.dagger.modules.BuildersModule
import com.example.wagubibrian.kotlinapp.dagger.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class, NetModule::class)
)
interface AppComponent {
    fun inject(app: MyApplication)
}
