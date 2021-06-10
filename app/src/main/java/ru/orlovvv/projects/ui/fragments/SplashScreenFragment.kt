package ru.orlovvv.projects.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentSplashScreenBinding
import ru.orlovvv.projects.ui.ProjectsViewModel

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private lateinit var binding: FragmentSplashScreenBinding
    private val projectsViewModel: ProjectsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashScreenBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        projectsViewModel.inProgressProjects.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_splashScreenFragment_to_projectsFragment)
        })


    }
}