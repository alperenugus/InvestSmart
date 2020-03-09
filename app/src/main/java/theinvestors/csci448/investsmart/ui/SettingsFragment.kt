package theinvestors.csci448.investsmart.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import theinvestors.csci448.investsmart.R

private const val logTag: String = "SettingsFragment"

class SettingsFragment: Fragment() {

    private lateinit var changePasswordBtn: Button
    private lateinit var changeEmailBtn: Button
    private lateinit var resetAccountBtn: Button
    private lateinit var aboutUsBtn: Button


    interface CallBacks{
        fun settingsChangePasswordClicked()
        fun settingsChangeEmailClicked()
        fun settingsResetAccountClicked()
        fun settingsAboutUsClicked()
    }

    private var callBacks: CallBacks? = null


    override fun onAttach(context: Context?) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
        callBacks = context as CallBacks
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

        val view: View = inflater.inflate(R.layout.settings, container, false)

        changePasswordBtn = view.findViewById(R.id.settings_change_password_button)
        changeEmailBtn = view.findViewById(R.id.settings_change_email_button)
        resetAccountBtn = view.findViewById(R.id.settings_reset_account_button)
        aboutUsBtn = view.findViewById(R.id.settings_about_us_button)

        changePasswordBtn.setOnClickListener {
            callBacks?.settingsChangePasswordClicked()
        }

        changeEmailBtn.setOnClickListener {
            callBacks?.settingsChangeEmailClicked()
        }

        resetAccountBtn.setOnClickListener {
            callBacks?.settingsResetAccountClicked()
        }

        aboutUsBtn.setOnClickListener {
            callBacks?.settingsAboutUsClicked()
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
        callBacks = null
    }
}