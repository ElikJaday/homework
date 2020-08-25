package dev.elvir.user_search_list_impl.ui

import dev.elvir.core.addTo
import dev.elvir.core.base.AndroidDisposable
import dev.elvir.core.ioToMain
import dev.elvir.user_search_list_impl.data.Api
import retrofit2.Retrofit
import javax.inject.Inject

class UserSearchPresenter @Inject constructor(
    private val httpClient: Retrofit
) : UserSearchContract.Presenter {

    private var view: UserSearchContract.View? = null
    private val subscriptions = AndroidDisposable()

    override fun searchUserByLogin(inputText: String?) {
        inputText?.let { inputText ->
            httpClient.create(Api::class.java)
                .getSearch(inputText)
                .ioToMain()
                .subscribe(
                    { view?.showFindUsers(it.items) },
                    Throwable::printStackTrace
                )
                .addTo(subscriptions)

        }
    }

    override fun attach(view: UserSearchContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

}