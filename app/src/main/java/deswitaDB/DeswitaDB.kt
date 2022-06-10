package deswitaDB

import android.provider.BaseColumns

object deswitaDB {
    class UserReviewTable : BaseColumns {
        companion object {
            const val TABLE_USER_REVIEW = "user_review"
            const val COLUMN_ID = "id"
            const val COLUMN_CONTENT = "content"
            const val COLUMN_RATING = "rating"
            const val COLUMN_DESTINATION_ID = "destination_id"
            const val COLUMN_CREATED_AT = "created_at"
            const val COLUMN_UPDATED_AT = "updated_at"
            const val COLUMN_USER_ID = "user_id"
        }
    }

    class EventTable : BaseColumns {
        companion object {
            const val TABLE_EVENT = "event"
            const val COLUMN_ID = "id"
            const val COLUMN_IMAGE = "image"
            const val COLUMN_DATE = "date"
            const val COLUMN_NAME = "name"
            const val COLUMN_LOCATION = "location"
            const val COLUMN_DESCRIPTION = "description"
        }
    }

    class UserTable : BaseColumns {
        companion object {
            const val TABLE_USER = "user"
            const val COLUMN_ID = "id"
            const val COLUMN_COMMENT = "comment"
            const val COLUMN_RATING = "rating"
            const val COLUMN_DESTINATION_ID = "id_destination"
            const val COLUMN_CREATED_AT = "created_at"
            const val COLUMN_UPDATED_AT = "updated_at"
        }
    }
}