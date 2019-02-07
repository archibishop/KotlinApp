package com.example.wagubibrian.kotlinapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(GithubUsersData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun githubUsersDao(): GithubUsersDao
}