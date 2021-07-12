package io.github.o2formm.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import com.google.android.material.tabs.TabLayoutMediator
import io.github.o2formm.R
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivityMainBinding
import io.github.o2formm.databinding.FragmentTownshipFilterBinding
import io.github.o2formm.feature.filter.FilterByTownshipFragment
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {

  override val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater())
  }

  private val servicesPagerAdapter by lazy {
    ServicesPagerAdapter(this)
  }

  private val viewModel: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding.toolbar)

    viewModel.getAllServices()

    viewModel.allServicesLiveData.observe(this, ::observeAllServices)
  }

  private fun observeAllServices(viewState: AsyncViewState<List<ServiceTypeViewItem>>) {
    when (viewState) {
      is AsyncViewState.Error -> {
        showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Loading -> {

      }

      is AsyncViewState.Success -> {

        val servicesList = viewState.value
        binding.viewPager.adapter = servicesPagerAdapter
        binding.viewPager.offscreenPageLimit = servicesList.size
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
          tab.text = servicesList[position].type
        }.attach()

        servicesPagerAdapter.submitList(servicesList)

      }
    }

  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    return when (item.itemId) {
      R.id.actionFilterByTown -> {

        val fragment = FilterByTownshipFragment.newInstance()

        fragment.show(supportFragmentManager, FilterByTownshipFragment.TAG)

        true
      }
      R.id.actionAbout -> {

        true
      }
      else -> {
        super.onOptionsItemSelected(item)
      }
    }

  }

  //township filter
  private fun showTownshipFilter() {
    val dialog = AlertDialog.Builder(this).create()

    val dialogViewBinding = FragmentTownshipFilterBinding.inflate(layoutInflater())

    dialog.setView(dialogViewBinding.root)
    dialog.window?.apply {
      setBackgroundDrawableResource(android.R.color.transparent)
    }

    dialog.setCancelable(true)
    dialog.show()

  }

  companion object {

    fun newIntent(context: Context) = Intent(context, MainActivity::class.java).apply {

    }
  }
}