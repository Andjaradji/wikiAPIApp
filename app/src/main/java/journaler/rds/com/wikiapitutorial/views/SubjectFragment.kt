package journaler.rds.com.wikiapitutorial.views


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.models.FavWikiSubject
import journaler.rds.com.wikiapitutorial.presenters.SubjectView
import journaler.rds.com.wikiapitutorial.presenters.WikiAPIDataFetcher
import journaler.rds.com.wikiapitutorial.presenters.WikiSubjectAdapter
import journaler.rds.com.wikiapitutorial.views.ui.SubjectFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.find

class SubjectFragment : Fragment(), SubjectView {

    private lateinit var txtHitCount:TextView
    private lateinit var rvSubject:RecyclerView
    private lateinit var adapter:WikiSubjectAdapter

    private var listSubjectFav:MutableList<FavWikiSubject> = mutableListOf()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        txtHitCount = find(R.id.tvHitCount)
        rvSubject = find(R.id.rvQueryListID)

        adapter = WikiSubjectAdapter(listSubjectFav,ctx)
        rvSubject.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return SubjectFragmentUI().createView(AnkoContext.Companion.create(ctx,this ))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchTopic = menu?.findItem(R.id.searchMenuID)

        val searchView =  searchTopic?.actionView as SearchView

        searchView.queryHint = "Search Topic"

        searchView.isSubmitButtonEnabled = true

        val wikiFetcher = WikiAPIDataFetcher(this,ctx)

        searchView.setOnQueryTextListener(object:SubjectView, SearchView.OnQueryTextListener{
            override fun showSubjectList(data: List<FavWikiSubject>, count: Int) {
                listSubjectFav.clear()
                listSubjectFav.addAll(data)
                adapter.notifyDataSetChanged()
                txtHitCount.text = "Total Hit Count: $count"
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != ""){
                    wikiFetcher.requestData(query)
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != ""){
                    wikiFetcher.requestData(newText)
                }

                return true
            }


        })



        super.onCreateOptionsMenu(menu, inflater)
    }


   override fun showSubjectList(data: List<FavWikiSubject>, count: Int) {
        listSubjectFav.clear()
        listSubjectFav.addAll(data)
        adapter.notifyDataSetChanged()
        txtHitCount.text = "Total Hit Count: $count"
    }


}
