package jp.hacks.smartbread.ui.main.health.model

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import jp.hacks.smartbread.ui.main.health.HealthPermissionRepository

class HealthPermissionRepositoryImpl : HealthPermissionRepository {

    companion object {
        const val REQUEST_OAUTH_REQUEST_CODE = 0
    }

    override fun requestPermission(context: Context) {
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT)
            .addDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY)
            .build()

        if (!GoogleSignIn.hasPermissions(
                GoogleSignIn.getLastSignedInAccount(context),
                fitnessOptions
            )
        ) {
            /**/
        } else {
            //readData()
        }
    }
}