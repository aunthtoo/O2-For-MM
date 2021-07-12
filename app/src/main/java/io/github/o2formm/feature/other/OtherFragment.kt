package io.github.o2formm.feature.other

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOtherBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OtherFragment : BaseFragment<FragmentOtherBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentOtherBinding::inflate

  override fun onBindView() {
    super.onBindView()
  }

  companion object {

    fun newInstance() = OtherFragment()
  }
}