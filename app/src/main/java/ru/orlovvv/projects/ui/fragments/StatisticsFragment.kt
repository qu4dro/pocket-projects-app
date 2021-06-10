package ru.orlovvv.projects.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.FragmentStatisticsBinding
import ru.orlovvv.projects.ui.StatisticsViewModel

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private lateinit var binding: FragmentStatisticsBinding
    private val statisticsViewModel: StatisticsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentStatisticsBinding.inflate(layoutInflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@StatisticsFragment
            viewModel = statisticsViewModel
        }
    }
}