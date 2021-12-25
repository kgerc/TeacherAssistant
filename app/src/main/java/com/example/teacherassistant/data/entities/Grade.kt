package com.example.teacherassistant.data.entities

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Entity(
    tableName = "grades",
    foreignKeys = [ForeignKey(
        entity = StudentSubjectRelation::class,
        parentColumns = ["id"],
        childColumns = ["student_subject_id"],
        onDelete = ForeignKey.CASCADE)]
)
data class Grade constructor(

    @ColumnInfo(name = "student_subject_id")
    val studentSubjectId: Long,
    val value: Float,
    val note: String,
    val status: Status,
    @ColumnInfo(name = "last_modification_time")
    val lastModifitation: LocalDateTime = LocalDateTime.now()
) {
    enum class Status(val value: Int) {
        ADDED(0),
        EDITED(1),
        DEPRECATED(2),
        DELETED(3)
    }

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}