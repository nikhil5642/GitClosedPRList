package com.example.navi.components.pullRequests

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView
import com.example.navi.databinding.ItemPullRequestBinding

class PullRequestItemAdapter(private val items:ObservableArrayList<PullRequestItemModel>):
    RecyclerView.Adapter<PullRequestItemAdapter.ViewHolder>() {

    private var mOnListChangedCallback: OnListChangedCallback? = null


    inner class ViewHolder(val binding: ItemPullRequestBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mOnListChangedCallback = OnListChangedCallback()
        items.addOnListChangedCallback(mOnListChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        items.removeOnListChangedCallback(mOnListChangedCallback)
        mOnListChangedCallback = null
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PullRequestItemAdapter.ViewHolder {

        return ViewHolder(ItemPullRequestBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PullRequestItemAdapter.ViewHolder, position: Int) {
            holder.binding.vm= items[position]
            Log.d("kladsjfd;da",""+position)
    }

    override fun getItemCount(): Int {
       return items.size
    }

    private fun onChanged(sender: ObservableArrayList<PullRequestItemModel>) {
        notifyItemRangeChanged(0, items.size)
    }

    private fun onItemRangeChanged(sender: ObservableArrayList<PullRequestItemModel>, positionStart: Int, itemCount: Int) {
        notifyItemRangeChanged(positionStart, itemCount)
    }

    private fun onItemRangeInserted(sender: ObservableArrayList<PullRequestItemModel>, positionStart: Int, itemCount: Int) {
        notifyItemRangeInserted(positionStart, itemCount)
    }

    private fun onItemRangeMoved(sender: ObservableArrayList<PullRequestItemModel>, fromPosition: Int, toPosition: Int, itemCount: Int) {
        for (i in 0 until itemCount) {
            notifyItemMoved(fromPosition + i, toPosition + i)
        }
    }

    private fun onItemRangeRemoved(sender: ObservableArrayList<PullRequestItemModel>, positionStart: Int, itemCount: Int) {
        notifyItemRangeRemoved(positionStart, itemCount)
    }
    private inner class OnListChangedCallback : ObservableList.OnListChangedCallback<ObservableArrayList<PullRequestItemModel>>() {

        override fun onChanged(sender: ObservableArrayList<PullRequestItemModel>) {
            this@PullRequestItemAdapter.onChanged(sender)
        }

        override fun onItemRangeChanged(sender: ObservableArrayList<PullRequestItemModel>, positionStart: Int, itemCount: Int) {
            this@PullRequestItemAdapter.onItemRangeChanged(sender, positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableArrayList<PullRequestItemModel>, positionStart: Int, itemCount: Int) {
            this@PullRequestItemAdapter.onItemRangeInserted(sender, positionStart, itemCount)
        }

        override fun onItemRangeMoved(sender: ObservableArrayList<PullRequestItemModel>, fromPosition: Int, toPosition: Int, itemCount: Int) {
            this@PullRequestItemAdapter.onItemRangeMoved(sender, fromPosition, toPosition, itemCount)
        }

        override fun onItemRangeRemoved(sender: ObservableArrayList<PullRequestItemModel>, positionStart: Int, itemCount: Int) {
            this@PullRequestItemAdapter.onItemRangeRemoved(sender, positionStart, itemCount)
        }
    }

}