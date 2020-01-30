package com.example.test.service

import com.example.test.model.ResponsRespons
import com.example.test.model.Response
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("users?since=135")
    fun getData(@Query("login") login: String,
                @Query("id") id: String,
                @Query("node_id") node_id: String,
                @Query("avatar_url") avatar_url: String
//                @Query("gavatar_url") gavatar_url: String,
//                @Query("url") url: String,
//                @Query("html_url") html_url: String,
//                @Query("followers_url") followers_url: String,
//                @Query("following_url") following_url: String,
//                @Query("gists_url") gists_url: String,
//                @Query("starred_url") starred_url: String,
//                @Query("subscriptions_url") subscriptions_url: String,
//                @Query("organizations_url") organizations_url: String,
//                @Query("repos_url") repos_url: String,
//                @Query("events_url") events_url: String,
//                @Query("received_events_url") received_events_url: String,
//                @Query("type") type: String,
//                @Query("site_admin") site_admin: Boolean
    ): Single<ResponseBody>

    @GET("search/users")
    fun getItem(
//        @Query ("page") page: Int,
        @Query ("q") q: String
    ): Single<Response>

    @POST("mobile-customer-api/statusAplikasi/getApplicationList")
    fun getCustomerApplicationList(
        @Body body: JsonObject
    ): Single<ResponsRespons>


}



