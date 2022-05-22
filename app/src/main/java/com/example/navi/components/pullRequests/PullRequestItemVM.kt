package com.example.navi.components.pullRequests

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import java.time.LocalDateTime

data class PullRequestItemModel(
    val id:String,
    val state: String,
    val repoName:String,
    val title:String,
    val description:String,
    val userName:String,
    val prNumber:Int,
    val creationDate:LocalDateTime?,
    val isMerged:Boolean,
    val mergedDate:LocalDateTime?){

}

fun PullRequestItemModelMapper(item:JsonObject): PullRequestItemModel {
    var creationDate:LocalDateTime?= null
    if(item["created_at"]!=JsonNull.INSTANCE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            creationDate = LocalDateTime.parse(item["created_at"].asString.dropLast(1))
        }
    }

    var mergedDate:LocalDateTime?= null
    if(item["merged_at"]!= JsonNull.INSTANCE) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mergedDate = LocalDateTime.parse(item["merged_at"].asString.dropLast(1))
        }
    }

    return PullRequestItemModel(item["id"].asString,
        item["state"].asString,
        item["base"].asJsonObject["repo"].asJsonObject["full_name"].asString,
        item["title"].asString,
        item["body"].asString,
        item["user"].asJsonObject["login"].asString,
        item["number"].asInt,
        creationDate,
        mergedDate!=null,
        mergedDate)
}
