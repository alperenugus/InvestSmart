package theinvestors.csci448.investsmart.ui.Invest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.data.asset.Asset
import theinvestors.csci448.investsmart.data.asset.AssetRepository

class InvestViewModel(private val assetsRepository: AssetRepository): ViewModel() {

    val currentAssets: LiveData<List<Asset>> = assetsRepository.getAssets(MainActivity.email)

    fun addAsset(asset: Asset){
        assetsRepository.addAsset(asset)
    }

}