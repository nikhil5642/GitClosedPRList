package com.example.navi.core

import com.example.navi.components.pullRequests.PullRequestPresenter
import com.example.navi.core.features.GitHubFeatures

class PresentorFactory {

    private val gitHubFeatures:GitHubFeatures= GitHubFeatures()

    fun getPullRequestPresenter():PullRequestPresenter{
        return PullRequestPresenter(gitHubFeatures)
    }
}