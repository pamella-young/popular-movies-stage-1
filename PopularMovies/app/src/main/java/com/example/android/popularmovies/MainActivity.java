package com.example.android.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.popularmovies.Utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    TextView mURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mURL = (TextView)findViewById(R.id.tv_url);
        mURL.setText(NetworkUtils.buildUrl("popularity.desc").toString());
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
            mURL.setText(NetworkUtils.buildUrl("popularity.desc").toString());
        }
        else if(id == R.id.sort_by_ranking){
            mURL.setText(NetworkUtils.buildUrl("vote_average.desc").toString());
        }

        return super.onOptionsItemSelected(item);
    }
}
