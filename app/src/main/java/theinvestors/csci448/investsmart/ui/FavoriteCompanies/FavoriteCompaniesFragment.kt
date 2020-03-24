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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.FavCompModel
import theinvestors.csci448.investsmart.service.FavCompService

private const val logTag: String = "FavoriteCompFragment"

class FavoriteCompaniesFragment: Fragment() {

    private lateinit var favCompsRecyclerView: RecyclerView
    private lateinit var adapter: FavCompAdapter

    private val favCompService = FavCompService()
    private lateinit var favCompRequest: LiveData<List<FavCompModel>>

    fun updateUI(favComps : List<FavCompModel>){
        adapter = FavCompAdapter(favComps){
                favComp: FavCompModel -> Unit
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

        favCompRequest = favCompService.getFavComps("alperenugus@gmail.com")

        favCompRequest.observe(
            viewLifecycleOwner,
            Observer { favCompRequest ->
                favCompRequest.let {
                    Log.d(logTag, favCompRequest.toString())
                    updateUI(favCompRequest)
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