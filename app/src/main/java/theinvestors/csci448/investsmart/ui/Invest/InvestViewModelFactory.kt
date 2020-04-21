package theinvestors.csci448.investsmart.ui.Invest

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import theinvestors.csci448.investsmart.data.asset.AssetRepository

class InvestViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(AssetRepository::class.java)
            .newInstance(AssetRepository.getInstance(context))}

}