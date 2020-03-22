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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.AssetModel
import theinvestors.csci448.investsmart.data.FavCompModel
import theinvestors.csci448.investsmart.service.AssetService
import theinvestors.csci448.investsmart.service.FavCompService
import theinvestors.csci448.investsmart.service.UserService

private const val logTag: String = "SignUpFragment"

class SignUpFragment: Fragment() {

    private lateinit var emailTextView: EditText
    private lateinit var passwordTextView: EditText
    private lateinit var passwordAgainTextView: EditText
    private lateinit var signUpBtn: Button

    private lateinit var email: String
    private var password: String = "nulla"
    private var passwordAgain: String = "nullb"

    private val userService =
        UserService()

    private lateinit var signUpRequest: LiveData<Boolean>


    // TEST PURPOSES
    private val assetService = AssetService()
    private lateinit var assetRequest: LiveData<List<AssetModel>>

    private val favCompService = FavCompService()
    private lateinit var favCompRequest: LiveData<List<FavCompModel>>


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
        val view: View = inflater.inflate(R.layout.sign_up, container, false)

        emailTextView = view.findViewById(R.id.sign_up_email_edit_text)
        passwordTextView = view.findViewById(R.id.sign_up_password_edit_text)
        passwordAgainTextView = view.findViewById(R.id.sign_up_password_again_edit_text)
        signUpBtn = view.findViewById(R.id.sign_up_signup_button)

        emailTextView.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                email = emailTextView.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        passwordTextView.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                password = passwordTextView.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        passwordAgainTextView.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                passwordAgain = passwordAgainTextView.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        signUpBtn.setOnClickListener {
            // Implement new user database call

            if (password == passwordAgain){

                signUpRequest = userService.saveUser(email, password)

                signUpRequest.observe(
                    viewLifecycleOwner,
                    Observer { signUpRequest ->
                        signUpRequest.let {
                            Log.i(logTag, "Saved ${signUpRequest.toString()}")
                            val action =
                                SignUpFragmentDirections.actionSignUpFragmentToLoginFragment()
                            findNavController().navigate(action)
                        }
                    }
                )

            }else {
                Toast.makeText(context, "Passwords should match!", Toast.LENGTH_SHORT).show()
            }
        }

        // TESTING THE SERVICES
        assetRequest = assetService.getAssets("Kuleli")

        assetRequest.observe(
            viewLifecycleOwner,
            Observer { assetRequest ->
                assetRequest.let {
                    Log.d(logTag, assetRequest.toString())
                }
            }
        )

        favCompRequest = favCompService.getFavComp("alpi")

        favCompRequest.observe(
            viewLifecycleOwner,
            Observer { favCompRequest ->
                favCompRequest.let {
                    Log.d(logTag, favCompRequest.toString())
                }
            }
        )


        return view
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