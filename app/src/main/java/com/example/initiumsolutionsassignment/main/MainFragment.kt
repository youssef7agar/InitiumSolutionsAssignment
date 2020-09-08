package com.example.initiumsolutionsassignment.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.model.Entity
import com.example.initiumsolutionsassignment.model.MainResponse
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var adapter: EntityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClicks()

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it?.let {
                when {
                    it.loading -> {
                        rv_entities.visibility = View.INVISIBLE
                        tv_select_entity.visibility = View.INVISIBLE
                        tv_no_entities.visibility = View.INVISIBLE
                        progress_bar.visibility = View.VISIBLE
                    }
                    it.error != null -> {
                        rv_entities.visibility = View.INVISIBLE
                        tv_select_entity.visibility = View.INVISIBLE
                        progress_bar.visibility = View.GONE
                        tv_no_entities.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), "Something wrong happened", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        rv_entities.visibility = View.VISIBLE
                        tv_select_entity.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        tv_no_entities.visibility = View.INVISIBLE

                        val fullList = createEntityList(it.entities ?: listOf())
                        val shortList = getList(fullList)

                        adapter = EntityAdapter {
                            val fragment = OthersFragment()
                            val bundle = Bundle()
                            val fullArrayList = ArrayList<Entity>()
                            fullList.toCollection(fullArrayList)
                            bundle.putParcelableArrayList("list", fullArrayList)
                            fragment.arguments = bundle
                            (activity as MainActivity).showFragment(fragment, "others")
                        }

                        rv_entities.adapter = adapter
                        adapter.submitList(shortList)
                    }
                }
            }
        })
    }

    private fun btnClicks() {
        btn_login.setOnClickListener {
            (activity as MainActivity).navigate(R.id.nv_login)
        }
        tv_register.setOnClickListener {
            (activity as MainActivity).navigate(R.id.nv_register)
        }
    }

    private fun createEntityList(oldEntities: List<MainResponse.Entity>): List<Entity> {
        val newEntities: MutableList<Entity> = mutableListOf()
        if (oldEntities.isNotEmpty()) {
            for (i in oldEntities) {
                newEntities.add(Entity(i.entityEnglishName ?: "", toBitMap(i.entityLogo ?: "")))
            }
        }
        Log.d("MainFragment", newEntities.size.toString())
        return newEntities
    }

    private fun toBitMap(stringImage: String): Bitmap {
        val decodedString: ByteArray = Base64.decode(stringImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    private fun getList(oldList: List<Entity>): List<Entity> {
        return if (oldList.size < 4) {
            oldList
        } else {
            val entity = Entity(
                name = "Others",
                image = AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.ic_baseline_more_horiz_35
                )?.toBitmap()
            )
            val newList: MutableList<Entity> = mutableListOf()
            newList.add(oldList[0])
            newList.add(oldList[1])
            newList.add(oldList[2])
            newList.add(entity)
            newList
        }
    }
}