package journaler.rds.com.wikiapitutorial.views.ui

import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.views.SubjectFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class SubjectFragmentUI:AnkoComponent<SubjectFragment> {
    override fun createView(ui: AnkoContext<SubjectFragment>): View = with(ui) {

        verticalLayout {
            lparams(matchParent, wrapContent)

            textView {
                id = R.id.tvHitCount
                text ="Text Hit Count"
                textSize = 16f
                gravity = Gravity.CENTER_HORIZONTAL
            }.lparams(matchParent, wrapContent)

            recyclerView {
                id = R.id.rvQueryListID
                layoutManager = LinearLayoutManager(ctx)
            }.lparams(matchParent, wrapContent)

        }
    }
}