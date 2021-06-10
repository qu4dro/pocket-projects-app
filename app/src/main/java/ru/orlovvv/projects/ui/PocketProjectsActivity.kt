package ru.orlovvv.projects.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.orlovvv.projects.R
import ru.orlovvv.projects.databinding.ActivityPocketProjectsBinding

@AndroidEntryPoint
class PocketProjectsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPocketProjectsBinding
    private val projectsViewModel: ProjectsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPocketProjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNavigation()
    }

    private fun setNavigation() {
        binding.apply {
            topAppBar.setNavigationOnClickListener {
                drawerLayout.open()
            }
            val navController =
                Navigation.findNavController(this@PocketProjectsActivity, R.id.nav_host_fragment)
            navigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                with(topAppBar) {
                    title = when (destination.id) {
                        R.id.boardContainerFragment -> projectsViewModel.currentProjectName.value.toString()
                        R.id.statisticsFragment -> resources.getString(R.string.statistics)
                        R.id.trashFragment -> resources.getString(R.string.trash_bin)
                        R.id.projectsFragment -> resources.getString(R.string.projects)
                        else -> ""
                    }
                }
            }
        }

    }

}