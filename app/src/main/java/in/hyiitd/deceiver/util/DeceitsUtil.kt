package `in`.hyiitd.deceiver.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DeceitsUtil {
    @JvmStatic val DECEIT_MODE_NONE = -1
    @JvmStatic val DECEIT_MODE_NORMAL = 0
    @JvmStatic val DECEIT_MODE_DECENT = 1
    @JvmStatic val DECEIT_MODE_DESPERATE = 2

    @JvmStatic var currentModeOption = -1
    @JvmStatic var currentPermissionSelected = ""

    @JvmStatic var supportedModes = listOf<String>("normal", "decent", "desperate")

    @JvmStatic fun updateDeceitValuesByMode(mode: Int) {

    }
}