package io.github.o2formm.android.extensions

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

/**
Created By Aunt Htoo Aung on 08/07/2021.
 **/

fun ImageView.setDrawableColorTint(@ColorRes color: Int) {
  setColorFilter(
    ContextCompat.getColor(
      context,
      color
    ), android.graphics.PorterDuff.Mode.SRC_IN
  )
}