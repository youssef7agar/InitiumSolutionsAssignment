package com.example.initiumsolutionsassignment.auth.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.initiumsolutionsassignment.R
import com.example.initiumsolutionsassignment.auth.UserStatus
import com.example.initiumsolutionsassignment.auth.UserStatusChange
import com.example.initiumsolutionsassignment.main.MainActivity
import com.example.initiumsolutionsassignment.main.MainFragment
import com.example.initiumsolutionsassignment.model.LogInRequest
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.et_email_address
import kotlinx.android.synthetic.main.fragment_login.et_password
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    var statusChangeListener: UserStatusChange? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            statusChangeListener = (parentFragment ?: activity) as UserStatusChange
        } catch (e: Exception) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnClicks()

        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            it?.let {
                btn_login.isEnabled = true
                when {
                    it.loading -> {
                        progress_bar.visibility = View.VISIBLE
                        btn_login.isEnabled = false
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
                        val fragment = MainFragment.newInstance(
                            it.user?.customerFirstName ?: "",
                            it.user?.customerLastName ?: ""
                        )
                        statusChangeListener?.onStatusChange(
                            UserStatus.LoggedIn(
                                it.user?.customerFirstName ?: "",
                                it.user?.customerLastName ?: ""
                            )
                        )
                        (activity as MainActivity).showFragment(fragment, "main")
                        progress_bar.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun btnClicks() {
        tv_register.setOnClickListener {
            (activity as MainActivity).navigate(R.id.nv_register)
        }
        btn_login.setOnClickListener {
            if (et_email_address.text.toString().isEmpty() ||
                et_password.text.toString().isEmpty()
            ) {
                Toast.makeText(requireContext(), "Please fill all spaces", Toast.LENGTH_SHORT)
                    .show()
            } else if (!isEmailValid(et_email_address.text.toString())) {
                Toast.makeText(requireContext(), "Enter a valid email", Toast.LENGTH_SHORT)
                    .show()
            } else {
                viewModel.logIn(
                    LogInRequest(
                        customerEmail = et_email_address.text.toString(),
                        customerPassword = et_password.text.toString()
                    ),
                    switch_credentials_saving.isChecked
                )
            }
        }
    }

    private fun isEmailValid(email: String?): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}