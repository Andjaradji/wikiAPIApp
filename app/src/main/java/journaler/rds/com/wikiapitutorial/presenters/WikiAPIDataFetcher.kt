package journaler.rds.com.wikiapitutorial.presenters

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import journaler.rds.com.wikiapitutorial.views.HomeActivity
import org.jetbrains.anko.longToast

class WikiAPIDataFetcher (val view:SubjectView, val context:Context) {


    val wikiAPIService by lazy{
        WikiAPIService.create()
    }

    var disposable:Disposable?=null

    // https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=Indonesia


    fun requestData(userInput:String?){
        HomeActivity.idlingResourceCounter = "Not Idle"
        disposable = wikiAPIService.getEndPoint("query", "json", "search",userInput )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.showSubjectList(it.query.search,it.query.searchInfo.totalHits)
                    HomeActivity.idlingResourceCounter = "Idle"
                },{
                    context.longToast(it.message.toString())
                })
    }

}