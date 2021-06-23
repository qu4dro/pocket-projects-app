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

    private var _binding: ActivityPocketProjectsBinding? = null
    val binding
        get() = _binding!!
    private val projectsViewModel: ProjectsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityPocketProjectsBinding.inflate(layoutInflater)
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
//            navigationView.setBackgroundColor(ResourcesCompat.getDrawable(resources.getDrawable(R.drawable.drawer_header_background))


            navController.addOnDestinationChangedListener { _, destination, _ ->
                with(topAppBar) {
                    title = when (destination.id) {
                        R.id.boardContainerFragment -> projectsViewModel.currentProjectName.value.toString()
                        R.id.statisticsFragment -> resources.getString(R.string.statistics)
                        R.id.archiveFragment -> resources.getString(R.string.archive)
                        R.id.trashFragment -> resources.getString(R.string.trash_bin)
                        R.id.projectsFragment -> resources.getString(R.string.projects)
                        else -> ""
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}