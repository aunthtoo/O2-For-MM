package io.github.o2formm.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivityMainBinding
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

  override val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater())
  }

  private val viewModel: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding.toolbar)

    viewModel.getAllServices()

    viewModel.allServicesLiveData.observe(this, ::observeAllServices)
  }

  private fun observeAllServices(viewState: AsyncViewState<String>) {
    when (viewState) {
      is AsyncViewState.Error -> {
        showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Loading -> {

      }

      is AsyncViewState.Success -> {
        binding.tv.setText(viewState.value)
      }
    }

  }


  companion object {

    fun newIntent(context: Context) = Intent(context, MainActivity::class.java).apply {

    }
  }
}