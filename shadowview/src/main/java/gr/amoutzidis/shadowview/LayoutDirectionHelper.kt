package gr.amoutzidis.shadowview

import android.content.Context
import android.content.res.Configuration
import android.view.View

object LayoutDirectionHelper {


    fun isRTL(context: Context): Boolean{

        val config: Configuration = context.resources.configuration

        return  config.layoutDirection === View.LAYOUT_DIRECTION_RTL
    }

}