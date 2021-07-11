package io.github.o2formm.android.extensions

import android.util.Log
import android.widget.Spinner

/**
 * Safely select Spinner and invoke [doOnError] function when an error is thrown during selection
 *
 * @param position Position to be selected
 * @param doOnError A function that is invoked when an error is thrown, by default this log the exception
 * @param doAfterSelect A optional function that can be executed if the selection is successfully completed
 */
fun Spinner.safeSelection(
  position: Int,
  doOnError: ((Exception) -> (Unit)) = { error ->
    Log.e("SpinnerExtensions", Log.getStackTraceString(error))
  },
  doAfterSelect: ((Int) -> (Unit)) = { _ ->
    //Do Nothing
  }
) {
  try {
    setSelection(position)
    doAfterSelect(position)
  } catch (e: Exception) {
    doOnError.invoke(e)
  }

}