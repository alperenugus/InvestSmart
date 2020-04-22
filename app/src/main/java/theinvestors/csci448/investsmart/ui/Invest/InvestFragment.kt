package theinvestors.csci448.investsmart.ui.Invest

import android.content.Context
import android.graphics.drawable.ClipDrawable.HORIZONTAL
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.api.CompanyValue
import theinvestors.csci448.investsmart.data.asset.Asset
import java.util.*
import kotlin.collections.ArrayList


private const val logTag: String = "InvestFragment"

class InvestFragment: Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InvestAdapter
    private lateinit var companies: MutableList<Company>
    private lateinit var factory: InvestViewModelFactory
    private lateinit var mContext: Context

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


        fun bind(company: Company, context: Context){
            this.company = company
            companyNameTextView.text = company.companyName
            companyValue.text = company.companyValue.current
            companyO.text = company.companyValue.open
            companyH.text = company.companyValue.high
            companyL.text = company.companyValue.low
            companyPc.text = company.companyValue.pc

            investBtn.setOnClickListener {
                Log.d(logTag, "Invest Clicked.")
                Log.d(logTag, "${companyNameTextView.text}.")

                var asset: Asset = Asset(UUID.randomUUID(), MainActivity.email, companyNameTextView.text.toString(), 5)
                Log.d(logTag, context.toString())
                factory = InvestViewModelFactory(context)
                investViewModel.addAsset(asset)

                //view.findNavController().navigate(R.id.buyFragment)
            }
        }
    }

    inner class InvestAdapter(private val companies: List<Company>, context: Context): RecyclerView.Adapter<InvestHolder>() {

        var mContext = context

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