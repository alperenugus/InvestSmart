package theinvestors.csci448.investsmart

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.navigation.NavigationView
import theinvestors.csci448.investsmart.api.CompanyHistoricValue
import theinvestors.csci448.investsmart.api.CompanyValue
import theinvestors.csci448.investsmart.api.StockService
import theinvestors.csci448.investsmart.ui.LoginFragment
import theinvestors.csci448.investsmart.util.NetworkUtil

private const val logTag: String = "MainActivity"
class MainActivity : AppCompatActivity(), LoginFragment.LoginInterface{

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var stockService: StockService
    private var networkUtil: NetworkUtil = NetworkUtil()

    companion object{
        var InvestSmartEmail = "investsmartmines@gmail.com"
        var signedIn: Boolean = false;
        var name: String = "investsmart"
        var email: String = "investsmartmines@gmail.com"
        var totalMoney: Double = 1000.0
        lateinit var companyNames: MutableList<String>
        lateinit var companyValues: MutableMap<String, CompanyValue>
        lateinit var companyHistoricValues: MutableMap<String, CompanyHistoricValue>
        lateinit var mGoogleSignInClient: GoogleSignInClient
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.currentAssetsFragment, R.id.investFragment,
            R.id.resetAccountFragment, R.id.aboutUsFragment), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        stockService = StockService()
        companyNames = ArrayList()
        companyValues = HashMap()
        companyHistoricValues = HashMap()

        companyNames.add("AAPL")
        companyNames.add("AMZN")
        companyNames.add("IBM")
        companyNames.add("MSFT")
        companyNames.add("PYPL")

        if(networkUtil.isNetworkAvailableAndConnected(this)){
            for (i in 0 until companyNames.size){
                getValues(companyNames[i])
                getHistoricValue(companyNames[i])
            }
        }
        else{
            Toast.makeText(this, R.string.internet, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_contact -> {

                if(signedIn)
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.contactFragment)
                else Toast.makeText(applicationContext, "You should sign in!", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_signout -> {

                if(!signedIn){
                    Toast.makeText(this, R.string.not_signed_in, Toast.LENGTH_SHORT).show()
                }
                else{
                    mGoogleSignInClient.signOut()
                    signedIn = false
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.loginFragment)
                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun LoginLockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        Log.d(logTag, "LoginLockDrawer() called")
    }

    override fun LoginUnlockDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        Log.d(logTag, "LoginUnlockDrawer() called")
    }

    fun getValues(companyName: String){

        var companyValue = stockService.getValue(companyName)

        companyValue.observe(
            this,
            Observer { result -> result.let {
                companyValues.put(companyName, result)
            }
            }
        )
    }

    fun getHistoricValue(companyName: String){
        var historicValue = stockService.getHistoricValue(companyName)

        historicValue.observe(
            this,
            Observer { result -> result.let {
                companyHistoricValues.put(companyName, result)
                //Log.d(logTag, companyName + result.current.toString() + result.timestamp.toString())
            } }
        )

    }

}
