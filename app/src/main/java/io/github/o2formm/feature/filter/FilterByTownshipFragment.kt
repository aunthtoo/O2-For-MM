package io.github.o2formm.feature.filter

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseDialogFragment
import io.github.o2formm.databinding.FragmentTownshipFilterBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
class FilterByTownshipFragment : BaseDialogFragment<FragmentTownshipFilterBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentTownshipFilterBinding::inflate

  private val viewModel: FilterByTownshipViewModel by viewModel()

  override fun onBindView() {
    super.onBindView()
  }

  companion object {

    val TAG = FilterByTownshipFragment::class.java.name.toString()

    fun newInstance() = FilterByTownshipFragment()
  }
}