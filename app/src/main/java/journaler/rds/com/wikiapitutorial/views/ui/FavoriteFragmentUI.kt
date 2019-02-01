package journaler.rds.com.wikiapitutorial.views.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.views.FavoriteFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FavoriteFragmentUI:AnkoComponent<FavoriteFragment> {
    override fun createView(ui: AnkoContext<FavoriteFragment>): View = with(ui) {
        verticalLayout {

            lparams(matchParent, wrapContent)

            recyclerView {
                id = R.id.rvFavSubjectID
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(matchParent, wrapContent)

        }
    }
}