package journaler.rds.com.wikiapitutorial.presenters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.models.FavWikiSubject
import journaler.rds.com.wikiapitutorial.views.DetailActivity
import journaler.rds.com.wikiapitutorial.views.ui.RecyclerViewItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.intentFor

class WikiSubjectAdapter(private val list:List<FavWikiSubject>, private val context: Context):
RecyclerView.Adapter<WikiSubjectAdapter.FavSubjectHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): WikiSubjectAdapter.FavSubjectHolder {
        return FavSubjectHolder(RecyclerViewItemUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(p0: WikiSubjectAdapter.FavSubjectHolder, p1: Int) {
        p0.bindSubject(list[p1])
    }


   inner class FavSubjectHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val subjectTitle:TextView = itemView.find(R.id.tvQUeryTitleID)
        private val subjectURL:TextView = itemView.find(R.id.tvQueryLinkID)
       private val baseLink:String = "https://en.wikipedia.org/?curid"

         fun bindSubject(subject:FavWikiSubject){
             subjectTitle.text = subject.title
             subjectURL.text = "$baseLink=${subject.pageid}"

             itemView.setOnClickListener {
                 context.startActivity(context.intentFor<DetailActivity>("Wiki" to subject))
             }


         }

    }
}
