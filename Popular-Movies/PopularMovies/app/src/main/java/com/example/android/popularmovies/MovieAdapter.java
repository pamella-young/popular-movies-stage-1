package com.example.android.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.popularmovies.Data.MovieData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Pamella on 26-Jun-17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    final String BASE_URL = "http://image.tmdb.org/t/p/";
    final String IMAGE_SIZE = "w500";
    Context myContext;

    public MovieAdapter(Context myContext) {
        this.myContext = myContext;
    }

    private ArrayList<MovieData> mMovieData;

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder{
        public final ImageView mMoviePosterImageView;

        public MovieAdapterViewHolder(View itemView) {
            super(itemView);
            mMoviePosterImageView = (ImageView) itemView.findViewById(R.id.imageview_movie_poster);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String imagePath = BASE_URL + IMAGE_SIZE + mMovieData.get(position).getImagePath();
        Picasso.with(myContext).load(imagePath).into(holder.mMoviePosterImageView);
    }

    @Override
    public int getItemCount() {
        if(mMovieData == null) return 0;
        return mMovieData.size();
    }

    public void setMovieData(ArrayList<MovieData> movieData){
        mMovieData = movieData;
        notifyDataSetChanged();
    }
}
