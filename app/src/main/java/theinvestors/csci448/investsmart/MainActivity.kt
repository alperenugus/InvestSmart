package theinvestors.csci448.investsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import theinvestors.csci448.investsmart.ui.HomeScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = HomeScreenFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }
}
