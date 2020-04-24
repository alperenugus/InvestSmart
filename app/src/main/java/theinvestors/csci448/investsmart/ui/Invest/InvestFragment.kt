package theinvestors.csci448.investsmart.ui.Invest

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.api.CompanyValue
import theinvestors.csci448.investsmart.data.asset.Asset
import theinvestors.csci448.investsmart.data.asset.AssetRepository
import theinvestors.csci448.investsmart.data.user.User
import theinvestors.csci448.investsmart.data.user.UserRepository
import java.util.*
import kotlin.collections.ArrayList


private const val logTag: String = "InvestFragment"

class InvestFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var totalMoneyTextView: TextView
    private lateinit var adapter: InvestAdapter
    private lateinit var companies: MutableList<Company>
    private lateinit var factory: InvestViewModelFactory
    private lateinit var mContext: Context
    private lateinit var assetRepository: AssetRepository
    private lateinit var userRepository: UserRepository


    private val investViewModel: InvestViewModel by lazy {
        ViewModelProvider(this@InvestFragment, factory)
            .get(InvestViewModel::class.java)
    }


    override fun onAttach(context: Context) {
        Log.d(logTag, "onAttach() called")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(logTag, "onCreate() called")
        super.onCreate(savedInstanceState)
        factory = InvestViewModelFactory(requireContext())
        assetRepository = AssetRepository.getInstance(requireContext())!!
        userRepository = UserRepository.getInstance(requireContext())!!

        // Context to pass recycler view
        mContext = requireActivity()


        companies = ArrayList()

        MainActivity.companyValues.forEach {
            var companyValue: CompanyValue = CompanyValue("", "", "", "", "", "")
            var company: Company = Company("", companyValue)
            company.companyName = it.key
            company.companyValue = it.value

            companies.add(company)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(logTag, "onCreateView() called")

        val view: View = inflater.inflate(R.layout.invest, container, false)

        recyclerView = view.findViewById(R.id.invest_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val itemDecor = DividerItemDecoration(requireContext(), HORIZONTAL)
        recyclerView.addItemDecoration(itemDecor)

        totalMoneyTextView = view.findViewById(R.id.invest_total_money)
        totalMoneyTextView.text = totalMoneyTextView.text.toString() + String.format("%.1f", MainActivity.totalMoney)

        updateUI(emptyList())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        investViewModel.currentAssets.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { assetList ->
                assetList.let {
                    Log.d(logTag, "Got assetList ${assetList.toString()}")
                }
            }
        )
        updateUI(companies)
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

    fun updateUI(companies: List<Company>){
        adapter = InvestAdapter(companies, mContext)
        recyclerView.adapter = adapter
    }

    inner class InvestHolder(val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var company: Company

        var companyNameTextView: TextView = itemView.findViewById(R.id.invest_company_name)
        var companyValue: TextView = itemView.findViewById(R.id.invest_company_value)
        var companyO: TextView = itemView.findViewById(R.id.invest_company_o)
        var companyH: TextView = itemView.findViewById(R.id.invest_company_h)
        var companyL: TextView = itemView.findViewById(R.id.invest_company_l)
        var companyPc: TextView = itemView.findViewById(R.id.invest_company_pc)
        var investBtn: Button = itemView.findViewById(R.id.list_item_invest_button)


        fun bind(company: Company, mContext: Context){
            this.company = company
            companyNameTextView.text = company.companyName
            companyValue.text = company.companyValue.current
            companyO.text = String.format("%.1f", company.companyValue.open.toFloat())
            companyH.text = String.format("%.1f", company.companyValue.high.toFloat())
            companyL.text = String.format("%.1f", company.companyValue.low.toFloat())
            companyPc.text = String.format("%.1f", company.companyValue.pc.toFloat())


            investBtn.setOnClickListener {
                Log.d(logTag, "Invest Clicked.")
                Log.d(logTag, "${companyNameTextView.text}.")

                // User money is not enough
                if(MainActivity.totalMoney < company.companyValue.current.toFloat()){
                    Toast.makeText(mContext, R.string.not_enough_money, Toast.LENGTH_SHORT).show()
                }
                // User has enough money
                else{

                    val dialogBuilder = AlertDialog.Builder(requireContext())

                    dialogBuilder.setMessage(R.string.invest_confirmation)
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton(R.string.proceed, DialogInterface.OnClickListener {
                                dialog, id ->

                            var user : LiveData<User?> = userRepository.getUser(MainActivity.email)
                            var oldAsset: LiveData<Asset> = assetRepository.getAsset(MainActivity.email, company.companyName)

                            user.observe(
                                viewLifecycleOwner,
                                androidx.lifecycle.Observer {
                                        userResult -> userResult.let {

                                    if(userResult != null){
                                        oldAsset.observe(
                                            viewLifecycleOwner,
                                            androidx.lifecycle.Observer {
                                                    result -> result.let {
                                                if(result == null){
                                                    Log.d(logTag, "User does not owe any assets of this company")
                                                    var newAsset: Asset = Asset(UUID.randomUUID(), MainActivity.email, company.companyName, 1)
                                                    assetRepository.addAsset(newAsset)

                                                    // Decrease user total money from both database and MainActivity
                                                    userResult.totalmoney -= company.companyValue.current.toFloat()
                                                    userRepository.addUser(userResult)
                                                    MainActivity.totalMoney = userResult.totalmoney
                                                    totalMoneyTextView.text = getString(R.string.total_money) + String.format("%.1f", userResult.totalmoney)

                                                    // Remove observers to prevent infinite loops since the observed items changes with this code
                                                    oldAsset.removeObservers(viewLifecycleOwner)
                                                    user.removeObservers(viewLifecycleOwner)
                                                }
                                                //
                                                else{
                                                    Log.d(logTag, "User previously bought assets from this company")
                                                    result.owned_shares += 1
                                                    assetRepository.addAsset(result)

                                                    // Decrease user total money from both database and MainActivity
                                                    userResult.totalmoney -= company.companyValue.current.toFloat()
                                                    userRepository.addUser(userResult)
                                                    MainActivity.totalMoney = userResult.totalmoney
                                                    totalMoneyTextView.text = getString(R.string.total_money) + String.format("%.1f", userResult.totalmoney)

                                                    // Remove observers to prevent infinite loops since the observed items changes with this code
                                                    oldAsset.removeObservers(viewLifecycleOwner)
                                                    user.removeObservers(viewLifecycleOwner)
                                                }

                                            }
                                            }
                                        )
                                    }



                                }
                                }
                            )

                            Toast.makeText(requireContext(), R.string.invest_success, Toast.LENGTH_SHORT).show()

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
            }
        }
    }

    inner class InvestAdapter(private val companies: List<Company>, mContext: Context): RecyclerView.Adapter<InvestHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_invest, parent, false)
            return InvestHolder(view)
        }

        override fun getItemCount(): Int {
            return companies.size
        }

        override fun onBindViewHolder(holder: InvestHolder, position: Int) {
            val company = companies[position]
            holder.bind(company, mContext)
        }
    }

}