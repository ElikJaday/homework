package dev.elvir.user_search_list_impl.data

import dev.elvir.user_detail_screen_api.model.UserSearchResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    fun getSearch(@Query("q") search: String): Single<UserSearchResponseModel>

}