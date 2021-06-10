package ru.orlovvv.projects.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.orlovvv.projects.databinding.DialogCreateProjectBinding
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.ui.ProjectsViewModel

class CreateProjectDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCreateProjectBinding
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private lateinit var bottomDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DialogCreateProjectBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnCreateProject.setOnClickListener {
                createProject()
                bottomDialog.dismiss()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomDialog = super.onCreateDialog(savedInstanceState)
        bottomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return bottomDialog
    }

    private fun createProject() {
        projectsViewModel.apply {
            createProject(
                Project(
                    binding.etProjectName.text.toString(),
                    binding.etProjectDescription.text.toString()
                )
            )
        }
    }
}