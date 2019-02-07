package com.example.wagubibrian.kotlinapp.api

import com.example.wagubibrian.kotlinapp.model.GithubUsers
import retrofit2.http.GET
import io.reactivex.Observable

interface ApiInterface {

    @GET("/users?q=location:uganda+language:java")
    fun getUsers(): Observable<List<GithubUsers>>
}