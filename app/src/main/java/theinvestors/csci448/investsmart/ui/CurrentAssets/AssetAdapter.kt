package theinvestors.csci448.investsmart.ui.CurrentAssets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.AssetModel

class AssetAdapter(private val assets: List<AssetModel>, private val clickListener: (AssetModel) -> Unit): PagedListAdapter<AssetModel, AssetHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AssetModel>() {
            override fun areItemsTheSame(oldItem: AssetModel, newItem: AssetModel) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: AssetModel, newItem: AssetModel) =
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return assets.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_asset, parent, false)
        return AssetHolder(view)
    }

    override fun onBindViewHolder(holder: AssetHolder, position: Int) {
        val asset = assets[position]
        holder.bind(asset, clickListener)
    }


}