package io.github.o2formm.feature.main

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.github.o2formm.domain.sheet.model.ServiceTypeConstants
import io.github.o2formm.feature.callcenter.CallCenterFragment
import io.github.o2formm.feature.office.OfficeFragment
import io.github.o2formm.feature.other.OtherFragment
import io.github.o2formm.feature.oxygen.OxygenFragment
import io.github.o2formm.feature.oxygenplant.OxygenPlantFragment
import io.github.o2formm.feature.teleconsultation.TeleconsultationFragment

/**
Created By Aunt Htoo Aung on 11/07/2021.
 **/
class ServicesPagerAdapter constructor(fragmentActivity: FragmentActivity) :
  FragmentStateAdapter(fragmentActivity) {

  private var list = mutableListOf<ServiceTypeViewItem>()

  override fun getItemCount(): Int = list.size

  override fun createFragment(position: Int): Fragment {

    val service = list[position].type.trim()
    return OxygenFragment.newInstance(serviceType = service)
    /*when {
        service.equals(ServiceTypeConstants.OXYGEN, ignoreCase = true) -> {
          OxygenFragment.newInstance()
        }
        service.equals(ServiceTypeConstants.CALL_CENTER, ignoreCase = true) -> {
          CallCenterFragment.newInstance()
        }

        service.equals(ServiceTypeConstants.TELECONSULTATION, ignoreCase = true) -> {
          TeleconsultationFragment.newInstance()
        }
        service.equals(ServiceTypeConstants.OFFICE, ignoreCase = true) -> {
          OfficeFragment.newInstance()
        }
        service.equals(ServiceTypeConstants.OXYGEN_PLANT, ignoreCase = true) -> {
          OxygenPlantFragment.newInstance()
        }
        service.equals(ServiceTypeConstants.OTHER, ignoreCase = true) -> {
          OtherFragment.newInstance()
        }
        else -> {
          OxygenFragment.newInstance()
        }*/
    //}
  }

  @SuppressLint("NotifyDataSetChanged")
  fun submitList(list: List<ServiceTypeViewItem>) {
    this.list.clear()
    this.list.addAll(list.toMutableList())
    notifyDataSetChanged()
  }

}