package company.tap.cardbusiness

import android.annotation.SuppressLint
import android.content.Context
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
       // viewModel.processEvent(InitEvent,this)
        viewModel.processEvent(MockEvent, this)

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
            println("response data ${data}")
            it.data?.merchant?.logo?.let { it1 -> concatText(it1) }
        }
        data?.mockAPIResponse.let {
            println("mock response data ${data}")
            it?.merchant?.name?.let { it1 -> concatText(it1) }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun concatText(newText: String) {
        result.text =  result.text.toString() + "\n\n" + newText
    }
}
