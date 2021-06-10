package ru.orlovvv.projects.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.orlovvv.projects.util.ProjectStatus
import java.text.DateFormat

@Entity(tableName = "projects")
data class Project(
    val name: String,
    val description: String,
    val status: String = ProjectStatus.IN_PROGRESS.toString()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var dateCreated: Long = System.currentTimeMillis()
    val dateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(dateCreated)
}
