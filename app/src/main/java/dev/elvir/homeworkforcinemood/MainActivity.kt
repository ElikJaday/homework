package dev.elvir.homeworkforcinemood

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private val subject = BehaviorSubject.create<String>()
    private val textInput = subject.toFlowable(BackpressureStrategy.LATEST)
    private val disposable = CompositeDisposable()
    private val httpClient: Retrofit = NetworkModule.provideRetrofit(
        NetworkModule.providesGson(),
        NetworkModule.provideOkHttpClientDefault(
            NetworkModule.provideHttpLoggingInterceptor(),
            NetworkModule.getTimeOut()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_user_search?.layoutManager = LinearLayoutManager(this)
        setUpListener()
    }

    private fun setUpListener() {
        et_user_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                subject.onNext(et_user_search.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        disposable.add(
            textInput.debounce(300, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { inputText ->
                    httpClient.create(Api::class.java)
                        .getSearch(inputText)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { userSearchResponseModel ->
                                rv_user_search.adapter =
                                    SearchUserAdapter(this, userSearchResponseModel.items)
                            },
                            {
                                it.printStackTrace()
                            }
                        )
                }
        )
    }


}