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
import ru.orlovvv.projects.databinding.FragmentArchiveBinding
import ru.orlovvv.projects.ui.ProjectsViewModel
import ru.orlovvv.projects.ui.fragments.boards.adapters.ProjectAdapter
import ru.orlovvv.projects.util.setProjectActions
import ru.orlovvv.projects.util.setStickersSpacing

class ArchiveFragment : Fragment(R.layout.fragment_archive) {

    private var _binding: FragmentArchiveBinding? = null
    val binding
        get() = _binding!!
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private val projectAdapter = ProjectAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArchiveBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@ArchiveFragment
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

            findNavController().navigate(R.id.action_archiveFragment_to_boardContainerFragment)
        }
        projectAdapter.setOnPopupClickListener { popUp, project ->
            popUp.setProjectActions(projectsViewModel, project, popUp)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}