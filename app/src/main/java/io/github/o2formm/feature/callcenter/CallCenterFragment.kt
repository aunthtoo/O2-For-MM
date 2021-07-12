package io.github.o2formm.feature.callcenter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.o2formm.android.extensions.layoutInflater
import io.github.o2formm.android.extensions.showShortToast
import io.github.o2formm.core.BaseFragment
import io.github.o2formm.databinding.DialogPhonesBinding
import io.github.o2formm.databinding.FragmentCallCenterBinding
import io.github.o2formm.helper.asyncviewstate.AsyncViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class CallCenterFragment : BaseFragment<FragmentCallCenterBinding>() {

  override val bindingInflater: (LayoutInflater) -> ViewBinding = FragmentCallCenterBinding::inflate

  private val callCenterListAdapter by lazy {
    CallCenterListAdapter(onItemClick = { item ->
      showPhoneDialog(item.name, item.phones)
    })
  }

  private val viewModel: CallCenterViewModel by viewModel()

  override fun onBindView() {
    super.onBindView()

    setUpRecyclerView()

    viewModel.getCallCenter()
    viewModel.callCenterLiveData.observe(viewLifecycleOwner, ::observeCallCenterLiveData)
  }

  private fun setUpRecyclerView() {
    binding.rvCallCenter.apply {
      adapter = callCenterListAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

  }

  private fun observeCallCenterLiveData(viewState: AsyncViewState<List<CallCenterViewItem>>) {
    when (viewState) {
      is AsyncViewState.Loading -> {
        //do nothing
      }

      is AsyncViewState.Error -> {
        requireContext().showShortToast(viewState.errorMessage)
      }

      is AsyncViewState.Success -> {
        callCenterListAdapter.submitList(viewState.value)
      }
    }
  }

  private fun showPhoneDialog(title: String, phones: List<String>) {
    val phoneDialog = AlertDialog.Builder(requireContext()).create()

    val phoneBinding = DialogPhonesBinding.inflate(requireContext().layoutInflater())

    phoneBinding.tvTitle.text = title

    val phoneAdapter = CallCenterPhoneListAdapter(onItemClick = { item ->
      callPhone(item)
    })

    phoneBinding.rvPhones.apply {
      adapter = phoneAdapter
      layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    phoneAdapter.submitList(phones)

    phoneDialog.setView(phoneBinding.root)
    phoneDialog.setCancelable(true)
    phoneDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
    phoneDialog.show()

  }

  private fun callPhone(phone: String) {
    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
    startActivity(intent)
  }

  companion object {

    fun newInstance() = CallCenterFragment()
  }
}