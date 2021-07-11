package io.github.o2formm.feature.splash

import android.os.Bundle
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivitySplashBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

  override val binding: ActivitySplashBinding by lazy {
    ActivitySplashBinding.inflate(layoutInflater())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }
}