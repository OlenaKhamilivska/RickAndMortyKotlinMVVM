package com.example.fff

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fff.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.crashlytics.FirebaseCrashlytics

class NavGraphActivity: AppCompatActivity() {

//    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_graph)

//        FirebaseCrashlytics.getInstance().setUserId("12345")

//        val dateFormat = DateFormat.getDateFormat(
//            applicationContext
//        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.characterListFragment,
                R.id.characterSearchFragment,
                R.id.episodeListFragment
            ),
            drawerLayout = drawerLayout
        )
        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )

        findViewById<NavigationView>(R.id.nav_view).apply {
            setupWithNavController(navController)
            setCheckedItem(navController.graph.getStartDestination())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}