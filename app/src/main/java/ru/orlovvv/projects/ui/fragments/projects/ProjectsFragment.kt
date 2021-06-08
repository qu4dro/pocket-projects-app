package ru.orlovvv.projects.ui.fragments.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentProjectsBinding
import ru.orlovvv.projects.ui.PocketProjectsViewModel
import ru.orlovvv.projects.ui.dialogs.CreateProjectDialog
import ru.orlovvv.projects.ui.fragments.boards.adapters.ProjectAdapter
import ru.orlovvv.projects.util.setStickersSpacing

class ProjectsFragment : Fragment(R.layout.fragment_projects) {

    private lateinit var binding: FragmentProjectsBinding
    private val pocketProjectsViewModel: PocketProjectsViewModel by activityViewModels()
    private var projectAdapter = ProjectAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProjectsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@ProjectsFragment
            viewModel = pocketProjectsViewModel
            rvProjects.apply {
                adapter = projectAdapter
                setStickersSpacing()
            }
            fabCreateProject.setOnClickListener {
                openCreateProjectDialog()
            }
        }
        projectAdapter.setOnItemClickListener {
            pocketProjectsViewModel.currentProjectId.value = it.id
            findNavController().navigate(R.id.action_projectsFragment_to_boardContainerFragment)
        }
    }

    private fun openCreateProjectDialog() {
        val dialog = CreateProjectDialog()
        dialog.show(requireActivity().supportFragmentManager, "CreateProjectDialog")
    }
}