package ru.orlovvv.projects.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.orlovvv.projects.databinding.DialogCreateTaskBinding
import ru.orlovvv.projects.db.entities.Task
import ru.orlovvv.projects.ui.ProjectsViewModel

class CreateTaskDialog : BottomSheetDialogFragment() {

    private var _binding: DialogCreateTaskBinding? = null
    val binding
        get() = _binding!!
    private val projectsViewModel: ProjectsViewModel by activityViewModels()
    private lateinit var bottomDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DialogCreateTaskBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnCreateTask.setOnClickListener {
                createTask()
                bottomDialog.dismiss()
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomDialog = super.onCreateDialog(savedInstanceState)
        bottomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return bottomDialog
    }

    private fun createTask() {
        projectsViewModel.apply {
            createTask(
                Task(
                    binding.etTaskDescription.text.toString(),
                    currentPagerTaskStatus.value.toString(),
                    currentProjectId.value!!,
                    false,
                    binding.switchImportant.isChecked
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}