package dev.elvir.homeworkforcinemood

import dev.elvir.homeworkforcinemood.model.UserSearchResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("search/users")
    fun getSearch(@Query("q") search: String): Single<UserSearchResponseModel>

}