package ru.orlovvv.projects.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentArchiveBinding

class ArchiveFragment : Fragment(R.layout.fragment_archive) {

    private lateinit var binding: FragmentArchiveBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentArchiveBinding.inflate(layoutInflater)

        return binding.root
    }
}