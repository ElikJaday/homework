package dev.elvir.homeworkforcinemood.model

data class UserSearchResponseModel(
    var incomplete_results: Boolean,
    var items: List<User>,
    var total_count: Int
)