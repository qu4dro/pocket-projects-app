package ru.orlovvv.projects.ui.fragments.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentBoardDoneBinding
import ru.orlovvv.projects.ui.PocketProjectsViewModel
import ru.orlovvv.projects.ui.fragments.boards.adapters.TaskAdapter
import ru.orlovvv.projects.util.setActions

class BoardDoneFragment : Fragment(R.layout.fragment_board_done) {

    private lateinit var binding: FragmentBoardDoneBinding
    private val pocketProjectsViewModel: PocketProjectsViewModel by activityViewModels()
    private val taskAdapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBoardDoneBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@BoardDoneFragment
            viewModel = pocketProjectsViewModel
            rvTasks.adapter = taskAdapter
        }

        taskAdapter.setOnPopupClickListener { popUp, task ->
            popUp.setActions(pocketProjectsViewModel, task, popUp)
        }

    }
}