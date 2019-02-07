package com.example.wagubibrian.kotlinapp.model

data class GithubUsersResponse(var total_count: String, var incomplete_results: Boolean, var items: List<GithubUsers>)