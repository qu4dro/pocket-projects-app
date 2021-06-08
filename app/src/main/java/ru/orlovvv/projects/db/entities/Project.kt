package ru.orlovvv.projects.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.orlovvv.projects.util.ProjectStatus

@Entity(tableName = "projects")
data class Project(
    val name: String,
    val description: String,
    val status: String = ProjectStatus.IN_PROGRESS.toString()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
