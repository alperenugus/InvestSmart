package theinvestors.csci448.investsmart.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import theinvestors.csci448.investsmart.R

private const val logTag: String = "AssetsFragment"

class AssetsFragment: Fragment() {

    private lateinit var currentAssetsBtn: Button
    private lateinit var favCompaniesBtn: Button
    private lateinit var investBtn: Button

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

        val view: View = inflater.inflate(R.layout.assets, container, false)

        currentAssetsBtn = view.findViewById(R.id.assets_current_assets_button)
        favCompaniesBtn = view.findViewById(R.id.assets_fav_companies_button)
        investBtn = view.findViewById(R.id.assets_invest_button)

        currentAssetsBtn.setOnClickListener {
            val action =
                AssetsFragmentDirections.actionAssetsFragmentToCurrentAssetsFragment()
            findNavController().navigate(action)
        }

        favCompaniesBtn.setOnClickListener {
            val action =
                AssetsFragmentDirections.actionAssetsFragmentToFavoriteCompaniesFragment()
            findNavController().navigate(action)
        }

        investBtn.setOnClickListener {
            val action =
                AssetsFragmentDirections.actionAssetsFragmentToInvestFragment()
            findNavController().navigate(action)
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