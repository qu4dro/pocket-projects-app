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
import ru.orlovvv.projects.databinding.FragmentProjectsBinding
import ru.orlovvv.projects.ui.ProjectsViewModel
import ru.orlovvv.projects.ui.dialogs.CreateProjectDialog
import ru.orlovvv.projects.ui.fragments.boards.adapters.ProjectAdapter
import ru.orlovvv.projects.util.setProjectActions
import ru.orlovvv.projects.util.setStickersSpacing

class ProjectsFragment : Fragment(R.layout.fragment_projects) {

    private var _binding: FragmentProjectsBinding? = null
    val binding
        get() = _binding!!
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private val projectAdapter = ProjectAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProjectsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@ProjectsFragment
            viewModel = projectsViewModel
            rvProjects.apply {
                adapter = projectAdapter
                setStickersSpacing()
            }
            fabCreateProject.setOnClickListener {
                openCreateProjectDialog()
            }
        }
        projectAdapter.setOnItemClickListener {
            projectsViewModel.apply {
                currentProjectId.value = it.id
                currentProjectName.value = it.name
            }
            val extras = FragmentNavigatorExtras(binding.fabCreateProject to "fab_transition")
            findNavController().navigate(
                R.id.action_projectsFragment_to_boardContainerFragment,
                null,
                null,
                extras
            )
        }

        projectAdapter.setOnPopupClickListener { popUp, project ->
            popUp.setProjectActions(projectsViewModel, project, popUp)
        }
    }

    private fun openCreateProjectDialog() {
        val dialog = CreateProjectDialog()
        dialog.show(requireActivity().supportFragmentManager, "CreateProjectDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}