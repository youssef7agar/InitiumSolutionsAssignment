package com.example.initiumsolutionsassignment.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.auth.UserStatus
import com.example.initiumsolutionsassignment.auth.UserStatusChange
import com.example.initiumsolutionsassignment.auth.login.LogInFragment
import com.example.initiumsolutionsassignment.auth.register.RegisterFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), UserStatusChange, MainFragment.UserStatusListener {

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

        viewModel.getCachedUser()?.let {
            hideItem(R.id.nv_login)
            hideItem(R.id.nv_register)

        } ?: kotlin.run {
            hideItem(R.id.nv_logout)
            showItem(R.id.nv_login)
            showItem(R.id.nv_register)
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

                fragment = MainFragment.newInstance(viewModel.getCachedUser())
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
                onStatusChange(UserStatus.LoggedOut)
                fragment = null
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

    private fun showItem(itemId: Int) {
        val navMenu: Menu = navigation_menu.menu
        navMenu.findItem(itemId).isVisible = true
    }

    override fun onStatusChange(status: UserStatus) {
        statusChangeLiveData.value = status
        navigation_menu.menu.clear()
        navigation_menu.inflateMenu(R.menu.navigation_menu)
        when (status) {
            is UserStatus.LoggedIn -> {
                hideItem(R.id.nv_login)
                hideItem(R.id.nv_register)
                showItem(R.id.nv_logout)
                tv_menu_header.text = "Welcome, \n ${status.firstName} ${status.lastName}"
            }
            UserStatus.LoggedOut -> {
                showItem(R.id.nv_login)
                showItem(R.id.nv_register)
                hideItem(R.id.nv_logout)
                tv_menu_header.text = null
            }
        }
    }

    private val statusChangeLiveData = MutableLiveData<UserStatus>()
    override fun statusChange(): LiveData<UserStatus> {
        return statusChangeLiveData
    }
}