package io.github.o2formm.feature.callcenter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.withSafeBindingAdapterPosition
import io.github.o2formm.databinding.ItemCallCenterBinding
import io.github.o2formm.helper.diffCallBackWith

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class CallCenterListAdapter constructor(private val onItemClick: (item: CallCenterViewItem) -> Unit) :
  ListAdapter<CallCenterViewItem, CallCenterListAdapter.CallCenterListViewHolder>(
    diffCallBackWith(
      areItemTheSame = { item1, item2 -> item1.serviceId.id == item2.serviceId.id },
      areContentsTheSame = { item1, item2 -> item1 == item2 })
  ) {

  inner class CallCenterListViewHolder(val binding: ItemCallCenterBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallCenterListViewHolder {
    val binding = ItemCallCenterBinding.inflate(parent.layoutInflater(), parent, false)

    return CallCenterListViewHolder(binding).also { viewHolder ->
      viewHolder.itemView.setOnClickListener {
        viewHolder.withSafeBindingAdapterPosition { position ->
          getItem(position)?.let { itemAtIndex ->
            onItemClick(itemAtIndex)
          }
        }
      }
    }
  }

  override fun onBindViewHolder(holder: CallCenterListViewHolder, position: Int) {
    getItem(position)?.let { itemAtIndex ->
      holder.binding.apply {
        tvCallCenterName.text = itemAtIndex.name
        tvRemark.text = itemAtIndex.remark
      }
    }
  }
}