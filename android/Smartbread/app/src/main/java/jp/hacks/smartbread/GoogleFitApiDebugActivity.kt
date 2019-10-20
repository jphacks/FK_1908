package jp.hacks.smartbread

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.renderscript.Element
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessActivities
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Session
import com.google.android.gms.fitness.request.SessionReadRequest
import com.google.android.gms.fitness.result.SessionReadResponse
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit

/*
* うまくいくまで綺麗にかかない波なのでめっちゃ汚いです。
* */
class GoogleFitApiDebugActivity : AppCompatActivity() {

    private val fitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY, FitnessOptions.ACCESS_READ)
            .build()
    }

    private val googleSignInAccount by lazy {
        GoogleSignIn.getAccountForExtension(this, fitnessOptions)
    }

    private val identifier = "identifire_id"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_fit_api_debug)
        createGoogleFitApi()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result.RESULT_CANCELEDが帰ってくる。
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE) {
                accessGoogleFit()
            }
        }
    }

    private fun createGoogleFitApi() {
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY, FitnessOptions.ACCESS_READ)
            .build()

        if (!GoogleSignIn.hasPermissions(
                GoogleSignIn.getLastSignedInAccount(this),
                fitnessOptions
            )
        ) {
            GoogleSignIn.requestPermissions(
                this,
                GOOGLE_FIT_PERMISSIONS_REQUEST_CODE,
                GoogleSignIn.getLastSignedInAccount(this),
                fitnessOptions
            )
        } else {
            accessGoogleFit()
        }
    }

    // ここまでたどり着いてすらない。
    private fun accessGoogleFit() {
        val cal = Calendar.getInstance()
        cal.time = Date()
        val endTime = cal.timeInMillis
        cal.add(Calendar.DAY_OF_YEAR, -10)
        val startTime = cal.timeInMillis

        val session = Session.Builder()
            .setName("get_sleep_time")
            .setDescription("Long run around Shoreline Park")
            .setIdentifier(identifier)
            .setActivity(FitnessActivities.SLEEP_AWAKE)
            .setStartTime(startTime, TimeUnit.MINUTES)
            .build()

        Fitness.getSessionsClient(this, googleSignInAccount)
            .startSession(session)

        val readRequest = SessionReadRequest.Builder()
            .read(DataType.TYPE_SPEED)
            .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS)
            .setSessionName("get_sleep_time")
            .build()

        Fitness.getSessionsClient(this, GoogleSignIn.getLastSignedInAccount(this)!!)
            .readSession(readRequest)
            .addOnSuccessListener { it: SessionReadResponse ->
                val sessions = it.sessions

                for (session in sessions) {
                    // TODO
                    // Process the data sets for this session
                    val dataSets = it.getDataSet(session)
                    for (dataSet in dataSets) {
                        // TODO
                        Log.d(LOG_TAG, dataSet.toString())
                    }
                }
            }
            .addOnFailureListener { e -> Log.e(LOG_TAG, "onFailure()", e) }
            .addOnCompleteListener { Log.d(LOG_TAG, "onComplete()") }
    }

    private fun stopSession() {
        Fitness.getSessionsClient(this, googleSignInAccount)
            .stopSession(identifier)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopSession()
    }

    companion object {
        const val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 2
        const val LOG_TAG = "hoge main"
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, GoogleFitApiDebugActivity::class.java))
        }
    }
}
