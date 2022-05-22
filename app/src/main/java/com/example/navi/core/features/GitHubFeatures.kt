package com.example.navi.core.features

import com.example.navi.components.pullRequests.PullRequestItemModel
import com.example.navi.components.pullRequests.PullRequestItemModelMapper
import com.example.navi.core.request.RetrofitHttpClient
import io.reactivex.Observable

class GitHubFeatures {

    private val apiService=RetrofitHttpClient().getApiService()

    fun fetchAllPullRequests(userId:String,repoName:String,pageNo:Int): Observable<List<PullRequestItemModel>> {
        return apiService
            .fetchAllClosedPullRequests(userId,repoName,pageNo.toString())
            .map { it.map {it2-> PullRequestItemModelMapper(it2) } }

    }
}