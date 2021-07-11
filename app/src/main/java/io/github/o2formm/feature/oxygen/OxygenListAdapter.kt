package io.github.o2formm.feature.oxygen

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.withSafeBindingAdapterPosition
import io.github.o2formm.databinding.ItemOxygenBinding
import io.github.o2formm.helper.diffCallBackWith

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenListAdapter constructor(private val onItemClick: (item: OxygenViewItem) -> Unit) :
  ListAdapter<OxygenViewItem, OxygenListAdapter.OxygenListViewHolder>(
    diffCallBackWith(
      areItemTheSame = { item1, item2 -> item1 == item2 },
      areContentsTheSame = { item1, item2 -> item1 == item2 })
  ) {

  class OxygenListViewHolder(val binding: ItemOxygenBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OxygenListViewHolder {
    val binding = ItemOxygenBinding.inflate(parent.layoutInflater(), parent, false)

    return OxygenListViewHolder(binding).also { viewItem ->
      viewItem.itemView.setOnClickListener {

        viewItem.withSafeBindingAdapterPosition { position ->
          getItem(position)?.let { itemAtIndex ->
            onItemClick(itemAtIndex)
          }
        }
      }
    }
  }

  override fun onBindViewHolder(holder: OxygenListViewHolder, position: Int) {
    getItem(position)?.let { itemAtIndex ->
      holder.binding.apply {
        tvName.text = itemAtIndex.nameMm
        tvTownship.text = itemAtIndex.townshipMm
      }
    }
  }
}