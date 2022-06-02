package me.ztiany.arch.delegate.example

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.base.delegate.simpl.DelegateActivity
import com.android.sdk.mediaselector.common.ResultListener
import com.android.sdk.mediaselector.custom.newMediaSelector
import com.permissionx.guolindev.PermissionX
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : DelegateActivity() {

    private val mediaSelector by lazy {
        newMediaSelector(this, object : ResultListener {
            override fun onTakeSuccess(result: List<Uri>) {
                result.forEach {
                    Timber.e(it.toString())
                }
                showResult(result)
            }
        })
    }

    private fun showResult(results: List<Uri>) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putParcelableArrayListExtra(KEY, ArrayList(results))
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(DebugTree())
        askNecessaryPermissions()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Support
    ///////////////////////////////////////////////////////////////////////////
    private fun askNecessaryPermissions() {
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_MEDIA_LOCATION)
        } else {
            arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        PermissionX.init(this)
            .permissions(*permissions)
            .request { allGranted, _, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                    supportFinishAfterTransition()
                }
            }
    }

    fun selectPhotos(view: View) {
        mediaSelector.takePicture().needMediaLocation().count(9).needGif().start()
    }

}