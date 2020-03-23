package theinvestors.csci448.investsmart.ui.FavoriteCompanies

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.FavCompModel

class FavCompHolder(val view: View) : RecyclerView.ViewHolder(view) {

    lateinit var favCompModel: FavCompModel

    var favCompNameTextView: TextView = itemView.findViewById(R.id.list_item_fav_comp_name)

    fun bind(favCompModel: FavCompModel, clickListener: (FavCompModel) -> Unit){
        this.favCompModel = favCompModel
        itemView.setOnClickListener { clickListener(this.favCompModel) }

        favCompNameTextView.text = favCompModel.company_name.toString()
    }

}