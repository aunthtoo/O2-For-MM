package io.github.o2formm.feature.callcenter

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentCallCenterBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class CallCenterFragment : BaseFragment<FragmentCallCenterBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentCallCenterBinding::inflate

  override fun onBindView() {
    super.onBindView()
  }

  companion object {

    fun newInstance() = CallCenterFragment()
  }
}