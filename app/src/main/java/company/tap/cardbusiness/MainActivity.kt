package company.tap.cardbusiness

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import company.tap.cardbusinesskit.CardViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: CardViewModel by viewModels()
        viewModel.liveData.observe(this, Observer {
            result_text.text = result_text.text.toString() + "\n" + it
        })
        viewModel.getData(this)
    }
}
