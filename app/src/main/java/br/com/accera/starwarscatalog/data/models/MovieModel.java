package br.com.accera.starwarscatalog.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public class MovieModel {

    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
