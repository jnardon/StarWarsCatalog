package br.com.accera.starwarscatalog.network.settings;

import br.com.accera.starwarscatalog.data.models.CharacterModel;
import br.com.accera.starwarscatalog.data.models.MovieModel;
import br.com.accera.starwarscatalog.data.models.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by juliano.nardon on 22/01/18.
 */

public interface ApiInterface {

    @GET
    Call<CharacterModel> getCharacter(@Url String url);
    @GET
    Call<MovieModel> getMovie(@Url String url);

    @GET("search/movie")
    Call<MovieResponse> searchMovie(@Query("query") String query);
}
