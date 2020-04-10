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
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import theinvestors.csci448.investsmart.R

private const val logTag: String = "LoginFragment"

class LoginFragment: Fragment() {

    // Interface to lock and unlock navigation drawer
    interface LoginInterface {
        fun LoginLockDrawer()
        fun LoginUnlockDrawer()
    }

    private lateinit var loginInterface: LoginInterface

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpBtn: Button

    private lateinit var welcomeText: TextView

    private var email: String = "email"
    private var password: String = "password"

    
    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
        loginInterface = context as LoginInterface
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

        loginBtn = view.findViewById(R.id.login_signin_button)
        welcomeText = view.findViewById(R.id.login_welcome_text)

        loginBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToHomeScreenFragment()
            findNavController().navigate(action)
        }

        // Lock the drawer
        loginInterface.LoginLockDrawer()

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
        loginInterface.LoginUnlockDrawer()
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