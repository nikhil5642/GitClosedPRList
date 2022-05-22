package com.example.navi.core.request

import com.example.navi.components.pullRequests.PullRequestItemModel
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("/repos/{userId}/{repoName}/pulls?state=close")
    fun fetchAllClosedPullRequests(@Path("userId") userId:String, @Path("repoName") repoName:String,@Query("page") pageNo:String): Observable<List<JsonObject>>
}
