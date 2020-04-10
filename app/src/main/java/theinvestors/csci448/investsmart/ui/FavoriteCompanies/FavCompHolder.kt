package theinvestors.csci448.investsmart.ui.FavoriteCompanies

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.favcomp.FavComp

class FavCompHolder(val view: View) : RecyclerView.ViewHolder(view) {

    lateinit var favComp: FavComp

    var favCompNameTextView: TextView = itemView.findViewById(R.id.list_item_fav_comp_name_text)

    fun bind(favComp: FavComp, clickListener: (FavComp) -> Unit){
        this.favComp = favComp
        itemView.setOnClickListener { clickListener(this.favComp) }

        favCompNameTextView.text = favComp.company_name.toString()
    }

}