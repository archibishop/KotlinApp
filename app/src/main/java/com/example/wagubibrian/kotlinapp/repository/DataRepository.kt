package com.example.wagubibrian.kotlinapp.repository

import android.util.Log
import com.example.wagubibrian.kotlinapp.api.ApiInterface
import com.example.wagubibrian.kotlinapp.data.GithubUsersData
import com.example.wagubibrian.kotlinapp.data.GithubUsersDataDao
import io.reactivex.Observable
import javax.inject.Inject


class DataRepository @Inject constructor(val apiInterface: ApiInterface, val dao: GithubUsersDataDao){

    fun getGithubUsersData(): Observable<List<GithubUsersData>> {

        val observableApi = getUsersFromRemote()
        val observableRemote = getUsersFromDatabase()

        return Observable.concatArrayEager(observableApi, observableRemote)

    }

    private fun getUsersFromRemote(): Observable<List<GithubUsersData>> {
        return apiInterface
            .getUsers()
            .doOnNext {
                for (item in it) {
                    Log.e("ITEMS TO BE INSERTED", it.size.toString())
                    dao.insertUser(item)
                }
            }

    }

    private fun getUsersFromDatabase(): Observable<List<GithubUsersData>> {
        return dao
            .queryGithubUsers()
            .toObservable()
            .doOnNext{
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }

    }

}