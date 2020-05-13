package company.tap.cardbusiness

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import company.tap.cardbusinesskit.mvi.CardViewEvent.*
import company.tap.cardbusinesskit.mvi.CardViewModel
import company.tap.cardbusinesskit.mvi.CardViewState
import company.tap.cardbusinesskit.data.Resource
import company.tap.cardbusinesskit.data.Resource.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel: CardViewModel by viewModels()
        viewModel.liveData.observe(this, Observer { consumeResponse(it) })
        viewModel.processEvent(InitEvent)
    }

    private fun consumeResponse(response: Resource<CardViewState>) {
        when (response) {
            is Loading -> concatText("Loading")
            is Finished -> concatText("Finished")
            is Error -> response.message?.let { concatText(it) }
            is Success -> renderView(response.data)
        }
    }

    private fun renderView(data: CardViewState?) {
        data?.initResponse?.let {
            concatText(it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun concatText(newText: String) {
        result.text =  result.text.toString() + "\n\n" + newText
    }
}
