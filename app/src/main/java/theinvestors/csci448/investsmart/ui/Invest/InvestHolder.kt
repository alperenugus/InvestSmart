package theinvestors.csci448.investsmart.ui.Invest

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R

class InvestHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var company: Company

    var companyNameTextView: TextView = itemView.findViewById(R.id.invest_company_name)
    var companyValue: TextView = itemView.findViewById(R.id.invest_company_value)

    fun bind(company: Company){
        this.company = company
        companyNameTextView.text = company.companyName
        companyValue.text = company.companyValue.toString()
    }

}