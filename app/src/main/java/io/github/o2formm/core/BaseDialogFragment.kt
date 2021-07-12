package io.github.o2formm.core

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

/**
Created By Aunt Htoo Aung on 12/07/2021.
 **/
abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

  private var _binding: ViewBinding? = null

  @Suppress("UNCHECKED_CAST")
  protected val binding
    get() = _binding!! as VB

  protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

  override fun onStart() {
    super.onStart()
    dialog?.window?.apply {
      setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    _binding = bindingInflater(inflater)

    dialog?.window?.apply {
      requestFeature(Window.FEATURE_NO_TITLE)
      setBackgroundDrawableResource(android.R.color.transparent)
    }
    onBindView()
    return binding.root
  }

  //Function to safely call after on create and before onViewCreated
  protected open fun onBindView() {
    //Do Nothing
  }

  override fun onDestroyView() {
    _binding = null
    super.onDestroyView()
  }
}