package com.example.cbproject;

//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//import com.example.cbproject.adapter.ReviewAdapter;
//import com.example.cbproject.adapter.TrailerAdapter;
//import com.example.cbproject.api.Client;
//import com.example.cbproject.api.Service;
//import com.example.cbproject.data.FavoriteContract;
//import com.example.cbproject.data.FavoriteDbHelper;
//import com.example.cbproject.model.*;
//import com.github.ivbaranov.mfb.MaterialFavoriteButton;
//import com.google.android.material.appbar.CollapsingToolbarLayout;
//import com.google.android.material.snackbar.Snackbar;
//import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cbproject.adapter.TrailerAdapter;
import com.example.cbproject.api.Client;
import com.example.cbproject.api.Service;
import com.example.cbproject.data.FavoriteDbHelper;
import com.example.cbproject.model.Movie;
import com.example.cbproject.model.Trailer;
import com.example.cbproject.model.TrailerResponse;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.snackbar.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class Detail3Activity extends AppCompatActivity {
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;
    private RecyclerView recyclerView;
    private TrailerAdapter adapter;
    private List<Trailer> trailerList;
    String THE_MOVIE_DB_API_TOKEN="e5a3515630b2f6c99098494f03054864";
    private FavoriteDbHelper favoriteDbHelper;
    private Movie favorite;
    private final AppCompatActivity activity = Detail3Activity.this;

    Movie movie;
    String thumbnail, movieName, synopsis, rating, dateOfRelease;
    int movie_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
        nameOfMovie = (TextView) findViewById(R.id.title);
        plotSynopsis = (TextView) findViewById(R.id.plotsynopsis);
        userRating = (TextView) findViewById(R.id.userrating);
        releaseDate = (TextView) findViewById(R.id.releasedate);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movies")){

            movie = getIntent().getParcelableExtra("movies");

            thumbnail = movie.getPosterPath();
            movieName = movie.getOriginalTitle();
            synopsis = movie.getOverview();
            rating = Double.toString(movie.getVoteAverage());
            dateOfRelease = movie.getReleaseDate();
            movie_id = movie.getId();

            String poster = "https://image.tmdb.org/t/p/w500" + thumbnail;

            Glide.with(this)
                    .load(poster)
                    .placeholder(R.drawable.load)
                    .into(imageView);

            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);

        }
        else{
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }

        MaterialFavoriteButton materialFavoriteButtonNice =
                (MaterialFavoriteButton) findViewById(R.id.favorite_button);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        materialFavoriteButtonNice.setOnFavoriteChangeListener(
                new MaterialFavoriteButton.OnFavoriteChangeListener(){
                    @Override
                    public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite){
                        if (favorite){
                            SharedPreferences.Editor editor = getSharedPreferences("com.example.cbproject.Detail3Activity", MODE_PRIVATE).edit();
                            editor.putBoolean("Favorite Added", true);
                            editor.commit();
                            saveFavorite();
                            Snackbar.make(buttonView, "Added to Favorite",
                                    Snackbar.LENGTH_SHORT).show();
                        }else{
                            int movie_id = getIntent().getExtras().getInt("id");
                            favoriteDbHelper = new FavoriteDbHelper(Detail3Activity.this);
                            favoriteDbHelper.deleteFavorite(movie_id);

                            SharedPreferences.Editor editor = getSharedPreferences("com.example.cbroject.Detail3Activity", MODE_PRIVATE).edit();
                            editor.putBoolean("Favorite Removed", true);
                            editor.commit();
                            Snackbar.make(buttonView, "Removed from Favorite",
                                    Snackbar.LENGTH_SHORT).show();
                        }

                    }
                }
        );

        initViews();

    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener(){
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                    isShow = true;
                }else if (isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void initViews(){
        trailerList = new ArrayList<>();
        adapter = new TrailerAdapter(this, trailerList);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        loadJSON();

    }

    private void loadJSON(){

        try{
            if (THE_MOVIE_DB_API_TOKEN.isEmpty()){
                Toast.makeText(getApplicationContext(), "Please obtain your API Key from themoviedb.org", Toast.LENGTH_SHORT).show();
                return;
            }
            Client Client = new Client();
            Service apiService = Client.getClient().create(Service.class);
            Call<TrailerResponse> call = apiService.getMovieTrailer(movie_id,THE_MOVIE_DB_API_TOKEN);
            call.enqueue(new Callback<TrailerResponse>() {
                @Override
                public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                    List<Trailer> trailer = response.body().getResults();
                    recyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), trailer));
                    recyclerView.smoothScrollToPosition(0);
                }

                @Override
                public void onFailure(Call<TrailerResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(Detail3Activity.this, "Error fetching trailer data", Toast.LENGTH_SHORT).show();

                }
            });

        }catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void saveFavorite(){
        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new Movie();

        Double rate = movie.getVoteAverage();


        favorite.setId(movie_id);
        favorite.setOriginalTitle(movieName);
        favorite.setPosterPath(thumbnail);
        favorite.setVoteAverage(rate);
        favorite.setOverview(synopsis);

        favoriteDbHelper.addFavorite(favorite);
    }
}
//public class Detail3Activity extends AppCompatActivity {
//    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
//    ImageView imageView;
//    private RecyclerView recyclerView;
//    private TrailerAdapter adapter;
//    private List<Trailer> trailerList;
//    private FavoriteDbHelper favoriteDbHelper;
//    private Movie favorite;
//    private final AppCompatActivity activity =Detail3Activity.this;
//    private SQLiteDatabase mDb;
//    String THE_MOVIE_DB_API_TOKEN="e5a3515630b2f6c99098494f03054864";
//
//    Movie movie;
//    String thumbnail, movieName, synopsis, rating, dateOfRelease;
//    int movie_id;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        //TODO
//        FavoriteDbHelper dbHelper = new FavoriteDbHelper(this);
//        mDb = dbHelper.getWritableDatabase();
//
//
//        imageView = (ImageView) findViewById(R.id.thumbnail_image_header);
//        // nameOfMovie = (TextView) findViewById(R.id.title);
//        plotSynopsis = (TextView) findViewById(R.id.plotsynopsis);
//        userRating = (TextView) findViewById(R.id.userrating);
//        releaseDate = (TextView) findViewById(R.id.releasedate);
//
//        Intent intentThatStartedThisActivity = getIntent();
//        if (intentThatStartedThisActivity.hasExtra("movies")){
//
//            movie = getIntent().getParcelableExtra("movies");
//
//            thumbnail = movie.getPosterPath();
//            movieName = movie.getOriginalTitle();
//            synopsis = movie.getOverview();
//            rating = Double.toString(movie.getVoteAverage());
//            dateOfRelease = movie.getReleaseDate();
//            movie_id = movie.getId();
//
//            String poster = "https://image.tmdb.org/t/p/w500" + thumbnail;
//
//            Glide.with(this)
//                    .load(poster)
//                    .placeholder(R.drawable.load)
//                    .into(imageView);
//
//            //  nameOfMovie.setText(movieName);
//            plotSynopsis.setText(synopsis);
//            userRating.setText(rating);
//            releaseDate.setText(dateOfRelease);
//
//            //TODO
//            ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar)).setTitle(movieName);
//
//        }else{
//            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
//        }
//
//        MaterialFavoriteButton materialFavoriteButton = (MaterialFavoriteButton) findViewById(R.id.favorite_button);
//
//        if (Exists(movieName)){
//            materialFavoriteButton.setFavorite(true);
//            materialFavoriteButton.setOnFavoriteChangeListener(
//                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                        @Override
//                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                            if (favorite == true) {
//                                saveFavorite();
//                                Snackbar.make(buttonView, "Added to Favorite",
//                                        Snackbar.LENGTH_SHORT).show();
//                            } else {
//                                favoriteDbHelper = new FavoriteDbHelper(Detail3Activity.this);
//                                favoriteDbHelper.deleteFavorite(movie_id);
//                                Snackbar.make(buttonView, "Removed from Favorite",
//                                        Snackbar.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//
//        }else {
//            materialFavoriteButton.setOnFavoriteChangeListener(
//                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
//                        @Override
//                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                            if (favorite == true) {
//                                saveFavorite();
//                                Snackbar.make(buttonView, "Added to Favorite",
//                                        Snackbar.LENGTH_SHORT).show();
//                            } else {
//                                int movie_id = getIntent().getExtras().getInt("id");
//                                favoriteDbHelper = new FavoriteDbHelper(Detail3Activity.this);
//                                favoriteDbHelper.deleteFavorite(movie_id);
//                                Snackbar.make(buttonView, "Removed from Favorite",
//                                        Snackbar.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//
//        }
//
//        initViews();
//
//    }
//
//    public boolean Exists(String searchItem) {
//
//        String[] projection = {
//                FavoriteContract.FavoriteEntry._ID,
//                FavoriteContract.FavoriteEntry.COLUMN_MOVIEID,
//                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
//                FavoriteContract.FavoriteEntry.COLUMN_USERRATING,
//                FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH,
//                FavoriteContract.FavoriteEntry.COLUMN_PLOT_SYNOPSIS
//
//        };
//        String selection = FavoriteContract.FavoriteEntry.COLUMN_TITLE + " =?";
//        String[] selectionArgs = { searchItem };
//        String limit = "1";
//
//        Cursor cursor = mDb.query(FavoriteContract.FavoriteEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null, limit);
//        boolean exists = (cursor.getCount() > 0);
//        cursor.close();
//        return exists;
//    }
//
//
//    private void initViews(){
//        trailerList = new ArrayList<>();
//        adapter = new TrailerAdapter(this, trailerList);
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//
//        loadJSON();
//        loadReview();
//    }
//
//    private void loadJSON(){
//        try{
//            if (THE_MOVIE_DB_API_TOKEN.isEmpty()){
//                Toast.makeText(getApplicationContext(), "Please get your API Key", Toast.LENGTH_SHORT).show();
//                return;
//            }else {
//                Client Client = new Client();
//                Service apiService = Client.getClient().create(Service.class);
//                Call<TrailerResponse> call = apiService.getMovieTrailer(movie_id, THE_MOVIE_DB_API_TOKEN);
//                call.enqueue(new Callback<TrailerResponse>() {
//                    @Override
//                    public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
//                        if (response.isSuccessful()) {
//                            if (response.body() != null) {
//                                List<Trailer> trailer = response.body().getResults();
//                                MultiSnapRecyclerView recyclerView = (MultiSnapRecyclerView) findViewById(R.id.recycler_view1);
//                                LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
//                                recyclerView.setLayoutManager(firstManager);
//                                recyclerView.setAdapter(new TrailerAdapter(getApplicationContext(), trailer));
//                                recyclerView.smoothScrollToPosition(0);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<TrailerResponse> call, Throwable t) {
//                        Log.d("Error", t.getMessage());
//                        Toast.makeText(Detail3Activity.this, "Error fetching trailer", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//            }
//
//        } catch(Exception e){
//            Log.d("Error", e.getMessage());
//            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    //TODO
//    private void loadReview(){
//        try {
//            if (THE_MOVIE_DB_API_TOKEN.isEmpty()) {
//                Toast.makeText(getApplicationContext(), "Please get your API Key", Toast.LENGTH_SHORT).show();
//                return;
//            } else {
//                Client Client = new Client();
//                Service apiService = Client.getClient().create(Service.class);
//                Call<Review> call = apiService.getReview(movie_id, THE_MOVIE_DB_API_TOKEN);
//
//                call.enqueue(new Callback<Review>() {
//                    @Override
//                    public void onResponse(Call<Review> call, Response<Review> response) {
//                        if (response.isSuccessful()){
//                            if (response.body() != null){
//                                List<ReviewResult> reviewResults = response.body().getResults();
//                                MultiSnapRecyclerView recyclerView2 = (MultiSnapRecyclerView) findViewById(R.id.review_recyclerview);
//                                LinearLayoutManager firstManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
//                                recyclerView2.setLayoutManager(firstManager);
//                                recyclerView2.setAdapter(new ReviewAdapter(getApplicationContext(), reviewResults));
//                                recyclerView2.smoothScrollToPosition(0);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Review> call, Throwable t) {
//
//                    }
//                });
//            }
//
//        } catch (Exception e) {
//            Log.d("Error", e.getMessage());
//            Toast.makeText(this, "unable to fetch data",Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    public void saveFavorite(){
//        favoriteDbHelper = new FavoriteDbHelper(activity);
//        favorite = new Movie();
//
//        Double rate = movie.getVoteAverage();
//
//
//        favorite.setId(movie_id);
//        favorite.setOriginalTitle(movieName);
//        favorite.setPosterPath(thumbnail);
//        favorite.setVoteAverage(rate);
//        favorite.setOverview(synopsis);
//
//        favoriteDbHelper.addFavorite(favorite);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.detail_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()) {
//            case R.id.share:
//                shareContent();
//
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void shareContent(){
//
//        Bitmap bitmap =getBitmapFromView(imageView);
//        try {
//            File file = new File(this.getExternalCacheDir(),"logicchip.png");
//            FileOutputStream fOut = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
//            fOut.flush();
//            fOut.close();
//            file.setReadable(true, false);
//            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(Intent.EXTRA_TEXT, movieName);
//            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//            intent.setType("image/png");
//            startActivity(Intent.createChooser(intent, "Share image via"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private Bitmap getBitmapFromView(View view) {
//        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(returnedBitmap);
//        Drawable bgDrawable =view.getBackground();
//        if (bgDrawable!=null) {
//            bgDrawable.draw(canvas);
//        }   else{
//            canvas.drawColor(Color.WHITE);
//        }
//        view.draw(canvas);
//        return returnedBitmap;
//    }
//
//
//}