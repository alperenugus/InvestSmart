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
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.Asset
import java.util.*

private const val logTag: String = "CurrentAssetsFragment"

class CurrentAssetsFragment: Fragment() {

    private lateinit var assetsRecyclerView: RecyclerView
    private lateinit var adapter: AssetAdapter
    private lateinit var factory: CurrentAssetsViewModelFactory

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

        currentAssetsViewModel.currentAssets.observe(
            viewLifecycleOwner,
            Observer { assetList ->
                assetList?.let {
                    Log.d(logTag, "Got assetList ${assetList.toString()}")
                    updateUI(assetList)
                }
            }
        )

        // Sample data
        var asset1: Asset = Asset(UUID.randomUUID(), MainActivity.email, "SpaceX", 100)
        var asset2: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Apple", 200)
        var asset3: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Microsoft", 300)
        var asset4: Asset = Asset(UUID.randomUUID(), MainActivity.email, "IBM", 50)
        var asset5: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Samsung", 75)
        var asset6: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Siemens", 140)

        currentAssetsViewModel.addAsset(asset1)
        currentAssetsViewModel.addAsset(asset2)
        currentAssetsViewModel.addAsset(asset3)
        currentAssetsViewModel.addAsset(asset4)
        currentAssetsViewModel.addAsset(asset5)
        currentAssetsViewModel.addAsset(asset6)

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