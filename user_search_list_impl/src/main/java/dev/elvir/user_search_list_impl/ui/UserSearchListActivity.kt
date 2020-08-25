package dev.elvir.user_search_list_impl.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dev.elvir.core.addTo
import dev.elvir.core.afterTextChanged
import dev.elvir.core.base.AndroidDisposable
import dev.elvir.core.ioToMain
import dev.elvir.user_detail_screen_api.UserDetailRouter
import dev.elvir.user_detail_screen_api.model.User
import dev.elvir.user_search_list_impl.ComponentManager
import dev.elvir.user_search_list_impl.R
import dev.elvir.user_search_list_impl.adapter.SearchUserAdapter
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.user_search_list_layout.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class UserSearchListActivity : AppCompatActivity(), UserSearchContract.View {

    private val subject = BehaviorSubject.create<String>()
    private val textInput = subject.toFlowable(BackpressureStrategy.LATEST)
    private val disposable = AndroidDisposable()

    @Inject
    lateinit var presenter: UserSearchPresenter

    @Inject
    lateinit var userDetailRouter: UserDetailRouter


    override fun onCreate(savedInstanceState: Bundle?) {
        ComponentManager.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_search_list_layout)
        presenter.attach(this)
        rv_user_search?.layoutManager = LinearLayoutManager(this)
        setUpListener()
    }

    private fun setUpListener() {
        et_user_search.afterTextChanged {
            subject.onNext(it)
        }
        textInput.debounce(300, TimeUnit.MILLISECONDS)
            .ioToMain()
            .subscribe { inputText -> presenter.searchUserByLogin(inputText) }
            .addTo(disposable)
    }

    override fun showFindUsers(list: List<User>) {
        rv_user_search.adapter =
            SearchUserAdapter(this, list,userDetailRouter)
    }


}