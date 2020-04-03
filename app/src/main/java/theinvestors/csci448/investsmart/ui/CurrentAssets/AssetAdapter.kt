package theinvestors.csci448.investsmart.ui.CurrentAssets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.Asset

class AssetAdapter(private val assets: List<Asset>, private val clickListener: (Asset) -> Unit): PagedListAdapter<Asset, AssetHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Asset>() {
            override fun areItemsTheSame(oldItem: Asset, newItem: Asset) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Asset, newItem: Asset) =
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