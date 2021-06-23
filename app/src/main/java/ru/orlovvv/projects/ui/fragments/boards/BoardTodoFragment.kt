package ru.orlovvv.projects.ui.fragments.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentBoardTodoBinding
import ru.orlovvv.projects.ui.ProjectsViewModel
import ru.orlovvv.projects.ui.fragments.boards.adapters.TaskAdapter
import ru.orlovvv.projects.util.setTaskActions

class BoardTodoFragment : Fragment(R.layout.fragment_board_todo) {

    private lateinit var binding: FragmentBoardTodoBinding
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private val taskAdapter = TaskAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBoardTodoBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@BoardTodoFragment
            viewModel = projectsViewModel
            rvTasks.adapter = taskAdapter
        }

        projectsViewModel.currentProjectTasksTodo.observe(viewLifecycleOwner, Observer {

        })

        taskAdapter.setOnPopupClickListener { popUp, task ->
            popUp.setTaskActions(projectsViewModel, task, popUp)
        }


    }
}
