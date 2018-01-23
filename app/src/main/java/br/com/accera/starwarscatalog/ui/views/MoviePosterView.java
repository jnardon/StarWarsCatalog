package br.com.accera.starwarscatalog.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.accera.starwarscatalog.R;
import br.com.accera.starwarscatalog.data.models.MovieModel;
import br.com.accera.starwarscatalog.network.settings.ApiClient;

/**
 * Created by juliano.nardon on 23/01/18.
 */

public class MoviePosterView extends LinearLayout {

        private TextView movieTitleTextView;
        private ImageView movieImageView;
        private View rootView;
        private MovieModel movieModel;

        public MoviePosterView(Context context) {
            super(context);
            init(context);
        }

        public MoviePosterView(Context context, MovieModel movieModel) {
            super(context);
            this.movieModel = movieModel;
            init(context);
        }

        public MoviePosterView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

    private void init(Context context) {
        rootView = inflate(context, R.layout.view_movie_poster, this);
        movieTitleTextView = rootView.findViewById(R.id.movie_title_text_view);
        movieImageView = rootView.findViewById(R.id.movie_image_view);

        Picasso.with(context).load(ApiClient.IMAGES_URL + movieModel.getPosterPath()).into(movieImageView);
        movieTitleTextView.setText(movieModel.getTitle());
    }
}
