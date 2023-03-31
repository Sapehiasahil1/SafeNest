package com.example.safenest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_bar)

        bottomBar.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.nav_guard -> {
                    inflateFragment(GuardFragment.newInstance())
                }
                R.id.nav_home -> {
                    inflateHomeFragment(HomeFragment.newInstance())
                }
                R.id.nav_dashboard -> {
                    inflateDashboardFragment(DashboardFragment.newInstance())
                }
                R.id.nav_profile -> {
                    inflateProfileFragment(ProfileFragment.newInstance())
                }
            }

            bottomBar.selectedItemId = R.id.nav_home

            true
        }
    }

    private fun inflateProfileFragment(newInstance: ProfileFragment) {
        val trancsaction = supportFragmentManager.beginTransaction()
        trancsaction.replace(R.id.container, newInstance)
        trancsaction.commit()
    }

    private fun inflateDashboardFragment(newInstance: DashboardFragment) {
        val trancsaction = supportFragmentManager.beginTransaction()
        trancsaction.replace(R.id.container, newInstance)
        trancsaction.commit()
    }

    private fun inflateHomeFragment(newInstance: HomeFragment) {
        val trancsaction = supportFragmentManager.beginTransaction()
        trancsaction.replace(R.id.container, newInstance)
        trancsaction.commit()
    }

    private fun inflateFragment(newInstance: GuardFragment) {

        val trancsaction = supportFragmentManager.beginTransaction()
        trancsaction.replace(R.id.container, newInstance)
        trancsaction.commit()
    }
}