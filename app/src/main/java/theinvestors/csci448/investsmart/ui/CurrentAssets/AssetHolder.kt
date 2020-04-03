package theinvestors.csci448.investsmart.ui.CurrentAssets

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import theinvestors.csci448.investsmart.R
import theinvestors.csci448.investsmart.data.asset.Asset

class AssetHolder(val view: View) : RecyclerView.ViewHolder(view) {
    lateinit var asset: Asset
    var companyNameTextView: TextView = itemView.findViewById(R.id.list_item_asset_company_name)
    var ownedShareTextView: TextView = itemView.findViewById(R.id.list_item_asset_owned_share)

    fun bind(asset: Asset, clickListener: (Asset) -> Unit){
        this.asset = asset
        itemView.setOnClickListener { clickListener(this.asset) }

        companyNameTextView.text = asset.company.toString()
        ownedShareTextView.text = asset.owned_shares.toString()
    }
}