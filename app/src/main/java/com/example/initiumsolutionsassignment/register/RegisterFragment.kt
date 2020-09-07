package com.example.initiumsolutionsassignment.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.example.initiumsolutionsassignment.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTermsConditions()
    }

    private fun showTermsConditions() {
        val first = "I hereby agree with "
        val nextToFirst = "<font color='#FF4500'>Terms</font>"
        val second = " & "
        val nextToSecond = "<font color='#FF4500'>Conditions</font>"
        tv_terms_conditions.text = HtmlCompat.fromHtml(
            first + nextToFirst + second + nextToSecond,
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )

    }
}