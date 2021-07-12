package io.github.o2formm.feature.oxygen

import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.o2formm.R
import io.github.o2formm.android.extensions.setVisible
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOxygenBinding
import io.github.o2formm.domain.sheet.model.TownshipId
import io.github.o2formm.feature.main.SharedViewModel
import io.github.o2formm.feature.oxygen.detail.OxygenDetailActivity
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
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

    binding.edtText.addTextChangedListener { text ->
      viewModel.search(text.toString())
    }

    binding.ivClearFilter.setOnClickListener {
      sharedViewModel.selectTownshipLiveData.postValue(TownshipId(-1))
    }

    viewModel.getAllOxygenServices()

    viewModel.oxygenServiceLiveData.observe(viewLifecycleOwner, ::observeOxygenServiceLiveData)

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

        binding.tvNoContent.setVisible(viewState.value.isEmpty())

        oxygenListAdapter.submitList(viewState.value)
        oxygenListAdapter.notifyDataSetChanged()
      }
    }
  }

  companion object {
    fun newInstance() = OxygenFragment()
  }
}