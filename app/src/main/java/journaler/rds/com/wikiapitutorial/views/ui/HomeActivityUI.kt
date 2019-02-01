package journaler.rds.com.wikiapitutorial.views.ui

import android.view.View
import android.widget.LinearLayout
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.views.HomeActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.bottomNavigationView

class HomeActivityUI:AnkoComponent<HomeActivity> {
    override fun createView(ui: AnkoContext<HomeActivity>): View =  with(ui) {
        relativeLayout {
            frameLayout {
                id = R.id.mainContainerID

            }.lparams(width = matchParent, height = matchParent) {
                above(R.id.bottomLayoutID)
            }
            linearLayout {
                id = R.id.bottomLayoutID
                orientation = LinearLayout.VERTICAL
                view {
                    backgroundResource = R.drawable.shadow
                }.lparams(width = matchParent, height = dip(4))

                bottomNavigationView {
                    id = R.id.bottomNavigationID
                    itemBackgroundResource = android.R.color.white
                    itemTextColor = resources.getColorStateList(R.color.colorAccent)
                    itemIconTintList = resources.getColorStateList(R.color.colorAccent)

                    menu.apply {
                        inflateMenu(R.menu.bottom_navigation_menu)
                    }
//
//                    inflateMenu(R.menu.bottom_navigation_menu)

                }.lparams(width = matchParent)
            }.lparams(width = matchParent) {
                alignParentBottom()
            }
        }
    }
}