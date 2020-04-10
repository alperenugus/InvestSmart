package theinvestors.csci448.investsmart.ui.FavoriteCompanies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import theinvestors.csci448.investsmart.MainActivity
import theinvestors.csci448.investsmart.data.favcomp.FavComp
import theinvestors.csci448.investsmart.data.favcomp.FavCompRepository


class FavCompViewModel(private val favCompRepository: FavCompRepository): ViewModel() {

    val favComps: LiveData<List<FavComp>> = favCompRepository.getFavComps(MainActivity.email)

    fun addFavComp(favComp: FavComp){
        favCompRepository.addFavComp(favComp)
    }

}