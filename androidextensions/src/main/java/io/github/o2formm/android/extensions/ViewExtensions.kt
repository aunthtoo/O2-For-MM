package io.github.o2formm.android.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import io.github.o2formm.android.extensions.layoutInflater

/**
 * Created by Vincent on 2/13/20
 */
fun ViewGroup.layoutInflater(): LayoutInflater {
  return this.context.layoutInflater()
}

fun View.setVisible(bool: Boolean) {
  if (bool)
    this.visibility = View.VISIBLE
  else
    this.visibility = View.GONE
}

fun View.showKeyboard() {
  ViewCompat.getWindowInsetsController(this)?.show(WindowInsetsCompat.Type.ime())
}

fun View.hideKeyboard() {
  ViewCompat.getWindowInsetsController(this)?.hide(WindowInsetsCompat.Type.ime())
}