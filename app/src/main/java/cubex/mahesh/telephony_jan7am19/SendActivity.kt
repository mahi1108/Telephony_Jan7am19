package cubex.mahesh.telephony_jan7am19

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_send.*

class SendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send)

      //  var mno = intent.getStringExtra("mno")
        tv1.text = "Message Send To : $mno"
    }
}
