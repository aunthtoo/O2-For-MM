package io.github.o2formm.feature.callcenter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.withSafeBindingAdapterPosition
import io.github.o2formm.databinding.ItemPhoneBinding
import io.github.o2formm.helper.diffCallBackWith

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class CallCenterPhoneListAdapter constructor(private val onItemClick: (item: String) -> Unit) :
  ListAdapter<String, CallCenterPhoneListAdapter.CallCenterPhoneListViewHolder>(
    diffCallBackWith(
      areItemTheSame = { item1, item2 -> item1 == item2 },
      areContentsTheSame = { item1, item2 -> item1 == item2 })
  ) {

  inner class CallCenterPhoneListViewHolder(val binding: ItemPhoneBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallCenterPhoneListViewHolder {

    val binding = ItemPhoneBinding.inflate(parent.layoutInflater(), parent, false)

    return CallCenterPhoneListViewHolder(binding).also { viewHolder ->
      viewHolder.binding.btnCall.setOnClickListener {
        viewHolder.withSafeBindingAdapterPosition { position ->

          getItem(position)?.let { itemAtIndex ->
            onItemClick(itemAtIndex)
          }
        }
      }
    }
  }

  override fun onBindViewHolder(holder: CallCenterPhoneListViewHolder, position: Int) {
    getItem(position)?.let { itemAtIndex ->
      holder.binding.apply {
        tvPhone.text = itemAtIndex
      }
    }
  }


}