package ru.orlovvv.projects.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentTrashBinding
import ru.orlovvv.projects.ui.ProjectsViewModel
import ru.orlovvv.projects.ui.fragments.boards.adapters.ProjectAdapter
import ru.orlovvv.projects.util.setStickersSpacing

class TrashFragment : Fragment(R.layout.fragment_trash) {

    private lateinit var binding: FragmentTrashBinding
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private var projectAdapter = ProjectAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTrashBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@TrashFragment
            viewModel = projectsViewModel
            rvProjects.apply {
                adapter = projectAdapter
                setStickersSpacing()
            }
        }
        projectAdapter.setOnItemClickListener {
            projectsViewModel.apply {
                currentProjectId.value = it.id
                currentProjectName.value = it.name
            }

        }
    }
}