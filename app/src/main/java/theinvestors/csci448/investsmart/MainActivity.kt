package theinvestors.csci448.investsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import theinvestors.csci448.investsmart.ui.*

class MainActivity : AppCompatActivity(), AboutUsFragment.CallBacks, AssetsFragment.CallBacks, CurrentAssetsFragment.CallBacks,
FavoriteCompaniesFragment.CallBacks, HomeScreenFragment.CallBacks, InvestFragment.CallBacks, PredictionFragment.CallBacks,
ResetAccountFragment.CallBacks, ResetEmailFragment.CallBacks, ResetPasswordFragment.CallBacks, SettingsFragment.CallBacks{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = HomeScreenFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun assetsOnCurrentAssetsClicked() {

        val fragment = CurrentAssetsFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun assetsOnFavCompaniesClicked() {

        val fragment = FavoriteCompaniesFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun assetsOnInvestClicked() {

        val fragment = InvestFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun assetsOnSellClicked() {

        val fragment = InvestFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun homeScreenAssetsClicked() {

        val fragment = AssetsFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun homeScreenFavCompaniesClicked() {

        val fragment = FavoriteCompaniesFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun homeScreenSettingsClicked() {

        val fragment = SettingsFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun settingsChangePasswordClicked() {

        val fragment = ResetPasswordFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun settingsChangeEmailClicked() {

        val fragment = ResetEmailFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun settingsResetAccountClicked() {

        val fragment = ResetAccountFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    override fun settingsAboutUsClicked() {

        val fragment = AboutUsFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }
}
