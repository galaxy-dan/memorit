package com.galaxy.memorit.hilt

import android.app.Application
import android.content.SharedPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.orhanobut.logger.Logger
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import com.galaxy.data.datasource.local.SharedPreference
import com.galaxy.data.datasource.remote.LoginDatasource
import com.galaxy.data.network.Interceptor.AuthInterceptor
import com.galaxy.data.repository.LoginRepositoryImpl
import com.galaxy.data.service.login.LoginService
import com.galaxy.domain.repository.LoginRepository
import com.galaxy.memorit.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Singleton
    @Provides
    fun provideLoginRepository(
        loginDatasource: LoginDatasource,
        sharedPreference: SharedPreference
    ): LoginRepository {
        return LoginRepositoryImpl(loginDatasource, sharedPreference)
    }

    @Singleton
    @Provides
    fun provideLoginDataSource(service: LoginService): LoginDatasource {
        return LoginDatasource(service)
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return application.getSharedPreferences("spref", 0)
    }


    @Singleton
    @Provides
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkhttpCLient(authInterceptor: AuthInterceptor): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor {
            try {
                JSONObject(it)
                Logger.t("Interceptor").json(it)
            } catch (error: JSONException) {
            Logger.t("Interceptor").i(it)
            }
        }.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(sharedPreference: SharedPreference): AuthInterceptor {
        return AuthInterceptor(sharedPreference)
    }
}
//            Logger.t("OKHTTP").i(it)
//    }.setLevel(HttpLoggingInterceptor.Level.BODY)
//    private val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

