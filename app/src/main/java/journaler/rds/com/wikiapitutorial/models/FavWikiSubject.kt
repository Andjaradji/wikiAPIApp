package journaler.rds.com.wikiapitutorial.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavWikiSubject (
    var id:Long?,
    var title:String?,
    var pageid:Int?,
    var wordcount:Int?
):Parcelable {
    companion object {
        const val TABLE_FAVORITE_SUBJECT: String = "TABLE_FAVORITE_SUBJECT"
        const val ID:String ="ID_"
        const val SUBJECT_TITLE:String = "SUBJECT_TITLE"
        const val SUBJECT_PAGE_ID:String ="SUBJECT_PAGE_ID"
        const val SUBJECT_WORDCOUNT:String="SUBJECT_WORDCOUNT"
    }

}