package dev.elvir.user_search_list_impl.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.elvir.user_detail_screen_api.UserDetailRouter
import dev.elvir.user_detail_screen_api.model.User
import dev.elvir.user_search_list_impl.R
import dev.elvir.user_search_list_impl.ui.UserSearchListActivity
import kotlinx.android.synthetic.main.list_item_user_search_layout.view.*

class SearchUserAdapter(
    private val activity: UserSearchListActivity,
    private val userList: List<User>,
    private val userDetailRouter: UserDetailRouter
) : RecyclerView.Adapter<SearchUserAdapter.SearchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_user_search_layout, parent, false)
        )

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(userList[position], activity, userDetailRouter)

    class SearchViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {

        private var userName: TextView? = null
        private var userProfileLink: TextView? = null
        private var userRepositoryLink: TextView? = null
        private var userPhoto: ImageView? = null
        private var rootView: CardView? = null

        init {
            userName = view.tv_user_name
            userProfileLink = view.tv_user_profile_link
            userRepositoryLink = view.tv_user_repos_link
            userPhoto = view.iv_user_photo
            rootView = view.vg_root
        }

        fun bind(user: User, activity: UserSearchListActivity, userDetailRouter: UserDetailRouter) {
            userPhoto?.let {
                Glide.with(view.context)
                    .load(user.avatar_url)
                    .into(it)
            }
            rootView?.setOnClickListener {
                userDetailRouter.startUserDetailScreen(
                    activity,
                    user,
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        userPhoto!!,
                        ViewCompat.getTransitionName(userPhoto!!)!!
                    ).toBundle()!!
                )
            }
            userName?.text = user.login
            userProfileLink?.text = user.url
            userRepositoryLink?.text = user.repos_url
        }

    }
}
