package io.github.o2formm.feature.filter

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.o2formm.R
import io.github.o2formm.android.extensions.context
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.setVisible
import io.github.o2formm.android.extensions.withSafeBindingAdapterPosition
import io.github.o2formm.databinding.ItemTownshipBinding
import io.github.o2formm.helper.diffCallBackWith

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class TownshipFilterListAdapter constructor(private val onItemClick: (position: Int, item: TownshipViewItem) -> Unit) :
  ListAdapter<TownshipViewItem, TownshipFilterListAdapter.TownshipFilterListViewHolder>(
    diffCallBackWith(
      areItemTheSame = { item1, item2 -> item1.id == item2.id },
      areContentsTheSame = { item1, item2 -> item1 == item2 })
  ) {

  inner class TownshipFilterListViewHolder(val binding: ItemTownshipBinding) :
    RecyclerView.ViewHolder(binding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownshipFilterListViewHolder {
    val binding = ItemTownshipBinding.inflate(parent.layoutInflater(), parent, false)

    return TownshipFilterListViewHolder(binding).also { viewHolder ->

      viewHolder.itemView.setOnClickListener {

        viewHolder.withSafeBindingAdapterPosition { position ->

          getItem(position)?.let { itemAtIndex ->
            onItemClick(position, itemAtIndex)
          }
        }
      }
    }
  }

  override fun onBindViewHolder(holder: TownshipFilterListViewHolder, position: Int) {
    getItem(position)?.let { itemAtIndex ->
      holder.binding.apply {
        tvTownship.text = itemAtIndex.townshipNameMM
        content.isSelected = itemAtIndex.isSelect

        if (itemAtIndex.isSelect) {
          ivSelect.setVisible(true)
          tvTownship.setTextColor(ContextCompat.getColor(holder.context(), R.color.orange))
        } else {
          ivSelect.setVisible(false)
          tvTownship.setTextColor(
            ContextCompat.getColor(
              holder.context(),
              R.color.textLightSecondary
            )
          )
        }

      }
    }
  }

}