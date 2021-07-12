package io.github.o2formm.android.extensions

import androidx.fragment.app.Fragment
import io.github.o2formm.android.extensions.hideKeyboard
import io.github.o2formm.android.extensions.showKeyboard

/**
Created By Aunt Htoo Aung on 06/07/2021.
 **/

fun Fragment.showKeyboard() {
  requireActivity().showKeyboard()
}

fun Fragment.hideKeyboard() {
  requireActivity().hideKeyboard()
}