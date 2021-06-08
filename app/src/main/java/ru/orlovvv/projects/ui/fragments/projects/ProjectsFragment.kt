package ru.orlovvv.projects.ui.fragments.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentProjectsBinding
import ru.orlovvv.projects.ui.dialogs.CreateProjectDialog

class ProjectsFragment : Fragment(R.layout.fragment_projects) {

    private lateinit var binding: FragmentProjectsBinding

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

        binding.fabCreateProject.setOnClickListener {
            openCreateProjectDialog()
        }
    }

    private fun openCreateProjectDialog() {
        val dialog = CreateProjectDialog()
        dialog.show(requireActivity().supportFragmentManager, "CreateProjectDialog")
    }
}