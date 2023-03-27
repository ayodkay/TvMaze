package com.oamaru.tvmaze.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.oamaru.tvmaze.MainActivityViewModel
import com.oamaru.tvmaze.NavGraphDirections
import com.oamaru.tvmaze.R
import com.oamaru.tvmaze.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = mainActivityViewModel

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id != R.id.ScheduleFragment) {
                binding.searchBar.visibility = View.GONE
            } else {
                binding.searchBar.visibility = View.VISIBLE
            }
        }

        binding.searchView
            .editText
            .setOnEditorActionListener { _, _, _ ->
                mainActivityViewModel.searchQuery.set(binding.searchView.text.toString())
                mainActivityViewModel.searchShow()
                false
            }

        mainActivityViewModel.clickEvent.observe(this) {
            navController.navigateUp()
            navController.navigate(
                NavGraphDirections.actionToShowDetails(
                    it.show.name,
                    it.show,
                    mainActivityViewModel.selectedShowSeasons.get() ?: arrayOf()
                )
            )
            binding.searchView.hide()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }
}
