package ph.gcash.cadet.denselross.serrano.sample

import android.app.Application

class GCashApplication : Application() {
    companion object {
        const val BROADCASTLOADDATA = "ph.gcash.api.broadcast.LOADDATA"
        const val BROADCASTLOADIMAGE= "ph.gcash.api.broadcast.LOADIMAGEACTION"
    }

    override fun onCreate() {
        super.onCreate()
    }
}