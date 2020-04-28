package theinvestors.csci448.investsmart.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.AssetRepository
import theinvestors.csci448.investsmart.data.user.User
import theinvestors.csci448.investsmart.data.user.UserRepository
import theinvestors.csci448.investsmart.util.NetworkUtil
import java.util.*


private const val logTag: String = "LoginFragment"
private const val RC_SIGN_IN: Int = 0
private const val CLIENT_ID: String = "365224585407-9b1eg074rcr6g70p342ggr7j3q7vpmr4.apps.googleusercontent.com"

class LoginFragment: Fragment() {

    private lateinit var userRepository: UserRepository
    private lateinit var assetRepository: AssetRepository


    // Interface to lock and unlock navigation drawer
    interface LoginInterface {
        fun LoginLockDrawer()
        fun LoginUnlockDrawer()
    }

    private lateinit var loginInterface: LoginInterface
    private lateinit var loginBtn: Button
    private lateinit var welcomeText: TextView
    private var networkUtil: NetworkUtil = NetworkUtil()

    private val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(CLIENT_ID)
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
        userRepository = UserRepository.getInstance(requireContext())!!
        assetRepository = AssetRepository.getInstance(requireContext())!!

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
            if(networkUtil.isNetworkAvailableAndConnected(requireActivity())) signIn()
            else Toast.makeText(requireContext(), R.string.internet, Toast.LENGTH_SHORT).show()
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
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

    }

    override fun onPause() {
        Log.d(logTag, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(logTag, "onStop() called")
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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

            if (account != null) {
                MainActivity.email = account.email.toString()
                MainActivity.name = account.displayName.toString()

                var user: LiveData<User?> = userRepository.getUser(account.email.toString())

                user.observe(
                    viewLifecycleOwner,
                    Observer { result ->
                        result.let {

                            if(result == null){
                                Log.d(logTag, "New user added to the database")
                                MainActivity.totalMoney = 1000.0
                                var newUser = User(UUID.randomUUID(), account.email.toString(), 1000.0)
                                userRepository.addUser(newUser)
                                val action = LoginFragmentDirections.actionLoginFragmentToCurrentAssetsFragment()
                                // Added delay to get all the data in the MainActivity ready
                                Handler().postDelayed({
                                    findNavController().navigate(action)
                                }, 1000)
                            }
                            else{
                                MainActivity.totalMoney = result.totalmoney
                                Log.d(logTag, "Got totalMoney from database")
                                val action = LoginFragmentDirections.actionLoginFragmentToCurrentAssetsFragment()
                                // Added delay to get all the data in the MainActivity ready
                                Handler().postDelayed({
                                    findNavController().navigate(action)
                                }, 1000)
                            }
                            user.removeObservers(viewLifecycleOwner)
                        }
                    }
                )

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