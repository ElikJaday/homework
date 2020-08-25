package dev.elvir.user_search_list_impl.ui

import dev.elvir.core.base.BaseMvp
import dev.elvir.user_detail_screen_api.model.User

interface UserSearchContract {

    interface View : BaseMvp.View {
        fun showFindUsers(lsit: List<User>)
    }

    interface Presenter : BaseMvp.Presenter<View> {
        fun searchUserByLogin(login: String?)
    }

}