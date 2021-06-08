package ru.orlovvv.projects.ui.fragments.projects

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentProjectsBinding

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

        findNavController().navigate(R.id.action_projectsFragment_to_boardContainerFragment)
    }
}