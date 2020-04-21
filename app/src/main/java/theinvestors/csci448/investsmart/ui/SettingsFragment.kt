package theinvestors.csci448.investsmart.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceScreen
import theinvestors.csci448.investsmart.R

private const val logTag: String = "SettingsFragment"

class SettingsFragment: PreferenceFragmentCompat() {

    private var save2Database: Preference? = null
    private var deleteDatabase: Preference? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        val prefScreen: PreferenceScreen? = preferenceScreen

        save2Database = findPreference("database_save")
        deleteDatabase = findPreference("database_delete")



    }
}