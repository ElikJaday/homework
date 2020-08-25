package dev.elvir.user_detail_screen_api.model

data class UserSearchResponseModel(
    var incomplete_results: Boolean,
    var items: List<User>,
    var total_count: Int
)