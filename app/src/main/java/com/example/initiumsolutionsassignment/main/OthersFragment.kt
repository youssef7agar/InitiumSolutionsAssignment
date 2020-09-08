package com.example.initiumsolutionsassignment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.model.Entity
import kotlinx.android.synthetic.main.fragment_others.*

class OthersFragment: Fragment() {

    private lateinit var adapter: EntityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_others, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        if (bundle != null){
            val list = bundle.getParcelableArrayList<Entity>("list")?.toList()
            adapter = EntityAdapter {}
            rv_entities.adapter = adapter
            adapter.submitList(list)
        }
    }
}