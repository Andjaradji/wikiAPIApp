package journaler.rds.com.wikiapitutorial.presenters

import journaler.rds.com.wikiapitutorial.models.FavWikiSubject

interface SubjectView {
    fun showSubjectList (data:List<FavWikiSubject>, count:Int)
}