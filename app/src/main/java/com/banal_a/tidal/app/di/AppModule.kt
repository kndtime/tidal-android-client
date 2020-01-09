package com.banal_a.tidal.app.di

import android.content.Context
import com.banal_a.tidal.BuildConfig
import com.banal_a.tidal.app.App
import com.banal_a.tidal.app.network.TidalService
import com.banal_a.tidal.app.utils.RemoteContract
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    /**
     * Provides the Post service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the Post service implementation.
     */
    @Provides
    @Reusable
    internal fun providePostApi(retrofit: Retrofit): TidalService {
        return retrofit.create(TidalService::class.java)
    }

    @Provides
    @Reusable
    internal fun provideHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
            request.header(RemoteContract.HEADER_TOKEN, "9f1d8880748d48d68e5a10f3be1e60d5")
            chain.proceed(request.build())
        }
        httpClient.interceptors().add(interceptor)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }

    @Provides
    @Reusable
    fun provideMoshi(): Moshi {
        val moshi =  Moshi.Builder()
            .build()
        //val keaksAdapter : JsonAdapter<ArrayList<Beat>> = moshi.adapter(keaks)
        return moshi
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Singleton
    @Provides
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .client(provideHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    operator fun invoke(): AppModule {
        return this
    }
}