package com.example.initiumsolutionsassignment.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.core.text.HtmlCompat
import com.example.initiumsolutionsassignment.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val first = "I hereby agree with "
        val nextToFirst = "<font color='#FF4500'>Terms</font>"
        val second = " & "
        val nextToSecond = "<font color='#FF4500'>Conditions</font>"
        tv_terms_conditions.text = HtmlCompat.fromHtml(first+nextToFirst+second+nextToSecond, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}