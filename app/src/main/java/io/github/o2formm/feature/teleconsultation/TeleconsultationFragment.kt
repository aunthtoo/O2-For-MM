package io.github.o2formm.feature.teleconsultation

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.FragmentTeleconsultationBinding

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class TeleconsultationFragment : BaseFragment<FragmentTeleconsultationBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding =
    FragmentTeleconsultationBinding::inflate

  override fun onBindView() {
    super.onBindView()
  }

  companion object {
    fun newInstance() = TeleconsultationFragment()
  }
}