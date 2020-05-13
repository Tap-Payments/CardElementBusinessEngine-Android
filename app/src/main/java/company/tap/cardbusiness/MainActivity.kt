package company.tap.cardbusiness

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import company.tap.cardbusinesskit.CardViewEvent
import company.tap.cardbusinesskit.CardViewEvent.*
import company.tap.cardbusinesskit.CardViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: CardViewModel by viewModels()
        viewModel.liveData.observe(this, Observer {
            result.text = concatText(result.text, it)
        })
        viewModel.processEvent(InitEvent)
    }

    private fun concatText(oldText: CharSequence, newText: String): String {
        return oldText.toString() + "\n\n" + newText
    }
}
