package journaler.rds.com.wikiapitutorial.models

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FavDBOpenHelper(ctx: Context):ManagedSQLiteOpenHelper(ctx,"Favorite.db",null, 1) {

    companion object {
        private var instance:FavDBOpenHelper?=null

        @Synchronized
        fun getInstance(ctx:Context):FavDBOpenHelper{
            if (instance == null){
                instance = FavDBOpenHelper(ctx.applicationContext)
            }

            return instance as FavDBOpenHelper
        }
    }


    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavWikiSubject.TABLE_FAVORITE_SUBJECT,true,
                FavWikiSubject.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavWikiSubject.SUBJECT_TITLE to TEXT,
                FavWikiSubject.SUBJECT_PAGE_ID to INTEGER + UNIQUE,
                FavWikiSubject.SUBJECT_WORDCOUNT to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(FavWikiSubject.TABLE_FAVORITE_SUBJECT, true)
    }



}