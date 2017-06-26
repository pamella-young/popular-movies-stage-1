package com.example.android.popularmovies.Utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Pamella on 25-Jun-17.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/3/discover/movie?";

    private static final String MOVIE_BASE_URL = MOVIE_DB_URL;

    private static final String API_PARAM = "api_key";
    private static final String LANG_PARAM = "language";
    private static final String SORT_PARAM = "sort_by";
    private static final String INCLUDE_ADULT_PARAM = "include_adult";
    private static final String INCLUDE_VIDEO_PARAM = "include_video";
    private static final String PAGE_PARAM = "page";

    //insert api_key in the apiKey variable
    private static final String apiKey = "";
    private static final String lang = "en-US";
    private static final String sortByPopular = "popularity.desc";
    private static final String sortByRating = "vote_average.desc";
    private static String sort = "";

    /*TODO 1: we will use sort param to return the sort by param value*/

    public static URL buildUrl(Boolean sortPopular){
        if(sortPopular)sort = sortByPopular;
        else sort = sortByRating;
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendQueryParameter(API_PARAM, apiKey)
                .appendQueryParameter(LANG_PARAM, lang)
                .appendQueryParameter(SORT_PARAM, sort)
                .appendQueryParameter(INCLUDE_ADULT_PARAM, "false")
                .appendQueryParameter(INCLUDE_VIDEO_PARAM, "false")
                .appendQueryParameter(PAGE_PARAM, "1")
                .build();

        URL url = null;

        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException{
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
