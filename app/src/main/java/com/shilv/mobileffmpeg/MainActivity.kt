package com.shilv.mobileffmpeg

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.FFmpeg
import com.blankj.utilcode.util.PathUtils
import com.permissionx.guolindev.PermissionX

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
        val tvVersion: TextView = findViewById(R.id.tv_version)

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
                    Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                    val amr = PathUtils.getExternalDownloadsPath() + "/jys.amr"
                    val wav = PathUtils.getExternalDownloadsPath() + "/jys.wav"
                    val wav4 = PathUtils.getExternalDownloadsPath() + "/jyssss.wav"
                    val wav2 = PathUtils.getExternalDownloadsPath() + "/iattest.wav"
                    val mp3 = PathUtils.getExternalDownloadsPath() + "/wav2mp3.mp3"
                    val mp32 = PathUtils.getExternalDownloadsPath() + "/amr2mp3.mp3"
                    val wav3 = PathUtils.getExternalDownloadsPath() + "/mp3.wav"
                    Log.i(Config.TAG, "$amr -- $wav")

                    //ffmpeg -acodec libamr_nb -i shenhuxi.amr amr2wav.wav
                    val amr2mav = "-acodec libopencore_amrnb -i $amr $wav4"
//                    val amr2mav = "ffmpeg -i xxx.mp3 -acodec pcm_s16le -f s16le -ac 1 -ar 16000 xxx.pcm"
//                    val amr2mav = "-i $wav2 -f mp3 -acodec libmp3lame -y $mp3"
//                    val amr2mav = "-i $amr $mp32"
//                    val amr2mav = "-i $mp32 -f wav $wav3"
                    val rc2 = FFmpeg.execute(amr2mav)

                    if (rc2 == Config.RETURN_CODE_SUCCESS) {
                        Log.i(Config.TAG, "命令执行成功")
                    } else if (rc == Config.RETURN_CODE_CANCEL) {
                        Log.i(Config.TAG, "用户取消了命令")
                    } else {
                        Log.i(Config.TAG, String.format("命令执行失败, 返回值=%d", rc2))
                    }
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