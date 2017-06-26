package com.example.android.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.Data.MovieData;
import com.example.android.popularmovies.Utilities.MovieJsonUtils;
import com.example.android.popularmovies.Utilities.NetworkUtils;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Boolean mSortPopular = true;

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_movies);

        /*this is for debugging*/

        GridLayoutManager layoutManager
                = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mMovieAdapter = new MovieAdapter(this);
        mRecyclerView.setAdapter(mMovieAdapter);

        loadMovieData();
    }

    private void loadMovieData(){
        new FetchMovieTask().execute();
    }

    public class FetchMovieTask extends AsyncTask<Void, Void, ArrayList>{

        @Override
        protected ArrayList<MovieData> doInBackground(Void... params) {
            URL movieRequestUrl = NetworkUtils.buildUrl(mSortPopular);

            try{
                String jsonMovieResponse = NetworkUtils.
                        getResponseFromHttpUrl(movieRequestUrl);

                ArrayList<MovieData> simpleJsonMovieData = MovieJsonUtils
                        .getMovieDataFromJson(MainActivity.this, jsonMovieResponse);

                return simpleJsonMovieData;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList movieData) {
            if(movieData != null){
                mMovieAdapter.setMovieData(movieData);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.sort_by_popularity){
            mSortPopular = true;
            loadMovieData();
        }
        else if(id == R.id.sort_by_ranking){
            mSortPopular = false;
            loadMovieData();
        }

        return super.onOptionsItemSelected(item);
    }
}
