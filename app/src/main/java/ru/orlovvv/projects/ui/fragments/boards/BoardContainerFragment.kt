package ru.orlovvv.projects.ui.fragments.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentBoardContainerBinding
import ru.orlovvv.projects.ui.PocketProjectsViewModel
import ru.orlovvv.projects.ui.dialogs.CreateTaskDialog
import ru.orlovvv.projects.ui.fragments.boards.adapters.ScreenSlidePagerAdapter
import ru.orlovvv.projects.util.Constants
import ru.orlovvv.projects.util.Constants.NUM_PAGES
import ru.orlovvv.projects.util.TaskStatus
import timber.log.Timber

class BoardContainerFragment : Fragment(R.layout.fragment_board_container) {

    private lateinit var binding: FragmentBoardContainerBinding
    private val pocketProjectsViewModel: PocketProjectsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBoardContainerBinding.inflate(layoutInflater)

        binding.apply {
            pager.offscreenPageLimit = NUM_PAGES as Int
            pager.adapter =
                ScreenSlidePagerAdapter(childFragmentManager, lifecycle)
            fabCreateTask.setOnClickListener {
                openCreateTaskDialog()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPagerTabs()

        pocketProjectsViewModel.currentProjectId.observe(viewLifecycleOwner, Observer {
            pocketProjectsViewModel.getTasks(pocketProjectsViewModel.currentProjectId.value!!)
        })

    }


    private fun openCreateTaskDialog() {
        val dialog = CreateTaskDialog()
        dialog.show(requireActivity().supportFragmentManager, "CreateTaskDialog")
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
                pocketProjectsViewModel.currentPagerTaskStatus.value =
                    getStatusFromPosition(tab.position)
                Timber.d(pocketProjectsViewModel.currentPagerTaskStatus.value.toString())
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

    private fun getStatusFromPosition(position: Int): TaskStatus {
        return when (position) {
            1 -> TaskStatus.DOING
            2 -> TaskStatus.DONE
            else -> TaskStatus.TODO
        }
    }

}