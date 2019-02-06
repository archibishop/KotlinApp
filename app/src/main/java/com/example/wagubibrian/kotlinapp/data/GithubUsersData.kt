package com.example.wagubibrian.kotlinapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class GithubUsersData (
    @PrimaryKey var id: Int,
    @ColumnInfo (name = "username") var userName: String?,
    @ColumnInfo (name = "first_name") var firstName: String?,
    @ColumnInfo (name = "last_name") var lastName: String?,
    @ColumnInfo (name = "no_repo") var noRepos: String
)