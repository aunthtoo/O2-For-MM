package io.github.o2formm.android.extensions

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
Created By Aunt Htoo Aung on 27/06/2021.
 **/

fun TextView.setDrawableTint(@ColorRes color: Int) {

  this.compoundDrawablesRelative.forEach { drawable ->
    drawable?.mutate()
    drawable?.colorFilter =
      PorterDuffColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_IN)
  }
}