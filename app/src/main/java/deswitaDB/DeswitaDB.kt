package deswitaDB

import android.provider.BaseColumns

object deswitaDB {
    class UserReviewTable : BaseColumns {
        companion object {
            val TABLE_USER_REVIEW = "user_review"
            val COLUMN_ID = "id"
            val COLUMN_CONTENT = "content"
            val COLUMN_RATING = "rating"
            val COLUMN_DESTINATION_ID = "destination_id"
            val COLUMN_CREATED_AT = "created_at"
            val COLUMN_UPDATED_AT = "updated_at"
            val COLUMN_USER_ID = "user_id"
        }
    }

    class UserTable : BaseColumns {
        companion object {
            val TABLE_USER = "user"
            val COLUMN_ID = "id"
            val COLUMN_COMMENT = "comment"
            val COLUMN_RATING = "rating"
            val COLUMN_DESTINATION_ID = "id_destination"
            val COLUMN_CREATED_AT = "created_at"
            val COLUMN_UPDATED_AT = "updated_at"
        }
    }
}