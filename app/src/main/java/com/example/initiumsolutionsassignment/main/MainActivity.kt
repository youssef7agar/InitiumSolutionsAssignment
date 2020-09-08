package com.example.initiumsolutionsassignment.main

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.auth.login.LogInFragment
import com.example.initiumsolutionsassignment.auth.register.RegisterFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClicks()

        showFragment(MainFragment.newInstance(viewModel.getCachedUser()), "main")

        navigation_menu.setNavigationItemSelectedListener {
            navigate(it.itemId)
            return@setNavigationItemSelectedListener true
        }

        val user = viewModel.getCachedUser()
        if (user != null) {
            hideItem(R.id.nv_login)
            hideItem(R.id.nv_register)
//            tv_menu_header.text = "Welcome, \n ${user.customerFirstName} ${user.customerLastName}"
        } else {
            hideItem(R.id.nv_logout)
        }
    }

    private fun btnClicks() {
        img_menu.setOnClickListener {
            menu_drawer.openDrawer(GravityCompat.START)

        }
    }

    override fun onBackPressed() {
        if (menu_drawer.isDrawerOpen(GravityCompat.START)) {

            menu_drawer.closeDrawers()
            return
        }
        when (currentFragment) {
            "main" -> {
                super.onBackPressed()
            }
            else -> {
                navigate(R.id.nv_main)
            }
        }
    }

    private var currentFragment = ""
    fun showFragment(fragment: Fragment, tag: String) {
        if (getFragmentByTag(currentFragment) != null) {
            supportFragmentManager.beginTransaction().hide(getFragmentByTag(currentFragment)!!)
                .commit()
        }
        currentFragment = tag
        if (getFragmentByTag(tag) != null) {
            //if the fragment exists, show it.
            supportFragmentManager.beginTransaction().show(getFragmentByTag(tag)!!)
                .commit()
        } else {
            //if the fragment does not exist, add it to fragment manager.
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment, tag).commit()
        }
    }

    private fun getFragmentByTag(tag: String?): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    fun navigate(itemId: Int) {
        var fragment: Fragment? = null
        var tag: String? = null
        when (itemId) {
            R.id.nv_main -> {

                fragment = MainFragment()
                tag = "main"
                menu_drawer.closeDrawers()
            }
            R.id.nv_login -> {

                fragment = LogInFragment()
                tag = "login"
                menu_drawer.closeDrawers()
            }
            R.id.nv_register -> {

                fragment = RegisterFragment()
                tag = "register"
                menu_drawer.closeDrawers()
            }
            R.id.nv_logout -> {
                viewModel.logOut()
                menu_drawer.closeDrawers()
                fragment = LogInFragment()
                tag = "login"
            }
        }
        fragment?.let { fr ->
            menu_drawer.closeDrawers()
            showFragment(fr, tag ?: "")
        }
    }
    private fun hideItem(itemId: Int) {
        val navMenu: Menu = navigation_menu.menu
        navMenu.findItem(itemId).isVisible = false
    }
}