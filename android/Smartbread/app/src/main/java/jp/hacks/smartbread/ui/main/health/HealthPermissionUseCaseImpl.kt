package jp.hacks.smartbread.ui.main.health

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType

class HealthPermissionUseCaseImpl : HealthPermissionUseCase {
    companion object{
        private const val REQUEST_OAUTH_REQUEST_CODE = "AIzaSyB6f4AzYdspA2eGeJEhK8k9u4HajRJ7qcA"
    }

    override fun requestPermission(activity: AppCompatActivity) {
        val fitnessOptions = FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_ACTIVITY_SUMMARY)
            .addDataType(DataType.TYPE_ACTIVITY_SEGMENT)
            .build()
        if (!GoogleSignIn.hasPermissions(
                GoogleSignIn.getLastSignedInAccount(activity),
                fitnessOptions
            )
        ) {
            GoogleSignIn.requestPermissions(
                activity,
                0,
                GoogleSignIn.getLastSignedInAccount(activity),
                fitnessOptions
            )
        } else {

        }
    }
}