package theinvestors.csci448.investsmart.ui.FavoriteCompanies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import theinvestors.csci448.investsmart.data.favcomp.FavComp
import theinvestors.csci448.investsmart.data.favcomp.FavCompRepository


class FavCompViewModel(private val favCompRepository: FavCompRepository): ViewModel() {

    fun addFavComp(favComp: FavComp){
        favCompRepository.addFavComp(favComp)
    }

    fun getFavComps(email: String):  LiveData<FavComp?>{
        return favCompRepository.getFavComps(email)
    }

}