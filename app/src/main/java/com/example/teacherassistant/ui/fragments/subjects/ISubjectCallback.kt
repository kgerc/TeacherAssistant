package com.example.teacherassistant.ui.fragments.subjects

import com.example.teacherassistant.data.entities.Subject

interface ISubjectCallback {
    fun deleteSubject(subject: Subject)
    fun editSubject(subject: Subject)
    fun showGradesForSubject(subject: Subject)
}