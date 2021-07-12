package io.github.o2formm.feature.oxygenplant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.o2formm.R
import io.github.o2formm.android.extensions.setVisible
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOxygenPlantBinding
import io.github.o2formm.domain.sheet.model.TownshipId
import io.github.o2formm.feature.main.SharedViewModel
import io.github.o2formm.feature.oxygen.OxygenListAdapter
import io.github.o2formm.feature.oxygen.OxygenViewItem
import io.github.o2formm.feature.oxygen.OxygenViewModel
import io.github.o2formm.feature.oxygen.detail.OxygenDetailActivity
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenPlantFragment : BaseFragment<FragmentOxygenPlantBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentOxygenPlantBinding::inflate

  private val viewModel: OxygenPlantViewModel by viewModel()

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

  private val sharedViewModel: SharedViewModel by sharedViewModel()

  override fun onBindView() {
    super.onBindView()

    setUpRecyclerView()
    viewModel.oxygenServiceLiveData.observe(viewLifecycleOwner, this::observeOxygenServiceLiveData)

    binding.edtText.addTextChangedListener { text ->
      viewModel.search(text.toString())
    }

    //clear filter
    binding.ivClearFilter.setOnClickListener {
      sharedViewModel.selectTownshipLiveData.postValue(TownshipId(-1))
    }

    //observe township filter
    sharedViewModel.selectTownshipLiveData.observe(viewLifecycleOwner, Observer { townshipId ->
      viewModel.filterWithTownshipId(townshipId)
    })

    viewModel.oxygenServiceFilterByTownshipLiveData.observe(
      viewLifecycleOwner,
      Observer { viewState ->
        if (viewState is AsyncViewState.Success) {
          val townshipName = viewState.value
          if (townshipName.isEmpty()) {
            binding.filterGroup.setVisible(false)
          } else {
            binding.filterGroup.setVisible(true)
            binding.tvFilteredBy.text = getString(R.string.filtered_by, townshipName)
          }
        }
      })

  }

  companion object {

    fun newInstance() = OxygenPlantFragment()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel.getAllOxygenServices()
  }

  private fun setUpRecyclerView() {
    binding.rvOxygenServices.apply {
      adapter = oxygenListAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }
  }

  @SuppressLint("NotifyDataSetChanged")
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

        binding.tvNoContent.setVisible(viewState.value.isEmpty())

        oxygenListAdapter.submitList(viewState.value)
        oxygenListAdapter.notifyDataSetChanged()
      }
    }
  }
}