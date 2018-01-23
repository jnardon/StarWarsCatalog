package br.com.accera.starwarscatalog.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public class MovieResponse {

    @SerializedName("results")
    private ArrayList<MovieModel> results;

    public ArrayList<MovieModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieModel> results) {
        this.results = results;
    }
}
