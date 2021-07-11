package io.github.o2formm.feature.office

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOfficeBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OfficeFragment : BaseFragment<FragmentOfficeBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentOfficeBinding::inflate

  override fun onBindView() {
    super.onBindView()
  }

  companion object {
    fun newInstance() = OfficeFragment()
  }
}