package com.example.initiumsolutionsassignment.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.login.LogInFragment
import com.example.initiumsolutionsassignment.register.RegisterFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClicks()

        showFragment(MainFragment(), "main")

        navigation_menu.setNavigationItemSelectedListener {
            navigate(it.itemId)
            return@setNavigationItemSelectedListener true
        }
    }

    private fun btnClicks(){
        img_menu.setOnClickListener {
            menu_drawer.openDrawer(GravityCompat.START)
            hideBar()
        }
    }

    override fun onBackPressed() {
        if (menu_drawer.isDrawerOpen(GravityCompat.START)){
            showBar()
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
    private fun showFragment(fragment: Fragment, tag: String) {
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
                showBar()
                fragment = MainFragment()
                tag = "main"
                menu_drawer.closeDrawers()
            }
            R.id.nv_login -> {
                showBar()
                fragment = LogInFragment()
                tag = "login"
                menu_drawer.closeDrawers()
            }
            R.id.nv_register -> {
                showBar()
                fragment = RegisterFragment()
                tag = "register"
                menu_drawer.closeDrawers()
            }
            R.id.nv_logout -> {
                showBar()
                //TODO Logout
                menu_drawer.closeDrawers()
            }
        }
        fragment?.let { fr ->
            menu_drawer.closeDrawers()
            showFragment(fr, tag ?: "")
        }
    }

    companion object {
        fun start(context: Activity) {
            val i = Intent(context, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }
    }

    private fun showBar(){
        img_menu.visibility = View.VISIBLE
        img_call.visibility = View.VISIBLE
        background_bar.visibility = View.VISIBLE
    }

    private fun hideBar(){
        img_menu.visibility = View.GONE
        img_call.visibility = View.GONE
        background_bar.visibility = View.GONE
    }

}