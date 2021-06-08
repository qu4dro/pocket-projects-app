package ru.orlovvv.projects.ui.fragments.boards.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.orlovvv.projects.ui.fragments.boards.BoardDoingFragment
import ru.orlovvv.projects.ui.fragments.boards.BoardDoneFragment
import ru.orlovvv.projects.ui.fragments.boards.BoardTodoFragment
import ru.orlovvv.projects.util.Constants.NUM_PAGES

class ScreenSlidePagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> BoardDoingFragment()
            2 -> BoardDoneFragment()
            else -> BoardTodoFragment()
        }
    }

}