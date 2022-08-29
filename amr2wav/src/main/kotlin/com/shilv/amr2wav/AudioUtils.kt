package com.shilv.amr2wav

import android.util.Log
import com.arthenica.mobileffmpeg.Config
import com.arthenica.mobileffmpeg.FFmpeg

/**
 * Author：xuhao
 * Email: xuhaozv@163.com
 * description:音频格式转换
 */
object AudioUtils {
    fun arm2wav(amr: String, wav: String): Int {
        val amr2wavCommand = "-acodec libopencore_amrnb -i $amr $wav"
        val rc = FFmpeg.execute(amr2wavCommand)
        when (rc) {
            Config.RETURN_CODE_SUCCESS -> {
                Log.i(Config.TAG, "命令执行成功")
            }
            Config.RETURN_CODE_CANCEL -> {
                Log.i(Config.TAG, "用户取消了命令")
            }
            else -> {
                Log.i(Config.TAG, String.format("命令执行失败, 返回值=%d", rc))
            }
        }
        return rc
    }
}