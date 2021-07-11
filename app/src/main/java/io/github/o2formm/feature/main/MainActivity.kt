package io.github.o2formm.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

  override val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding.toolbar)
  }

  companion object {

    fun newIntent(context: Context) = Intent(context, MainActivity::class.java).apply {

    }
  }
}