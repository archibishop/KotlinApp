package com.example.wagubibrian.kotlinapp.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface GithubUsersDao {
    @Query("SELECT * FROM GithubUsersData")
    fun queryGithubUsers(): LiveData<List<GithubUsersData>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertUser(githubUsersData: GithubUsersData)


}