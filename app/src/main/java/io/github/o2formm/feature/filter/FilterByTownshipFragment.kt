package io.github.o2formm.feature.filter

import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.o2formm.android.extensions.hideKeyboard
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseDialogFragment
import io.github.o2formm.databinding.FragmentTownshipFilterBinding
import io.github.o2formm.domain.sheet.model.TownshipId
import io.github.o2formm.feature.main.SharedViewModel
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class FilterByTownshipFragment : BaseDialogFragment<FragmentTownshipFilterBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentTownshipFilterBinding::inflate

  private val viewModel: FilterByTownshipViewModel by viewModel()

  private val townshipFilterListAdapter by lazy {
    TownshipFilterListAdapter(onItemClick = { position, item ->
      viewModel.selectTownship(position, item)
      sharedViewModel.selectTownshipLiveData.postValue(item.id)
      hideKeyboard()
      dismiss()
    })
  }

  private val sharedViewModel: SharedViewModel by sharedViewModel()

  private val townshipId: TownshipId by lazy {
    TownshipId(requireArguments()?.getInt(ARG_TOWNSHIP_ID, -1)!!)
  }

  override fun onBindView() {
    super.onBindView()
    setUpRecyclerView()

    binding.btnClose.setOnClickListener {
      hideKeyboard()
      dismiss()
    }

    binding.edtSearchTownship.addTextChangedListener { text ->
      viewModel.searchTownship(text.toString())
    }

    viewModel.getAllTownship(townshipId)
    viewModel.townshipsLiveData.observe(viewLifecycleOwner, ::observeTownshipsLiveData)

    viewModel.indexToScrollLiveData.observe(viewLifecycleOwner, Observer { indexToScroll ->
      binding.rvTownships.scrollToPosition(indexToScroll)
    })
  }

  private fun setUpRecyclerView() {
    binding.rvTownships.apply {
      adapter = townshipFilterListAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }
  }

  private fun observeTownshipsLiveData(viewState: AsyncViewState<List<TownshipViewItem>>) {

    when (viewState) {
      is AsyncViewState.Loading -> {
        //do nothing
      }

      is AsyncViewState.Error -> {
        requireContext().showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Success -> {
        townshipFilterListAdapter.submitList(viewState.value)
        townshipFilterListAdapter.notifyDataSetChanged()
      }
    }
  }

  companion object {

    val TAG = FilterByTownshipFragment::class.java.name.toString()

    private const val ARG_TOWNSHIP_ID = "township_id"

    fun newInstance(townshipId: TownshipId) = FilterByTownshipFragment().apply {
      arguments = bundleOf(ARG_TOWNSHIP_ID to townshipId.id)
    }
  }
}