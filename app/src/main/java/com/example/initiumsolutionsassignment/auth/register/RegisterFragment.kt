package com.example.initiumsolutionsassignment.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.main.MainActivity
import com.example.initiumsolutionsassignment.main.MainFragment
import com.example.initiumsolutionsassignment.model.RegisterRequest
import com.example.initiumsolutionsassignment.model.User
import kotlinx.android.synthetic.main.fragment_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showTermsConditions()
        btnClicks()

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it?.let {
                when {
                    it.loading -> {
                        progress_bar.visibility = View.VISIBLE
                        btn_complete_registration.isEnabled = false
                    }
                    it.error != null -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Something wrong happened, please try again",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        viewModel.cacheUser(User(
                            customerFirstName = it.user?.customerFirstName ?: "",
                            customerLastName = it.user?.customerLastName ?: "",
                            customerId = it.user?.customerId
                        ))
                        val fragment = MainFragment()
                        val bundle = Bundle()
                        bundle.putString("first name", it.user?.customerFirstName)
                        bundle.putString("last name", it.user?.customerLastName)
                        fragment.arguments = bundle
                        (activity as MainActivity).showFragment(fragment, "main")
                    }
                }
            }
        })
    }

    private fun btnClicks() {
        btn_complete_registration.setOnClickListener {
            if (
                et_first_name.text.toString().isEmpty() ||
                et_last_name.text.toString().isEmpty() ||
                et_mobile_number.text.toString().isEmpty() ||
                et_civil_id.text.toString().isEmpty() ||
                et_email_address.text.toString().isEmpty() ||
                et_password.text.toString().isEmpty() ||
                et_retype_password.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Please fill all spaces", Toast.LENGTH_SHORT)
                    .show()
            } else if (et_password.text.toString() != et_retype_password.text.toString()) {
                Toast.makeText(requireContext(), "Check your password", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isEmailValid(et_email_address.text.toString())) {
                Toast.makeText(requireContext(), "Enter a valid email", Toast.LENGTH_SHORT)
                    .show()
            } else if (!chk_terms_conditions.isChecked) {
                Toast.makeText(
                    requireContext(),
                    "You need to accept the terms and conditions to register",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                viewModel.register(
                    RegisterRequest(
                        customerFirstName = et_first_name.text.toString(),
                        customerLastName = et_last_name.text.toString(),
                        customerMobileNo = et_mobile_number.text.toString(),
                        customerCivilID = et_civil_id.text.toString(),
                        customerEmail = et_email_address.text.toString(),
                        customerPassword = et_password.text.toString()
                    )
                )
            }
        }
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

    private fun isEmailValid(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}