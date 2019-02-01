package journaler.rds.com.wikiapitutorial.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.models.FavWikiSubject
import journaler.rds.com.wikiapitutorial.models.favSubjectDB
import journaler.rds.com.wikiapitutorial.presenters.WikiSubjectAdapter
import journaler.rds.com.wikiapitutorial.views.ui.FavoriteFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find

class FavoriteFragment : Fragment() {
    private lateinit var rvFavorite:RecyclerView

    private var subjectList:MutableList<FavWikiSubject> = mutableListOf()
    private lateinit var wikiAdapter:WikiSubjectAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvFavorite = find(R.id.rvFavSubjectID)

        wikiAdapter = WikiSubjectAdapter(subjectList,ctx)
        rvFavorite.adapter = wikiAdapter

        rvFavorite.layoutManager = LinearLayoutManager(ctx)

        subjectList.clear()
        showFavSubjectList()
    }

    private fun showFavSubjectList() {
        context?.favSubjectDB?.use {
            val result = select(FavWikiSubject.TABLE_FAVORITE_SUBJECT)
            val favorite = result.parseList(classParser<FavWikiSubject>())
            subjectList.addAll(favorite)
            wikiAdapter.notifyDataSetChanged()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return FavoriteFragmentUI().createView(AnkoContext.create(ctx, this))
    }


    override fun onResume() {
        subjectList.clear()
        showFavSubjectList()
        super.onResume()
    }


}
