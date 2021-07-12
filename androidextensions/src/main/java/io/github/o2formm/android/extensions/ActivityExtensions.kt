package io.github.o2formm.android.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import com.google.android.material.snackbar.BaseTransientBottomBar.Duration
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Vincent on 2/13/20
 */
fun Activity.showSnackbar(text: CharSequence, @Duration duration: Int) {
  val contentView = findViewById<View>(android.R.id.content)
  val snackbar = Snackbar.make(contentView, text, duration)
  snackbar.show()
}

fun Activity.showSnackbar(@StringRes stringResId: Int, @Duration duration: Int) {
  val contentView = findViewById<View>(android.R.id.content)
  val snackbar = Snackbar.make(contentView, stringResId, duration)
  snackbar.show()
}

fun Activity.showKeyboard() {
  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.hideKeyboard() {
  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(findViewById<ViewGroup>(android.R.id.content).windowToken, 0)
}