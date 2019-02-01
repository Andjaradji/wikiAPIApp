package journaler.rds.com.wikiapitutorial.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.views.ui.HomeActivityUI
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class HomeActivity : AppCompatActivity() {

    companion object {
      lateinit var idlingResourceCounter:String
    }

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        HomeActivityUI().setContentView(this)

        bottomNavigationView = find(R.id.bottomNavigationID)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottomSubjectsID ->{
                    loadSubjectFragment(savedInstanceState)
                    supportActionBar?.title = getString(R.string.wiki_subject)
                }

                R.id.bottomFavoritesID ->{
                    loadFavoritesFragment(savedInstanceState)
                    supportActionBar?.title = getString(R.string.favorite_subject)
                }
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.bottomSubjectsID

    }

    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainerID,FavoriteFragment(),FavoriteFragment::class.simpleName)
                    .commit()
        }
    }

    private fun loadSubjectFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainContainerID,SubjectFragment(),SubjectFragment::class.simpleName)
                    .commit()
        }

    }
}
