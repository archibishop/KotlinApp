package com.example.wagubibrian.kotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wagubibrian.kotlinapp.api.ApiClass
import com.example.wagubibrian.kotlinapp.api.ApiInterface
import com.example.wagubibrian.kotlinapp.model.GithubUsers
import com.example.wagubibrian.kotlinapp.model.GithubUsersResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

//import java.util.*

class MainActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showGithubUsers()
    }

    private fun showGithubUsers() {
        val response = getGithubUsers()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<List<GithubUsers>>(){
                override fun onNext(t: List<GithubUsers>) {
                    Log.d("Retrofit Call Complete", " Size: " + t.size)
                }


                override fun onComplete() {
                    Log.d("Retrofit Call Error", "An error has occured")
                    Log.d("Retrofit Call Complete", "Successful Retrofit Call")
                }

                override fun onError(e: Throwable) {
                    Log.d("Retrofit Call Error", "An error has occured")
                }
            })
    }

    fun handleResponse(githubUsersResponse: GithubUsersResponse){
        Log.d("Github Users", "" + githubUsersResponse.items)

    }

    private fun getGithubUsers(): Observable<List<GithubUsers>> {
        val retrofit = ApiClass.getClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface.getUsers()
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
