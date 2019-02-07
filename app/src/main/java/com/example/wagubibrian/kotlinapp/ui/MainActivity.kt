package com.example.wagubibrian.kotlinapp.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.wagubibrian.kotlinapp.R
import com.example.wagubibrian.kotlinapp.data.GithubUsersData
import com.example.wagubibrian.kotlinapp.utils.Constants
import com.example.wagubibrian.kotlinapp.utils.InfiniteScrollListener
import com.example.wagubibrian.kotlinapp.viewmodel.GithubUserViewModel
import com.example.wagubibrian.kotlinapp.viewmodel.GithubUserViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var githubUserViewModelFactory: GithubUserViewModelFactory
    var githubUsersAdapter = GithubUsersAdapter(ArrayList())
    lateinit var githubUserViewModel: GithubUserViewModel
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        intializeRecycler()

        progressBar.visibility = View.VISIBLE

        githubUserViewModel = ViewModelProviders
            .of(this, githubUserViewModelFactory)
            .get(GithubUserViewModel::class.java)

        loadData()

        githubUserViewModel.githubUsersResults().observe(this,
            Observer<List<GithubUsersData>> {
                Log.d("Retrofit Call Complete", "There are ${it?.size} github users.")
                if (it != null) {
                    val position = githubUsersAdapter.itemCount
                    githubUsersAdapter.addUsers(it)
                    recycler.adapter = githubUsersAdapter
                    recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
                }
            })


        githubUserViewModel.githubUsersError().observe(this, Observer<String>{
            Log.d("Retrofit Call Error", "An error has occured $it")
        })

        githubUserViewModel.usersLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })

    }

    private fun loadData() {
        githubUserViewModel.loadGithubUsers(Constants.LIMIT, currentPage * Constants.OFFSET)
        currentPage ++
    }

    private fun intializeRecycler() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
        }
    }

    override fun onDestroy() {
    githubUserViewModel.disposeElements()
        super.onDestroy()
    }
}
