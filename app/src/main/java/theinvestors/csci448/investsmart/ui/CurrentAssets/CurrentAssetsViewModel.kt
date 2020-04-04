package theinvestors.csci448.investsmart.ui.CurrentAssets

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import theinvestors.csci448.investsmart.data.asset.Asset
import theinvestors.csci448.investsmart.data.asset.AssetRepository


class CurrentAssetsViewModel(private val assetsRepository: AssetRepository): ViewModel() {

    fun addAsset(asset: Asset){
        assetsRepository.addAsset(asset)
    }

    fun getAssets(email: String):  LiveData<Asset?>{
        return assetsRepository.getAssets(email)
    }

}