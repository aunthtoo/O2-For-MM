package io.github.o2formm.android.extensions

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 * Safely receive ViewHolder [ViewHolder.getBindingAdapter] without having tyo check for [RecyclerView.NO_POSITION].
 *
 * @param doOnNoPosition A function that is invoked when the position returns
 * @param doOnSafePosition A function that will be executed if the position is not [RecyclerView.NO_POSITION], position is passed into this function
 */
fun ViewHolder.withSafeBindingAdapterPosition(
  doOnNoPosition: (() -> (Unit)) = {
    //Do Nothing
  },
  doOnSafePosition: ((@ParameterName("position") Int) -> (Unit))
) {
  val position = bindingAdapterPosition
  if (position != RecyclerView.NO_POSITION) {
    doOnSafePosition.invoke(position)
  } else {
    doOnNoPosition.invoke()
  }
}

fun ViewHolder.context() : Context {
  return itemView.context
}