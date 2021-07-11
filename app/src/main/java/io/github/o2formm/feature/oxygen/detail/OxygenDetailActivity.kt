package io.github.o2formm.feature.oxygen.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivityOxygenDetailBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenDetailActivity : BaseActivity<ActivityOxygenDetailBinding>() {

  override val binding: ActivityOxygenDetailBinding by lazy {
    ActivityOxygenDetailBinding.inflate(layoutInflater())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  companion object {

    fun newIntent(context: Context) = Intent(context, OxygenDetailActivity::class.java).apply {

    }
  }
}