package com.example.wagubibrian.kotlinapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.wagubibrian.kotlinapp.data.GithubUsersData
import com.example.wagubibrian.kotlinapp.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import java.util.concurrent.TimeUnit.MILLISECONDS

class GithubUserViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    var githubUsersResults: MutableLiveData<List<GithubUsersData>> = MutableLiveData()
    var githubUsersError: MutableLiveData<String> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<GithubUsersData>>

    fun githubUsersResults(): LiveData<List<GithubUsersData>> {
        return githubUsersResults
    }

    fun githubUsersError(): LiveData<String> {
        return githubUsersError
    }

    fun loadGithubUsers() {
        disposableObserver = object : DisposableObserver<List<GithubUsersData>>() {
            override fun onComplete() {

            }

            override fun onNext(users: List<GithubUsersData>) {
                githubUsersResults.postValue(users)
            }

            override fun onError(e: Throwable) {
                githubUsersError.postValue(e.message)
            }
        }

        dataRepository.getGithubUsersData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400, MILLISECONDS)
            .subscribe(disposableObserver)

    }

    fun disposeElements(){
        if(null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }

}