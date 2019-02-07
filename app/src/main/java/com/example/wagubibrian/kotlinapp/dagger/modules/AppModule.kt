package com.example.wagubibrian.kotlinapp.dagger.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.example.wagubibrian.kotlinapp.data.AppDatabase
import com.example.wagubibrian.kotlinapp.data.GithubUsersDataDao
import com.example.wagubibrian.kotlinapp.utils.Utils
import com.example.wagubibrian.kotlinapp.viewmodel.GithubUserViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application){

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java, "Github_Database").
        build()

    @Provides
    @Singleton
    fun provideDataDao(database: AppDatabase): GithubUsersDataDao = database.githubUsersDataDao()

    @Provides
    @Singleton
    fun provideGithubUserViewModelFactory(
        factory: GithubUserViewModelFactory): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)

}