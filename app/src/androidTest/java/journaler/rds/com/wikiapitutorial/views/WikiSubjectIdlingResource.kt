package journaler.rds.com.wikiapitutorial.views

import android.support.test.espresso.IdlingResource

class WikiSubjectIdlingResource:IdlingResource {
    override fun getName(): String {
        return "${WikiSubjectIdlingResource::class.java.simpleName}"
    }

    override fun isIdleNow(): Boolean {
        val idle = HomeActivity.idlingResourceCounter == "Idle"

        if (idle){
            callback?.onTransitionToIdle()
        }

        return idle
    }

    private var callback: IdlingResource.ResourceCallback? = null

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

}