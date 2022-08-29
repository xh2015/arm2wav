package com.shilv.mobileffmpeg

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.FFmpeg
import com.blankj.utilcode.util.PathUtils
import com.permissionx.guolindev.PermissionX
import com.shilv.amr2wav.AudioUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rc = FFmpeg.execute(" -version")

        if (rc == Config.RETURN_CODE_SUCCESS) {
            Log.i(Config.TAG, "命令执行成功")
        } else if (rc == Config.RETURN_CODE_CANCEL) {
            Log.i(Config.TAG, "用户取消了命令")
        } else {
            Log.i(Config.TAG, String.format("命令执行失败, 返回值=%d", rc))
        }
        val btnChange: Button = findViewById(R.id.btn_change)
        btnChange.setOnClickListener {
            PermissionX.init(this)
                .permissions(
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.CAMERA,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG)
                            .show()
                        val amr = PathUtils.getExternalDownloadsPath() + "/jys.amr"
                        val wav = PathUtils.getExternalDownloadsPath() + "/jysTemp.wav"
                        Log.i(Config.TAG, "$amr -- $wav")
                        AudioUtils.arm2wav(amr, wav);
                    } else {
                        Toast.makeText(
                            this,
                            "These permissions are denied: $deniedList",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}