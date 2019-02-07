package com.example.wagubibrian.kotlinapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wagubibrian.kotlinapp.data.GithubUsersData
import com.example.wagubibrian.kotlinapp.viewmodel.GithubUserViewModel
import com.example.wagubibrian.kotlinapp.viewmodel.GithubUserViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var githubUserViewModelFactory: GithubUserViewModelFactory
    lateinit var githubUserViewModel: GithubUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        githubUserViewModel = ViewModelProviders
            .of(this, githubUserViewModelFactory)
            .get(GithubUserViewModel::class.java)


        githubUserViewModel.loadGithubUsers()

        githubUserViewModel.githubUsersResults().observe(this,
            Observer<List<GithubUsersData>> {
                Log.d("Retrofit Call Complete", "There are ${it?.size} github users.")
            })

        githubUserViewModel.githubUsersError().observe(this, Observer<String>{
            Log.d("Retrofit Call Error", "An error has occured $it")
        })

    }

    override fun onDestroy() {
    githubUserViewModel.disposeElements()
        super.onDestroy()
    }
}
