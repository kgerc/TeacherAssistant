package com.example.teacherassistant.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.TypeConverter
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teacherassistant.data.daos.GradeDao
import com.example.teacherassistant.data.daos.StudentDao
import com.example.teacherassistant.data.daos.StudentSubjectRelationDao
import com.example.teacherassistant.data.daos.SubjectDao
import com.example.teacherassistant.data.entities.Grade
import com.example.teacherassistant.data.entities.Student
import com.example.teacherassistant.data.entities.StudentSubjectRelation
import com.example.teacherassistant.data.entities.Subject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Database(entities = [Student::class, Subject::class, StudentSubjectRelation::class, Grade::class], version = 7)
@TypeConverters(Converters::class)
abstract class TeacherAssistantDatabase : RoomDatabase() {
    abstract val students: StudentDao
    abstract val subjects: SubjectDao
    abstract val studentSubjectRels: StudentSubjectRelationDao
    abstract val grades: GradeDao
}

class Converters {
    @TypeConverter
    fun toGradeStatus(value: Int): Grade.Status = enumValues<Grade.Status>()[value]
    @TypeConverter
    fun fromGradeStatus(status: Grade.Status): Int = status.ordinal
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(dateTime: String): LocalDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String = dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
}