package journaler.rds.com.wikiapitutorial.views.ui

import android.view.View
import android.view.ViewGroup
import journaler.rds.com.wikiapitutorial.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class RecyclerViewItemUI:AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with((ui)) {
        cardView {
            lparams(matchParent, wrapContent)
            cardElevation = dip (6).toFloat()

            verticalLayout {
                padding = dip(8)
                lparams(matchParent, matchParent)

                textView {
                    id = R.id.tvQUeryTitleID
                    text = "Indonesia"
                    textSize = 18f
                }.lparams(matchParent, wrapContent) { bottomMargin = dip(5) }

                textView {
                    id = R.id.tvQueryLinkID
                    text = "https://blablablabla"
                    textSize = 16f
                }.lparams(matchParent, wrapContent)

            }
        }
    }
}