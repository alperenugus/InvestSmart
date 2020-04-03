package theinvestors.csci448.investsmart.ui.FavoriteCompanies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.favcomp.FavComp

class FavCompAdapter(private val favComps: List<FavComp>, private val clickListener: (FavComp) -> Unit): PagedListAdapter<FavComp, FavCompHolder>(FavCompAdapter.DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavComp>() {
            override fun areItemsTheSame(oldItem: FavComp, newItem: FavComp) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: FavComp, newItem: FavComp) =
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return favComps.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavCompHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_fav_comp, parent, false)
        return FavCompHolder(view)
    }

    override fun onBindViewHolder(holder: FavCompHolder, position: Int) {
        val favComp = favComps[position]
        holder.bind(favComp, clickListener)
    }
}