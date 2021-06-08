package ru.orlovvv.projects.ui.fragments.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentBoardContainerBinding

class BoardContainerFragment : Fragment(R.layout.fragment_board_container) {

    private lateinit var binding: FragmentBoardContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBoardContainerBinding.inflate(layoutInflater)

        return binding.root
    }
}