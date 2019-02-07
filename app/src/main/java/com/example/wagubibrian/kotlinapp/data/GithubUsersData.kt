package com.example.wagubibrian.kotlinapp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class GithubUsersData (
    @Json(name = "id")
    @PrimaryKey
    var id: Int,

    @Json(name = "login")
    @ColumnInfo (name = "login")
    var userName: String?,

    @Json(name = "repos_url")
    @ColumnInfo (name = "repos_url")
    var repos_url: String?,

    @Json(name = "html_url")
    @ColumnInfo (name = "html_url")
    var html_url: String?,

    @Json(name = "avatar_url")
    @ColumnInfo (name = "avatar_url")
    var avatar_url: String
)