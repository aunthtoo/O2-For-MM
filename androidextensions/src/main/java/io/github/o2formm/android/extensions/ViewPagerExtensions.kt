package io.github.o2formm.android.extensions

import androidx.viewpager2.widget.ViewPager2

fun ViewPager2.addOnPageChangeCallback(
  onPageScrollStateChanged: ((state: Int) -> Unit) = { _ -> },
  onPageScrolled: ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit) = { _, _, _ -> },
  onPageSelected: ((position: (Int)) -> Unit) = { _ -> }
): ViewPager2.OnPageChangeCallback {
  return object : ViewPager2.OnPageChangeCallback() {
    override fun onPageScrollStateChanged(state: Int) {
      super.onPageScrollStateChanged(state)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
      super.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }

    override fun onPageSelected(pos: Int) {
      super.onPageSelected(pos)
      onPageSelected.invoke(pos)
    }
  }.also {
    registerOnPageChangeCallback(it)
  }

}