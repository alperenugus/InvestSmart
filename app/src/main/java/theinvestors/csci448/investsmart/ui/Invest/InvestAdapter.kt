package theinvestors.csci448.investsmart.ui.Invest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R

class InvestAdapter(private val companies: List<Company>): RecyclerView.Adapter<InvestHolder>() {

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
        holder.bind(company)
    }
}