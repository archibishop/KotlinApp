package com.example.wagubibrian.kotlinapp.dagger.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.example.wagubibrian.kotlinapp.data.AppDatabase
import com.example.wagubibrian.kotlinapp.data.GithubUsersDao
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
    fun provideDataDao(database: AppDatabase): GithubUsersDao = database.githubUsersDao()

}