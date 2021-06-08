package ru.orlovvv.projects.ui.fragments.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentBoardContainerBinding
import ru.orlovvv.projects.ui.fragments.boards.adapters.ScreenSlidePagerAdapter

class BoardContainerFragment : Fragment(R.layout.fragment_board_container) {

    private lateinit var binding: FragmentBoardContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBoardContainerBinding.inflate(layoutInflater)

        binding.apply {
            pager.adapter =
                ScreenSlidePagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        }

        setPagerTabs()

        return binding.root
    }

    private fun setPagerTabs() {

        binding.apply {
            tabLayoutTop.addTab(tabLayoutTop.newTab().setText(R.string.todo))
            tabLayoutTop.addTab(tabLayoutTop.newTab().setText(R.string.doing))
            tabLayoutTop.addTab(tabLayoutTop.newTab().setText(R.string.done))
        }

        binding.tabLayoutTop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.apply {
                    tabLayoutTop.selectTab(tabLayoutTop.getTabAt(position))
                }
            }
        })
    }


}