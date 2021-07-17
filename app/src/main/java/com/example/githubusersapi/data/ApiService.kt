package com.example.githubusersapi.data


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = " https://api.github.com/"
//How to build the API by Retrofit? : URL,Converterfactory

/**
 * TODO(01)
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 *
 * implementation "com.squareup.moshi:moshi:1.8.0"
 * implementation "com.squareup.moshi:moshi-kotlin:1.8.0"
 * implementation "com.squareup.retrofit2:converter-moshi:2.9.0"
 */
private val moshi  = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * TODO(02)
 * Build the OkHttpClient object that Retrofit will be using, making sure to add the logging interceptor for
 * check response. Setup level to Level.BODY that we will know all information about http connect.
 *
 * implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
 */
private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()

/**
 * TODO(03)
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 *
 * And using an OkHttpClient with our OkHttpClient object.
 *
 * implementation "com.squareup.retrofit2:retrofit:2.9.0"
 */
private val retrofit  = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()
//addCallAdapterFactory(CoroutineCallAdapterFactory()) -> replace the call and get property with a coroutine deferred
//addConverterFactory-> Retrofit call String ot other primitive type
//baseURL -> specify the route web address
//build -> to create the retrofit object
//this is a retrofit builder
//interface XX -> how to talk our web server using HTTP request. retrofit create an object than implements all of the method and interface have all of method.
// Retrofit Annotated API interface -> Interface

/**
 * TODO(04)
 * A public interface that exposes the [getMarketingHots] method
 */
interface UsersApiApiService {
    /**
     * TODO(05)
     * Returns a Coroutine with [suspend] function in a Coroutine scope.
     *
     * Make sure our BASE_URL includes api path and api_version.
     *
     * The @GET annotation indicates that the "marketing/hots" endpoint will be requested with the GET
     * HTTP method
     */
//    Call: create a retrofit service
    @GET("users")
    suspend fun getUsers(): List<User>

}
/**
 * TODO(06)
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
//Below the MarsApiService interface, define a public object called MarsApi to initialize the Retrofit service.
object UsersApi {
    val retrofitService : UsersApiApiService by lazy { retrofit.create(UsersApiApiService::class.java) }
}
