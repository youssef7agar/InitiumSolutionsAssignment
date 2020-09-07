package com.example.initiumsolutionsassignment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.initiumsolutionsassignment.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClicks()
    }

    private fun btnClicks(){
        btn_login.setOnClickListener {
            (activity as MainActivity).navigate(R.id.nv_login)
        }
        tv_register.setOnClickListener {
            (activity as MainActivity).navigate(R.id.nv_register)
        }
    }
}