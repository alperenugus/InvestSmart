package theinvestors.csci448.investsmart.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.AssetRepository
import theinvestors.csci448.investsmart.data.user.User
import theinvestors.csci448.investsmart.data.user.UserRepository
import java.util.*


private const val logTag: String = "ResetAccountFragment"

class ResetAccountFragment: Fragment() {

    private lateinit var emailEditText: EditText
    private var inputEmail: String = ""
    private lateinit var resetBtn: Button
    private lateinit var assetRepository: AssetRepository
    private lateinit var userRepository: UserRepository

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
        assetRepository = AssetRepository.getInstance(requireContext())!!
        userRepository = UserRepository.getInstance(requireContext())!!

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")

        val view: View = inflater.inflate(R.layout.reset_account, container, false)

        emailEditText = view.findViewById(R.id.reset_account_email_edit_text)
        resetBtn = view.findViewById(R.id.reset_account_reset_button)

        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                inputEmail = emailEditText.text.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        resetBtn.setOnClickListener {

            if(inputEmail != MainActivity.email){
                Toast.makeText(requireContext(), R.string.unmatched, Toast.LENGTH_SHORT).show()
            }

            else{

                val dialogBuilder = AlertDialog.Builder(requireContext())

                dialogBuilder.setMessage(R.string.sure)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(R.string.proceed, DialogInterface.OnClickListener {
                            dialog, id ->

                        // Clear database
                        assetRepository.deleteAll()
                        userRepository.deleteUser(MainActivity.email)

                        var user: User = User(UUID.randomUUID(), MainActivity.email, 1000.0)
                        userRepository.addUser(user)

                        // Hide keyboard
                        val imm: InputMethodManager =
                            activity!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(view.windowToken, 0)

                        // Navigate to home
                        var action = ResetAccountFragmentDirections.actionResetAccountFragmentToCurrentAssetsFragment()
                        findNavController().navigate(action)
                    })
                    // negative button text and action
                    .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle(R.string.reset_account)
                // show alert dialog
                alert.show()
            }


        }


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