package com.example.teacherassistant.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "student_to_subject_rel",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["student_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subject_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
class StudentSubjectRelation(
    @ColumnInfo(name = "student_id")
    val studentId: Long,
    @ColumnInfo(name = "subject_id")
    val subjectId: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}