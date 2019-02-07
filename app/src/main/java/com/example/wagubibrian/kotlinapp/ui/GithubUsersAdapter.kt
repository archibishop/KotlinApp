package com.example.wagubibrian.kotlinapp.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wagubibrian.kotlinapp.R
import com.example.wagubibrian.kotlinapp.data.GithubUsersData

class GithubUsersAdapter (
    users: List<GithubUsersData>?) : RecyclerView.Adapter<GithubUsersAdapter.GithubUsersViewHolder>(){

    private var githubUsersList = ArrayList<GithubUsersData>()

    init {
        this.githubUsersList =  users as ArrayList<GithubUsersData>
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GithubUsersViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_view,
            parent, false)
        return GithubUsersViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return githubUsersList.size
    }

    override fun onBindViewHolder(holder: GithubUsersViewHolder, position: Int) {
        val githubUser = githubUsersList[position]
        holder?.githubUsersListItem(githubUser)
    }

    fun addUsers(users: List<GithubUsersData>){
        val initPosition = githubUsersList.size
        githubUsersList.addAll(users)
        notifyItemRangeInserted(initPosition, githubUsersList.size)
    }

    class GithubUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var userId = itemView.findViewById<TextView>(R.id.users_id)

        fun githubUsersListItem(user: GithubUsersData) {
            userId.text = user.userName
        }
    }

}