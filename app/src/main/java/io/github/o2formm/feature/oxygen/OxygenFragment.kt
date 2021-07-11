package io.github.o2formm.feature.oxygen

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOxygenBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenFragment : BaseFragment<FragmentOxygenBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentOxygenBinding::inflate


  override fun onBindView() {
    super.onBindView()
  }

  companion object {
    fun newInstance() = OxygenFragment()
  }
}