package io.github.o2formm.feature.splash

import android.os.Bundle
import io.github.o2formm.BuildConfig
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.setVisible
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivitySplashBinding
import io.github.o2formm.feature.main.MainActivity
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

  override val binding: ActivitySplashBinding by lazy {
    ActivitySplashBinding.inflate(layoutInflater())
  }

  private val viewModel: SplashViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding.tvVersion.text = BuildConfig.VERSION_NAME

    viewModel.getAllDataFromSheet()

    viewModel.dataFromSheetLiveData.observe(this, ::observeServicesSheet)

  }

  private fun observeServicesSheet(viewState: AsyncViewState<Unit>) {

    when (viewState) {
      is AsyncViewState.Loading -> {
        binding.progressBar.setVisible(true)
      }

      is AsyncViewState.Error -> {
        showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Success -> {
        startActivity(MainActivity.newIntent(this))
        finishAffinity()
      }
    }
  }
}