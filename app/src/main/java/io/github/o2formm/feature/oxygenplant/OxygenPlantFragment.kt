package io.github.o2formm.feature.oxygenplant

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentOxygenPlantBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class OxygenPlantFragment : BaseFragment<FragmentOxygenPlantBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentOxygenPlantBinding::inflate

  override fun onBindView() {
    super.onBindView()
  }

  companion object {

    fun newInstance() = OxygenPlantFragment()
  }
}