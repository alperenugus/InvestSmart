package theinvestors.csci448.investsmart.ui.FavoriteCompanies

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
import theinvestors.csci448.investsmart.data.favcomp.FavComp
import java.util.*

private const val logTag: String = "FavoriteCompFragment"

class FavoriteCompaniesFragment: Fragment() {

    private lateinit var favCompsRecyclerView: RecyclerView
    private lateinit var adapter: FavCompAdapter
    private lateinit var factory: FavCompViewModelFactory


    private val favCompViewModel: FavCompViewModel by lazy {
        ViewModelProvider(this@FavoriteCompaniesFragment, factory)
            .get(FavCompViewModel::class.java)
    }

    fun updateUI(favComps : List<FavComp>){
        adapter = FavCompAdapter(favComps){
                favComp: FavComp -> Unit
        }
        favCompsRecyclerView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
        factory = FavCompViewModelFactory(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")

        val view: View = inflater.inflate(R.layout.favorite_companies, container, false)

        favCompsRecyclerView = view.findViewById(R.id.fav_comps_recycler_view)
        favCompsRecyclerView.layoutManager = LinearLayoutManager(context)

        updateUI(emptyList())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(logTag, "onViewCreated() called")
        // Load content from the database

        favCompViewModel.favComps.observe(
            viewLifecycleOwner,
            Observer { favComps ->
                favComps.let {
                    Log.d(logTag, "Got favComps ${favComps.toString()}")
                    updateUI(favComps)
                }
            }
        )

        // Sample data
        var favComp1: FavComp = FavComp(UUID.randomUUID(), MainActivity.email, "Apple")
        var favComp2: FavComp = FavComp(UUID.randomUUID(), MainActivity.email, "Samsung")
        var favComp3: FavComp = FavComp(UUID.randomUUID(), MainActivity.email, "Microsoft")
        var favComp4: FavComp = FavComp(UUID.randomUUID(), MainActivity.email, "Siemens")
        var favComp5: FavComp = FavComp(UUID.randomUUID(), MainActivity.email, "SpaceX")
        var favComp6: FavComp = FavComp(UUID.randomUUID(), MainActivity.email, "IBM")

        favCompViewModel.addFavComp(favComp1)
        favCompViewModel.addFavComp(favComp2)
        favCompViewModel.addFavComp(favComp3)
        favCompViewModel.addFavComp(favComp4)
        favCompViewModel.addFavComp(favComp5)
        favCompViewModel.addFavComp(favComp6)
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