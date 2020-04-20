package theinvestors.csci448.investsmart.ui.Invest

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R

private const val logTag: String = "InvestHolder"

class InvestHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var company: Company

    var companyNameTextView: TextView = itemView.findViewById(R.id.invest_company_name)
    var companyValue: TextView = itemView.findViewById(R.id.invest_company_value)
    var companyO: TextView = itemView.findViewById(R.id.invest_company_o)
    var companyH: TextView = itemView.findViewById(R.id.invest_company_h)
    var companyL: TextView = itemView.findViewById(R.id.invest_company_l)
    var companyPc: TextView = itemView.findViewById(R.id.invest_company_pc)
    var investBtn: Button = itemView.findViewById(R.id.list_item_invest_button)




    fun bind(company: Company){
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
            //view.findNavController().navigate(R.id.buyFragment)
        }



    }

}