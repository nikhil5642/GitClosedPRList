package com.example.navi.components.pullRequests

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField

class PullRequestModel(var userId: String,var repoId:String,val handler:PullRequestModelHandler){

    val isLoading=ObservableField<Boolean>(false)
    val objectList=ObservableArrayList<PullRequestItemModel>()
    fun changeUserId(){
            handler.onRefresh()
    }
}


interface PullRequestModelHandler{
    fun onRefresh()
}