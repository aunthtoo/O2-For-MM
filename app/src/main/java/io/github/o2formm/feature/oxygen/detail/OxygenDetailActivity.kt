package io.github.o2formm.feature.oxygen.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseActivity
import io.github.o2formm.databinding.ActivityOxygenDetailBinding
import io.github.o2formm.domain.sheet.model.ServiceId
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenDetailActivity : BaseActivity<ActivityOxygenDetailBinding>() {

  override val binding: ActivityOxygenDetailBinding by lazy {
    ActivityOxygenDetailBinding.inflate(layoutInflater())
  }

  private val id: ServiceId by lazy {
    ServiceId(intent?.getIntExtra(EXTRA_SERVICE_ID, 0)!!)
  }

  private val viewModel: OxygenDetailViewModel by viewModel()

  private val phoneListAdapter by lazy {
    OxygenPhoneListAdapter(onItemClick = { item ->
      callPhone(item)
    })
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding.toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = ""

    setUpPhoneRecyclerView()

    viewModel.getOxygenService(id)

    viewModel.oxygenServiceLiveData.observe(this, Observer { observeOxygenDetailLiveData(it) })

  }

  private fun setUpPhoneRecyclerView() {
    binding.rvPhones.apply {
      isNestedScrollingEnabled = false
      adapter = phoneListAdapter
      layoutManager = LinearLayoutManager(this@OxygenDetailActivity, RecyclerView.VERTICAL, false)
    }
  }

  private fun observeOxygenDetailLiveData(viewState: AsyncViewState<OxygenDetailViewItem>) {

    when (viewState) {
      is AsyncViewState.Loading -> {
        //do nothing
      }

      is AsyncViewState.Error -> {
        showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Success -> {

        val data = viewState.value

       // supportActionBar?.title = data.name

        binding.apply {
          tvName.text = data.name
          tvFullAddress.text = data.fullAddress
          tvTownship.text = data.township
          tvStateOrRegion.text = data.stateRegion
        }

        phoneListAdapter.submitList(data.phones)
      }
    }
  }

  private fun callPhone(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
    startActivity(intent)
  }

  companion object {

    private const val EXTRA_SERVICE_ID = "id"


    fun newIntent(context: Context, id: ServiceId) =
      Intent(context, OxygenDetailActivity::class.java).apply {
        putExtras(
          bundleOf(
            EXTRA_SERVICE_ID to id.id
          )
        )
      }
  }
}