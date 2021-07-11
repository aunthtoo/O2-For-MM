package io.github.o2formm.feature.oxygen

import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.o2formm.android.extensions.setVisible
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOxygenBinding
import io.github.o2formm.feature.oxygen.detail.OxygenDetailActivity
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenFragment : BaseFragment<FragmentOxygenBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentOxygenBinding::inflate

  private val viewModel: OxygenViewModel by viewModel()

  private val oxygenListAdapter by lazy {
    OxygenListAdapter(onItemClick = { item ->

      Timber.e("${item.nameMm}")

      startActivity(
        OxygenDetailActivity.newIntent(
          requireContext(),
          item.serviceId
        )
      )
    })
  }

  override fun onBindView() {
    super.onBindView()

    setUpRecyclerView()

    binding.edtText.addTextChangedListener { text ->
      viewModel.search(text.toString())
    }

    viewModel.getAllOxygenServices()

    viewModel.oxygenServiceLiveData.observe(viewLifecycleOwner, ::observeOxygenServiceLiveData)
  }

  private fun setUpRecyclerView() {
    binding.rvOxygenServices.apply {
      adapter = oxygenListAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }
  }

  private fun observeOxygenServiceLiveData(viewState: AsyncViewState<List<OxygenViewItem>>) {
    when (viewState) {
      is AsyncViewState.Loading -> {
        binding.progressBar.setVisible(true)
        binding.rvOxygenServices.setVisible(false)
      }

      is AsyncViewState.Error -> {
        requireContext().showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Success -> {
        binding.progressBar.setVisible(false)
        binding.rvOxygenServices.setVisible(true)
        oxygenListAdapter.submitList(viewState.value)
      }
    }
  }

  companion object {
    fun newInstance() = OxygenFragment()
  }
}