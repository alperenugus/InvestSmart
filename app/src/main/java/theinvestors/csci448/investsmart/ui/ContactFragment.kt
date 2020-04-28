package theinvestors.csci448.investsmart.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R


private const val logTag: String = "ContactFragment"

class ContactFragment: Fragment() {

    private lateinit var contactNameEditText: EditText
    private lateinit var contactSubjectEditText: EditText
    private lateinit var contactMessageEditText: EditText
    private lateinit var contactSubmitBtn: Button

    private lateinit var name: String
    private lateinit var subject: String
    private lateinit var message: String

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

        val view: View = inflater.inflate(R.layout.contact, container, false)

        contactNameEditText = view.findViewById(R.id.contact_name)
        contactSubjectEditText = view.findViewById(R.id.contact_subject)
        contactMessageEditText = view.findViewById(R.id.contact_message)
        contactSubmitBtn = view.findViewById(R.id.contact_submit)

        contactNameEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                name = contactNameEditText.text.toString()
            }

        })

        contactSubjectEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                subject = contactSubjectEditText.text.toString()
            }

        })

        contactMessageEditText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                message = contactMessageEditText.text.toString()
            }

        })

        contactSubmitBtn.setOnClickListener {

            if(name.isNullOrEmpty() || subject.isNullOrEmpty() || message.isNullOrEmpty()){
                Toast.makeText(requireContext(), R.string.input_problem, Toast.LENGTH_SHORT).show()
            }

            else{
                val emailIntent = Intent(
                    Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", MainActivity.InvestSmartEmail, null
                    )
                )
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
                emailIntent.putExtra(Intent.EXTRA_TEXT, message + "\n\n" + name)
                startActivity(Intent.createChooser(emailIntent, "Send email..."))
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