package io.github.o2formm.feature.filter

import android.view.LayoutInflater
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.o2formm.android.extensions.hideKeyboard
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseDialogFragment
import io.github.o2formm.databinding.FragmentTownshipFilterBinding
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
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
    })
  }


  override fun onBindView() {
    super.onBindView()
    setUpRecyclerView()

    binding.btnClose.setOnClickListener {
      dismiss()
      hideKeyboard()
    }

    binding.edtSearchTownship.addTextChangedListener { text ->
      viewModel.searchTownship(text.toString())
    }

    viewModel.getAllTownship()
    viewModel.townshipsLiveData.observe(viewLifecycleOwner, ::observeTownshipsLiveData)
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

    fun newInstance() = FilterByTownshipFragment()
  }
}