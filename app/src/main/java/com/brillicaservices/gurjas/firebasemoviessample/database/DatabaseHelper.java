package com.brillicaservices.gurjas.firebasemoviessample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brillicaservices.gurjas.firebasemoviessample.R;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //always extend SQLiteOpenHelper
    List<MoviesModelView> moviesList = new ArrayList<>();
    /*
     * Database details*/
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MovieSample";

    /*
     *table details saved in variables so that we if later we make changes we have to change only here*/
    private static final String TABLE_NAME = "TableMovieSample";
    private static final String MOVIE_ID = "MovieId";
    private static final String TITLE = "TitleOfMovie";
    private static final String IMAGE = "Image";
    private static final String YEAR = "YearOfRelease";
    private static final String RATINGS = "Rating";
    private static final String DESCRIPTION = "Description";

    /*
     * Table structure*/
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            TITLE + " TEXT, " + IMAGE + " INTEGER, " + YEAR + " INTEGER, " +
            RATINGS + " INTEGER, " + DESCRIPTION + " TEXT ); ";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }
    public long addNewMovie(MoviesModelView movieModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();//to write into database

        ContentValues contentValues = new ContentValues();//ContentValues object which works on key value pair

        contentValues.put(TITLE, movieModel.getTitle());//set value to key : value
        contentValues.put(IMAGE, movieModel.getImage());
        contentValues.put(YEAR, movieModel.getReleaseYear());
        contentValues.put(RATINGS, movieModel.getRating());
        contentValues.put(DESCRIPTION, movieModel.getDescription());

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);//null?

        sqLiteDatabase.close();

        return id;
    }
    public List<MoviesModelView> allMoviesDetails() {

        String selectQuery = " SELECT * FROM " + TABLE_NAME ;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //use rawquery when getting data for all students
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);//in place of null we can write where clause

        /*moveToFirst(): to move cursor to first object*/
        if (cursor.moveToFirst()){
            do {
                MoviesModelView movieModel = new MoviesModelView();
                movieModel.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                movieModel.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE)));
                movieModel.setReleaseYear(cursor.getInt(cursor.getColumnIndex(YEAR)));
                movieModel.setRating(cursor.getInt(cursor.getColumnIndex(RATINGS)));
                movieModel.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));

                moviesList.add(movieModel);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();

        return  moviesList;
    }


}
