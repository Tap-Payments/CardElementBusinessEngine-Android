package company.tap.cardbusiness

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import company.tap.cardbusinesskit.CardViewModel
import company.tap.tapnetworkkit.connection.NetworkApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NetworkApp.initNetwork(
            this,
            "sk_test_kovrMB0mupFJXfNZWx6Etg5y",
            "company.tap.goSellSDKExample",
            "https://api.tap.company/v2/"
        )

        val viewModel: CardViewModel by viewModels()
        viewModel.liveData.observe(this, Observer {
            result_text.text = result_text.text.toString() + "\n\n" + it
        })
        viewModel.getData()
    }
}
