package com.example.navi.components.pullRequests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navi.R
import com.example.navi.core.PresentorFactory
import com.example.navi.databinding.FragmentPullRequestBinding

class PullRequestFragment : Fragment(), PullRequestPresenterCallback {
    private lateinit var binding: FragmentPullRequestBinding

    private lateinit var presenter: PullRequestPresenter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_pull_request, container, false)
        presenter= PresentorFactory().getPullRequestPresenter()
        presenter.onAttach(this)
        binding.vm=presenter.vm
        binding.pullToRefresh.setOnRefreshListener(presenter)
        binding.recyclerView.layoutManager = GridLayoutManager(context,1)
        binding.recyclerView.adapter = PullRequestItemAdapter(presenter.vm.objectList)

        return binding.root
    }

    override fun updateRefreshData(isRefreshing: Boolean) {
        binding.pullToRefresh.isRefreshing=isRefreshing

    }


}

interface  PullRequestPresenterCallback{
    fun updateRefreshData(isRefreshing:Boolean)
}