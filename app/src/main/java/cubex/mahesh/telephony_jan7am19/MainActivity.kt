package cubex.mahesh.telephony_jan7am19

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var sms_permission : Boolean = false
    var call_permission : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      var sms_status =   ContextCompat.checkSelfPermission(
            this@MainActivity,
          Manifest.permission.SEND_SMS)

        var call_status =   ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.CALL_PHONE)

        if(sms_status == PackageManager.PERMISSION_GRANTED)
        {
            sms_permission = true
        }

        if(call_status == PackageManager.PERMISSION_GRANTED)
        {
            call_permission = true
        }

        if(sms_status != PackageManager.PERMISSION_GRANTED ||
                call_status != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions()
        }else if(sms_status != PackageManager.PERMISSION_GRANTED)
        {
            requestSmsPermission()
        }else if(call_status != PackageManager.PERMISSION_GRANTED)
        {
            requestCallPermission()
        }

        sendSMS.setOnClickListener {
            if(sms_permission){

                var sManager = SmsManager.getDefault()
                sManager.sendTextMessage(et1.text.toString(),
                    null,et2.text.toString(),null,
                    null)

            }else{
                requestSmsPermission()
            }
        }
        call.setOnClickListener {
                if(call_permission){

                    var i = Intent( )
                    i.action = Intent.ACTION_CALL
                    i.data = Uri.parse("tel:"+et1.text.toString())
                    startActivity(i)

                }else{
                    requestCallPermission()
                }
        }
    } // onCreate( )

    fun  requestPermissions( ){
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.SEND_SMS,
                Manifest.permission.CALL_PHONE),
            1)
    }

    fun requestSmsPermission( ){
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.SEND_SMS),
            2)
    }

    fun requestCallPermission( ){
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.CALL_PHONE),
            3)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
            permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode,
            permissions, grantResults)

        when(requestCode){
            1->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    sms_permission = true
                }
                if(grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    call_permission = true
                }
            }
            2->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    sms_permission = true
                }
            }
            3->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    call_permission = true
                }
            }
        }

    }

}
