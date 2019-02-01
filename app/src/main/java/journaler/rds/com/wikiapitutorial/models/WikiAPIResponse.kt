package journaler.rds.com.wikiapitutorial.models

import android.app.DownloadManager
import com.google.gson.annotations.SerializedName

object Model {
    data class Result(@SerializedName("query") val query:Query)
    data class Query(@SerializedName("searchinfo") val searchInfo: SearchInfo,
                     @SerializedName("search") val search:List<FavWikiSubject>)
    data class SearchInfo(@SerializedName("totalhits") val totalHits:Int)


}