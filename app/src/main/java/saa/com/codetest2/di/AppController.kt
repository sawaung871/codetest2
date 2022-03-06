package saa.com.codetest2.di


import android.app.Application


class AppController : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this) //pass application object to application method()
            .build() //build AppComponent


    }




}