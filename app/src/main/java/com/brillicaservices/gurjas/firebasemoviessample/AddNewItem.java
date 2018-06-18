package com.brillicaservices.gurjas.firebasemoviessample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.brillicaservices.gurjas.firebasemoviessample.database.DatabaseHelper;
import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;

import java.sql.Array;
import java.util.Collections;

public class AddNewItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button save;
    Button cancel;

    EditText mtitle;
    EditText myear;
    RatingBar mratings;
    EditText mdescription;

    DatabaseHelper db;

    int image;


    Integer imagesArray[] = new Integer[]{R.drawable.avengers_infinity_war,
            R.drawable.avengers_infinity_war_imax_poster,
            R.drawable.dark_knight,
            R.drawable.deadpool,
            R.drawable.fast_furious_7,
            R.drawable.fight_club,
            R.drawable.godfather,
            R.drawable.hancock,
            R.drawable.harry_potter,
            R.drawable.inception,
            R.drawable.into_the_wild,
            R.drawable.iron_man_3,
            R.drawable.pursuit_of_happiness,
            R.drawable.john_wick,
            R.drawable.lion_king,
            R.drawable.rocky,
            R.drawable.shawshank_redemption,
            R.drawable.wanted};

    Spinner category, movieThumbnail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);

        movieThumbnail = findViewById(R.id.item_image);
        ArrayAdapter<Integer> movieThumbnailAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, imagesArray);
        movieThumbnail.setAdapter(movieThumbnailAdapter);

        movieThumbnail.setPrompt("Select Image");

        movieThumbnail.setOnItemSelectedListener(this);

        mtitle=findViewById(R.id.item_title);
        myear=findViewById(R.id.release_year);
        mratings=findViewById(R.id.item_rating);
        mdescription=findViewById(R.id.item_description);
        save=findViewById(R.id.save_item);
        cancel=findViewById(R.id.cancel_item);


        db=new DatabaseHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=mtitle.getText().toString();
                int year=Integer.parseInt(myear.getText().toString());
                int rating=mratings.getNumStars();
                String description=mdescription.getText().toString();
                db.addNewMovie(new MoviesModelView(title,image,year,rating,description));
                Toast.makeText(getApplicationContext(), "Movies data saved successfully", Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddNewItem.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        image=imagesArray[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
