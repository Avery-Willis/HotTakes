package com.example.hottakesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

import com.example.hottakesapp.fragments.AddFragment
import com.example.hottakesapp.fragments.HomeFragment
import com.example.hottakesapp.fragments.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    lateinit var bottomNav : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val addFragment = AddFragment()
        val userFragment = UserFragment()
        setCurrentFragment(homeFragment)
        bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_home->{
                    setCurrentFragment(homeFragment)
                    true
                }
                R.id.nav_add-> {
                    setCurrentFragment(addFragment)
                    true
                }
                R.id.nav_profile->{
                    setCurrentFragment(userFragment)
                    true
                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}