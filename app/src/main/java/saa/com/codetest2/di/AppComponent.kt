package saa.com.codetest2.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import saa.com.codetest2.ui.MovieList
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class])

interface AppComponent {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder //at runtime bind (Application) to AppComponent

        fun build(): AppComponent
    }

     fun inject(activity: MovieList)

}