package theinvestors.csci448.investsmart.ui.CurrentAssets

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.Asset
import theinvestors.csci448.investsmart.data.asset.AssetRepository
import theinvestors.csci448.investsmart.data.user.User
import theinvestors.csci448.investsmart.data.user.UserRepository
import java.util.*

private const val logTag: String = "CurrentAssetsFragment"

class CurrentAssetsFragment: Fragment() {

    private lateinit var assetsRecyclerView: RecyclerView
    private lateinit var adapter: AssetAdapter
    private lateinit var factory: CurrentAssetsViewModelFactory
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var totalMoneyTextView: TextView
    private lateinit var assetRepository: AssetRepository
    private lateinit var userRepository: UserRepository
    private lateinit var mContext: Context


    private val currentAssetsViewModel: CurrentAssetsViewModel by lazy {
        ViewModelProvider(this@CurrentAssetsFragment, factory)
            .get(CurrentAssetsViewModel::class.java)
    }

    fun updateUI(assets : List<Asset>){
        adapter = AssetAdapter(assets, mContext)
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
        assetRepository = AssetRepository.getInstance(requireContext())!!
        userRepository = UserRepository.getInstance(requireContext())!!
        // Context to pass recycler view
        mContext = requireActivity()
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

        val itemDecor = DividerItemDecoration(requireContext(), ClipDrawable.HORIZONTAL)
        assetsRecyclerView.addItemDecoration(itemDecor)

        nameTextView = view.findViewById(R.id.name_text_view)
        nameTextView.text = " " + MainActivity.name + "!"

        emailTextView = view.findViewById(R.id.email_text_view)
        emailTextView.text = MainActivity.email

        totalMoneyTextView = view.findViewById(R.id.total_money_text_view)
        totalMoneyTextView.text = String.format("%.1f", MainActivity.totalMoney)

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
//        var asset1: Asset = Asset(UUID.randomUUID(), MainActivity.email, "SpaceX", 100)
//        var asset2: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Apple", 200)
//        var asset3: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Microsoft", 300)
//        var asset4: Asset = Asset(UUID.randomUUID(), MainActivity.email, "IBM", 50)
//        var asset5: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Samsung", 75)
//        var asset6: Asset = Asset(UUID.randomUUID(), MainActivity.email, "Siemens", 140)
//
//        currentAssetsViewModel.addAsset(asset1)
//        currentAssetsViewModel.addAsset(asset2)
//        currentAssetsViewModel.addAsset(asset3)
//        currentAssetsViewModel.addAsset(asset4)
//        currentAssetsViewModel.addAsset(asset5)
//        currentAssetsViewModel.addAsset(asset6)

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

    inner class AssetHolder(val view: View) : RecyclerView.ViewHolder(view) {
        lateinit var asset: Asset
        var companyNameTextView: TextView = itemView.findViewById(R.id.list_item_asset_company_name_text)
        var ownedShareTextView: TextView = itemView.findViewById(R.id.list_item_asset_owned_share_text)
        var sellBtn: Button = itemView.findViewById(R.id.asset_sell_button)
        var predictBtn: Button = itemView.findViewById(R.id.asset_predict_button)

        fun bind(asset: Asset, mContext: Context){
            this.asset = asset
            companyNameTextView.text = asset.company
            ownedShareTextView.text = asset.owned_shares.toString()


            sellBtn.setOnClickListener {
                Log.d(logTag, "Sell Button Clicked.")

                val dialogBuilder = AlertDialog.Builder(requireContext())

                dialogBuilder.setMessage(R.string.sell_confirmation)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(R.string.proceed, DialogInterface.OnClickListener {
                            dialog, id ->

                        var user : LiveData<User?> = userRepository.getUser(MainActivity.email)
                        var oldAsset: LiveData<Asset> = assetRepository.getAsset(MainActivity.email, asset.company)


                        user.observe(
                            viewLifecycleOwner,
                            Observer { userResult -> userResult.let {

                                if(userResult != null){

                                    oldAsset.observe(
                                        viewLifecycleOwner,
                                        Observer { assetResult -> assetResult.let {

                                            if(assetResult != null){
                                                if(assetResult.owned_shares == 1){
                                                    Log.d(logTag, "User only has 1 stock of this company")
                                                    // Delete the asset
                                                    assetRepository.deleteAsset(assetResult)
                                                    // Update user money and text view
                                                    userResult.totalmoney += MainActivity.companyValues[asset.company]!!.current.toFloat()
                                                    MainActivity.totalMoney = userResult.totalmoney
                                                    userRepository.addUser(userResult)
                                                    totalMoneyTextView.text = String.format("%.1f", userResult.totalmoney)
                                                }
                                                else{
                                                    Log.d(logTag, "User has many stocks of this company")

                                                    assetResult.owned_shares -= 1
                                                    assetRepository.addAsset(assetResult)

                                                    // Update user money and text view
                                                    userResult.totalmoney += MainActivity.companyValues[asset.company]!!.current.toFloat()
                                                    MainActivity.totalMoney = userResult.totalmoney
                                                    userRepository.addUser(userResult)
                                                    totalMoneyTextView.text = String.format("%.1f", userResult.totalmoney)

                                                }
                                            }

                                            // Remove observers to prevent infinite loops since the observed items changes with this code
                                            oldAsset.removeObservers(viewLifecycleOwner)
                                            user.removeObservers(viewLifecycleOwner)

                                        } }
                                    )
                                }
                            } }
                        )

                    })
                    // negative button text and action
                    .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle(R.string.confirmation)
                // show alert dialog
                alert.show()
            }

            predictBtn.setOnClickListener {

            }


        }
    }

    inner class AssetAdapter(private val assets: List<Asset>, mContext: Context): RecyclerView.Adapter<AssetHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_asset, parent, false)
            return AssetHolder(view)
        }

        override fun getItemCount(): Int {
            return assets.size
        }

        override fun onBindViewHolder(holder: AssetHolder, position: Int) {
            val asset = assets[position]
            holder.bind(asset, mContext)
        }

    }


}