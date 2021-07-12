package io.github.o2formm.util

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri


/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
object FacebookIntentUtil {
  fun newFacebookIntent(pm: PackageManager, url: String): Intent? {
    var uri: Uri = Uri.parse(url)
    try {
      val applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0)
      if (applicationInfo.enabled) {
        // http://stackoverflow.com/a/24547437/1048340
        uri = Uri.parse("fb://facewebmodal/f?href=$url")
      }
    } catch (ignored: PackageManager.NameNotFoundException) {
    }
    return Intent(Intent.ACTION_VIEW, uri)
  }

}