package theinvestors.csci448.investsmart.ui.CurrentAssets

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.Asset

private const val logTag: String = "CurrentAssetsFragment"

class CurrentAssetsFragment: Fragment() {

    private var email = "alperenugus@gmail.com"
    private var password = "123"

    private lateinit var assetsRecyclerView: RecyclerView
    private lateinit var adapter: AssetAdapter
    private lateinit var factory: CurrentAssetsViewModelFactory
    private lateinit var currentAssets: LiveData<Asset?>

    private val currentAssetsViewModel: CurrentAssetsViewModel by lazy {
        ViewModelProvider(this@CurrentAssetsFragment, factory)
            .get(CurrentAssetsViewModel::class.java)
    }

    fun updateUI(assets : List<Asset>){
        adapter = AssetAdapter(assets){
                asset: Asset -> Unit
        }
        assetsRecyclerView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
        factory = CurrentAssetsViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")

        val view: View = inflater.inflate(R.layout.current_assets, container, false)

        assetsRecyclerView = view.findViewById(R.id.current_assets_recycler_view)
        assetsRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI(emptyList())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(logTag, "onViewCreated() called")
        // Load content from the database

        currentAssets = currentAssetsViewModel.getAssets(email)


        currentAssets.observe(
            viewLifecycleOwner,
            Observer { assetList ->
                assetList.let {
                    Log.d(logTag, "Got assetList ${assetList.toString()}")
                }
            }
        )

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