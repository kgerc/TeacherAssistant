package com.example.teacherassistant.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.ui.setupWithNavController
import com.example.teacherassistant.DialogManager
import com.example.teacherassistant.R
import com.example.teacherassistant.data.TeacherAssistantRepository
import com.example.teacherassistant.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import org.koin.android.ext.android.inject
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var floatingButton: FloatingActionButton
    private lateinit var navController: NavController

    private val dialogForFragment = mapOf(
        R.id.nav_students to { dialogManager.showAddStudentDialog() },
        R.id.nav_subjects to { dialogManager.showAddSubjectDialog() },
        R.id.nav_grades to { dialogManager.showAddGradeDialog() }
    )

    private val dialogManager: DialogManager by inject()
    private val repository: TeacherAssistantRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_main, null)

        Companion.fragmentManager = supportFragmentManager


        setContentView(view)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        floatingButton = findViewById(R.id.fab)

        floatingButton.setOnClickListener {
            showDialog()
        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        navController = findNavController(R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { controller, destination, _ ->
            if (destination.id == R.id.nav_summary) floatingButton.hide() else floatingButton.show()
            if (controller.currentDestination?.id == R.id.nav_grades) repository.clearGradeFilter()
        }

        loadKoinModules(
            module {
                single { DialogManager(supportFragmentManager, navController) }
            }
        )

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_subjects,
            R.id.nav_students,
            R.id.nav_grades,
            R.id.nav_summary
        ), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }*/

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun showDialog() {
        dialogForFragment[navController.currentDestination?.id]?.invoke()
    }

    companion object {
        private lateinit var fragmentManager: FragmentManager

        fun showDialog(dialog: DialogFragment) {
            dialog.show(fragmentManager, "AnotherDialog")
        }
    }
}