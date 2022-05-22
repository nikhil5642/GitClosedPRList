package com.example.navi.components.pullRequests

import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.navi.core.features.GitHubFeatures
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PullRequestPresenter (val gitHubFeatures: GitHubFeatures) : PullRequestModelHandler,
    SwipeRefreshLayout.OnRefreshListener {
    lateinit var vm:PullRequestModel
    private val compositeDisposable = CompositeDisposable()
    var pageNo=1
    var isNewDataPresent=true
    lateinit var fragmentCallback: PullRequestPresenterCallback
    fun onAttach(callback:PullRequestPresenterCallback){
        this.vm = PullRequestModel("gfxfundamentals","threejsfundamentals",this)
        fragmentCallback=callback
        loadPullRequests()
    }

    private fun loadPullRequests(){
        if(isNewDataPresent){
            compositeDisposable.add(gitHubFeatures
                .fetchAllPullRequests(vm.userId,vm.repoId,pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataReceivedSuccess,this::onDataReceivedFailure))
        }

    }

    private fun onDataReceivedSuccess(response: List<PullRequestItemModel>) {
        if(pageNo==1){
            vm.objectList.clear()
        }
        vm.objectList.addAll(response)
        pageNo+=1
        if(response.isEmpty()){
            isNewDataPresent=false
        }
        fragmentCallback.updateRefreshData(false)
        vm.isLoading.set(false)
    }

    private fun onDataReceivedFailure(failure: Any){
        fragmentCallback.updateRefreshData(false)
        vm.isLoading.set(false)
        Log.d("Failure",failure.toString())
    }

    override fun onRefresh() {
        pageNo=1
        isNewDataPresent=true
        fragmentCallback.updateRefreshData(true)
        vm.isLoading.set(true)
        loadPullRequests()
    }
}