package com.ibm.kotlin.mfp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.worklight.wlclient.api.WLClient
import com.worklight.wlclient.api.WLFailResponse
import com.worklight.wlclient.api.WLResponse
import com.worklight.wlclient.api.WLResponseListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WLClient.createInstance(this);
        val wlResourceResponse = object : WLResponseListener {
            override fun onSuccess(wlResponse: WLResponse?) {
               runOnUiThread(object :Runnable {
                   override fun run() {
                      Toast.makeText(this@MainActivity, wlResponse?.responseText, Toast.LENGTH_LONG).show();
                   }
               })
            }

            override fun onFailure(wlFailResponse: WLFailResponse?) {
                runOnUiThread(object :Runnable {
                    override fun run() {
                        Toast.makeText(this@MainActivity, wlFailResponse?.errorMsg, Toast.LENGTH_LONG).show();
                    }
                })
            }
        }

        WLClient.getInstance().connect(wlResourceResponse);
    }
}
