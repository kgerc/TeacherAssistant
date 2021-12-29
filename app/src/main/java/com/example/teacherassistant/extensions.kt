package com.example.teacherassistant

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.ModuleDeclaration
import org.koin.dsl.module


fun Application.addModules(moduleDeclaration: ModuleDeclaration) {
    val self = this

    val addedModules = module(moduleDeclaration = moduleDeclaration)

    org.koin.core.context.startKoin {
        androidLogger()
        androidContext(self)
        androidFileProperties()
        modules(addedModules)
    }
}
inline fun<reified T> Application.buildDatabase(name: String): T where T: RoomDatabase {
    return Room.databaseBuilder(
        applicationContext,
        T::class.java, name
    ).fallbackToDestructiveMigration()
        .build()
}