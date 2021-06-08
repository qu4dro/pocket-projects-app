package ru.orlovvv.projects.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.orlovvv.projects.databinding.DialogCreateProjectBinding
import ru.orlovvv.projects.db.entities.Project
import ru.orlovvv.projects.ui.PocketProjectsViewModel

class CreateProjectDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogCreateProjectBinding
    private val pocketProjectsViewModel: PocketProjectsViewModel by activityViewModels()
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
            btnCreateExercise.setOnClickListener {
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
        pocketProjectsViewModel.createProject(
            Project(
                "Тестовый проект",
                "Какое-то краткое описание для проекта"
            )
        )
    }
}