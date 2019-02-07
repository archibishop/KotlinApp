package com.example.wagubibrian.kotlinapp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface GithubUsersDataDao {
    @Query("SELECT * FROM GithubUsersData ORDER BY id limit :limit offset :offset")
    fun queryGithubUsers(limit:Int, offset:Int): Single<List<GithubUsersData>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertUser(githubUsersData: GithubUsersData)


}