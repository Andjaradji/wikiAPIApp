package journaler.rds.com.wikiapitutorial.views.ui

import android.view.Gravity
import android.view.View
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.views.DetailActivity
import org.jetbrains.anko.*

class DetailActivityUI:AnkoComponent<DetailActivity> {
    override fun createView(ui: AnkoContext<DetailActivity>): View = with (ui) {
        verticalLayout {
            lparams(matchParent, matchParent)

            textView {
                id = R.id.tvSubjectTopicID
                text =context.getString(R.string.subject_topic)
                textSize = 30f
                gravity = Gravity.CENTER_HORIZONTAL
            }.lparams(matchParent, wrapContent)

            textView {
                id = R.id.tvLinkUrlID
                text =context.getString(R.string.link_url)
                textSize = 20f
                gravity = Gravity.CENTER_HORIZONTAL
            }.lparams(matchParent, wrapContent)

            textView {
                id = R.id.tvWordCountID
                text =context.getString(R.string.word_count)
                textSize = 20f
                gravity = Gravity.CENTER_HORIZONTAL
            }.lparams(matchParent, wrapContent)

        }
    }
}