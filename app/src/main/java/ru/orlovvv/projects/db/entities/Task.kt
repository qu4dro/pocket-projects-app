package ru.orlovvv.projects.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "tasks")
data class Task(
    var description: String,
    var status: String,
    val projectId: Long,
    var isChecked: Boolean = false,
    var isImportant: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
    var dateCreated: Long = System.currentTimeMillis()
    val dateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(dateCreated)
}