package com.example.roomdb

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.gson.Gson
import java.util.concurrent.TimeUnit


class MainActivity : ComponentActivity() {
    val repo:UserRepository by lazy {
        UserRepository(this)
    }
    var increse=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainactivity)
        val constraint = Constraints.Builder()
            //.setRequiresDeviceIdle(true)
            //.setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            //.setRequiresBatteryNotLow(true)
            //.setRequiresStorageNotLow(true)
            .build()
        val request1= PeriodicWorkRequest.Builder(MyWork::class.java, 15, TimeUnit.MINUTES)
        //val request = OneTimeWorkRequestBuilder<MyWork>()
            .setConstraints(constraint)
            //.setInputData(data)
            .build()
            //.build()

        val button = findViewById<View>(R.id.clickIt) as Button
        button.setOnClickListener {

           val hashMap = HashMap<String, String>()

            hashMap["DG_KEY_APP_ID"] = "DG_VAL_APP_ID_PHARMA"
            hashMap["DG_KEY_APP_VERSION"] = "BuildConfig.VERSION_NAME_APP"
            hashMap["DG_KEY_CHANNEL_ID"] = "DG_VAL_CHANNEL_ID"
            val hashMap1 = HashMap<String, String>()
            hashMap1["DG_KEY_COMP_TYPE"] = "DG_VAL_COMP_TYPE"
            hashMap1["DG_KEY_DEVICE_MODEL"] = "Build.MODEL"
            hashMap1["DG_KEY_EVENT_TIME"] = "com.jio.jioutils.utils.DataGrinchUtilHelper.getEventTime()"
            hashMap1["DG_KEY_LANG"] = "DG_VAL_LANG"
            val gson = Gson()
            val json = gson.toJson(hashMap)
            val json1 = gson.toJson(hashMap1)
            println(" jahur log "+json +json1)
            //val systemUsers: MutableList<String?> = mutableListOf()
            var supplierNames = ArrayList<String>()
            supplierNames.add(json)
            supplierNames.add(json1)
            repo.insertUser(Users(supplierNames))

            println(" jahur log1 "+repo.getAllUsers())
        }
        val button2=findViewById<View>(R.id.clickIt1)
        button2.setOnClickListener {
            println("Jahur--"+repo.getAllUsers().toString())
            WorkManager.getInstance(this).enqueue(request1)

        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request1.id)
            .observe(this, Observer {

                val status: String = it.state.name
                Toast.makeText(this,status, Toast.LENGTH_SHORT).show()
            })

        //WorkManager.getInstance(this).cancelWorkById(request1.id)

    }

    }



