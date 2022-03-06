package saa.com.codetest2.di

import android.app.Application
import android.util.Log

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import saa.com.codetest2.datasource.network.retrofit.ServiceApi
import saa.com.codetest2.util.URL
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    /*
     * The method returns the Gson object
     * */
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }


    /*
     * The method returns the Cache object
     * */
    @Provides
    @Singleton
    fun provideCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024.toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    /*
     * The method returns the Okhttp object
     * */
    @Provides
    @Singleton
    fun provideOkhttpClient(
        cache: Cache
    ): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.cache(cache)
        httpClient.addInterceptor(logging)
        //httpClient.addNetworkInterceptor(new RequestInterceptor());
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        return httpClient.build()
    }


    /* @Provides
    @Singleton
    LiveDataCallAdapterFactory provideLiveDataCallAdapterFactory(){
        return new LiveDataCallAdapterFactory();
    }*/
    /*
     * The method returns the Retrofit object
     * */
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            //.addCallAdapterFactory(LiveDataCallAdapterFactory.create())
            //.addConverterFactory(LiveDataResponseBodyConverterFactory.create())
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit): ServiceApi {
        if (retrofit == null) {
            Log.d("NetworkModule", "retrofit is null")
        }

        return retrofit.create(ServiceApi::class.java)
    }


//    @Provides
//    @Singleton
//    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(URL)
//            .client(client)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
//        return OkHttpClient.Builder().writeTimeout(1, TimeUnit.MINUTES)
//            .readTimeout(1, TimeUnit.MINUTES)
//            .callTimeout(1, TimeUnit.MINUTES)
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
//
//        return HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//
//
//        }
//    }
//
//        @Provides
//        @Singleton
//        fun providesGson(): Gson {
//            return GsonBuilder().create()
//        }


}