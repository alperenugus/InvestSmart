package theinvestors.csci448.investsmart.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.service.UserService

private const val logTag: String = "LoginFragment"

class LoginFragment: Fragment() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button

    private lateinit var welcomeText: TextView

    private var email: String = "null"
    private var password: String = "null"

    private val userService =
        UserService()

    private lateinit var loginRequest: LiveData<Boolean>



    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")

        val view: View = inflater.inflate(R.layout.login , container, false)

        emailEditText = view.findViewById(R.id.login_email_edit_text)
        passwordEditText = view.findViewById(R.id.login_password_edit_text)
        loginBtn = view.findViewById(R.id.login_signin_button)
        signUpBtn = view.findViewById(R.id.login_signup_button)
        welcomeText = view.findViewById(R.id.login_welcome_text)

        emailEditText.addTextChangedListener(object: TextWatcher{

            override fun afterTextChanged(s: Editable?) {
                email = emailEditText.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        passwordEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                password = passwordEditText.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        loginBtn.setOnClickListener {

            loginRequest = userService.getUser(email, password)

            loginRequest.observe(
                viewLifecycleOwner,
                Observer{loginRequest ->
                    loginRequest.let {
                        Log.i(logTag, "Got text ${loginRequest.toString()}")
                        if(loginRequest){
                            val action =
                                LoginFragmentDirections.actionLoginFragmentToHomeScreenFragment()
                            findNavController().navigate(action)
                        }

                        else{
                            Toast.makeText(context, "Username or password is wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )



        }

        signUpBtn.setOnClickListener {
            val action =
                LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(action)
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(logTag, "onViewCreated() called")
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(logTag, "onActivityCreated() called")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        Log.d(logTag, "onStart() called")
        super.onStart()
    }

    override fun onResume() {
        Log.d(logTag, "onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(logTag, "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(logTag, "onDestroy() called")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(logTag, "onDetach() called")
        super.onDetach()
    }
}