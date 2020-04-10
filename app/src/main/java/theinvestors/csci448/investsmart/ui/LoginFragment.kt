package theinvestors.csci448.investsmart.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R


private const val logTag: String = "LoginFragment"
private const val RC_SIGN_IN: Int = 0

class LoginFragment: Fragment() {

    // Interface to lock and unlock navigation drawer
    interface LoginInterface {
        fun LoginLockDrawer()
        fun LoginUnlockDrawer()
    }

    private lateinit var loginInterface: LoginInterface
    private lateinit var loginBtn: Button
    private lateinit var welcomeText: TextView

    private val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
        loginInterface = context as LoginInterface
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
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
            signIn()
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

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account =
                completedTask.getResult(ApiException::class.java)

            Log.d(logTag, "Signed in successfully, show authenticated UI")

            MainActivity.signedIn = true
            val action = LoginFragmentDirections.actionLoginFragmentToHomeScreenFragment()
            findNavController().navigate(action)

            if (account != null) {
                MainActivity.email = account.email.toString()
            }

            // Signed in successfully, show authenticated UI.
            //updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(logTag, "signInResult:failed code=" + e.statusCode)
            //updateUI(null)
        }
    }
}