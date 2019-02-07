package com.example.wagubibrian.kotlinapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class GithubUserViewModelFactory @Inject constructor(
    private val githubUserViewModel: GithubUserViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubUserViewModel::class.java!!)) {
            return githubUserViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}