package theinvestors.csci448.investsmart.ui.FavoriteCompanies

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import theinvestors.csci448.investsmart.data.favcomp.FavCompRepository

class FavCompViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(FavCompRepository::class.java)
            .newInstance(FavCompRepository.getInstance(context))}

}