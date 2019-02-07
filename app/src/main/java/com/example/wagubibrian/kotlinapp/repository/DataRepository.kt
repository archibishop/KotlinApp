package com.example.wagubibrian.kotlinapp.repository

import android.util.Log
import com.example.wagubibrian.kotlinapp.api.ApiInterface
import com.example.wagubibrian.kotlinapp.data.GithubUsersData
import com.example.wagubibrian.kotlinapp.data.GithubUsersDataDao
import com.example.wagubibrian.kotlinapp.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class DataRepository @Inject constructor(val apiInterface: ApiInterface, val dao: GithubUsersDataDao, val utils: Utils){

    fun getGithubUsersData(limit: Int, offset: Int): Observable<List<GithubUsersData>> {

        val hasConnection = utils.isConnectedToInternet()
        var observableApi: Observable<List<GithubUsersData>>? = null
        if(hasConnection) {
            observableApi = getUsersFromRemote()
        }
        val observableDb = getUsersFromDatabase(limit, offset)

        return if (hasConnection) Observable.concatArrayEager(observableApi, observableDb)
        else observableDb

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

    private fun getUsersFromDatabase(limit: Int, offset: Int): Observable<List<GithubUsersData>> {
        return dao
            .queryGithubUsers(limit, offset)
            .toObservable()
            .doOnNext{
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }

    }

}