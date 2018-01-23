package br.com.accera.starwarscatalog.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.accera.starwarscatalog.data.services.CharacterService;
import br.com.accera.starwarscatalog.network.settings.ApiClient;
import br.com.accera.starwarscatalog.network.settings.ApiInterface;
import br.com.accera.starwarscatalog.R;
import br.com.accera.starwarscatalog.data.models.CharacterModel;
import br.com.accera.starwarscatalog.data.models.MovieModel;
import br.com.accera.starwarscatalog.data.models.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterDetailActivity extends AppCompatActivity {

    private CharacterModel characterModel;
    private ApiInterface apiService;
    private ArrayList<MovieModel> movieModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        characterModel = CharacterService.instance.searchCharacter(getIntent().getStringExtra("characterModelName"));

        bindViews();
        getMoviesPosters();
    }

    private void getMoviesPosters() {
        apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieModel> call = apiService.getMovie(characterModel.getFilms().get(0));
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                searchMoviePoster(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
    }

    private void searchMoviePoster(String movieName) {
        Call<MovieResponse> call = apiService.searchMovie(movieName);
        Log.d("charName", movieName);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movieModels = response.body().getResults();
                loadMoviePosters();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    private void bindViews() {
        Toolbar toolbar = findViewById(R.id.character_detail_toolbar);
        TextView heightTextView = findViewById(R.id.height_text_view);
        TextView massTextView = findViewById(R.id.mass_text_view);
        TextView hairColorTextView = findViewById(R.id.hair_color_text_view);
        TextView skinColorTextView = findViewById(R.id.skin_color_text_view);
        TextView eyeColorTextView = findViewById(R.id.eye_color_text_view);
        TextView birthYearTextView = findViewById(R.id.birth_year_text_view);
        TextView latitudeTextView = findViewById(R.id.latitude_text_view);
        TextView longitudeTextView = findViewById(R.id.longitute_text_view);
        TextView dateTextView = findViewById(R.id.date_text_view);

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(characterModel.getName());
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        heightTextView.setText(characterModel.getHeight());
        massTextView.setText(characterModel.getMass());
        hairColorTextView.setText(characterModel.getHairColor());
        skinColorTextView.setText(characterModel.getSkinColor());
        eyeColorTextView.setText(characterModel.getEyeColor());
        birthYearTextView.setText(characterModel.getBirthYear());
        latitudeTextView.setText(String.format("%.3f", characterModel.getLatitude()));
        longitudeTextView.setText(String.format("%.3f", characterModel.getLongitude()));
        dateTextView.setText(characterModel.getPickedDate());
    }

    private void loadMoviePosters() {

    }
}
