package journaler.rds.com.wikiapitutorial.views

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import journaler.rds.com.wikiapitutorial.R
import journaler.rds.com.wikiapitutorial.models.FavWikiSubject
import journaler.rds.com.wikiapitutorial.models.favSubjectDB
import journaler.rds.com.wikiapitutorial.views.ui.DetailActivityUI
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class DetailActivity : AppCompatActivity() {
    private lateinit var txtSubjectTitle:TextView
    private lateinit var txtSubjectURL:TextView
    private lateinit var txtSubjectWordCount:TextView

    private lateinit var subject: FavWikiSubject

    private val baseURL:String ="https://en.wikipedia.org/?curid"

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var subjectID:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DetailActivityUI().setContentView(this)

        txtSubjectTitle = find(R.id.tvSubjectTopicID)
        txtSubjectURL = find(R.id.tvLinkUrlID)
        txtSubjectWordCount = find(R.id.tvWordCountID)

        subject = intent.getParcelableExtra("Wiki")

        txtSubjectTitle.text = subject.title
        txtSubjectURL.text = "$baseURL=${subject.pageid}"
        txtSubjectWordCount.text = "${subject.wordcount}"

        subjectID = "${subject.pageid}"

        favoriteState()

    }

    private fun favoriteState(){

        favSubjectDB.use {
            val result = select(FavWikiSubject.TABLE_FAVORITE_SUBJECT)
                    .whereArgs("(SUBJECT_PAGE_ID={id})",
                            "id" to subjectID)
            val favorite = result.parseList(classParser<FavWikiSubject>())

            if (!favorite.isEmpty()) isFavorite = true
        }
    }





    private fun addToFavorite(){
        try {
            favSubjectDB.use {
                insert(FavWikiSubject.TABLE_FAVORITE_SUBJECT,
                        FavWikiSubject.SUBJECT_TITLE to subject.title,
                        FavWikiSubject.SUBJECT_PAGE_ID to subject.pageid,
                        FavWikiSubject.SUBJECT_WORDCOUNT to subject.wordcount)
            }

            snackbar(txtSubjectURL, "Added to Favorite").show()

        } catch (e:SQLiteConstraintException){
            snackbar(txtSubjectTitle, e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite (){
        try {
            favSubjectDB.use {
                delete(FavWikiSubject.TABLE_FAVORITE_SUBJECT, "SUBJECT_PAGE_ID={id}",
                        "id" to subjectID )
            }
            snackbar(txtSubjectTitle,"Remove from Favorite").show()
        }catch (e:SQLiteConstraintException){
            snackbar(txtSubjectWordCount,e.localizedMessage).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.detail_menu, menu)

        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            R.id.addToFavID->{
                if (isFavorite)removeFromFavorite()else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else->super.onOptionsItemSelected(item)
        }
    }



    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}
