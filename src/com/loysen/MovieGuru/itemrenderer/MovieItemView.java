package com.loysen.MovieGuru.itemrenderer;

import com.github.droidfu.widgets.WebImageView;
import com.loysen.MovieGuru.activity.R;
import com.loysen.MovieGuru.model.Movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MovieItemView extends RelativeLayout {

	private TextView titleText;
	private TextView criticText;
	private TextView audienceText;
	private WebImageView imageView;
	
	public MovieItemView(Context context) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.movie_item_layout, this, true);
		
		
		titleText = (TextView) this.findViewById(R.id.titleText);
		criticText = (TextView) this.findViewById(R.id.criticText);
		audienceText = (TextView) this.findViewById(R.id.audienceText);
		
		imageView = (WebImageView) this.findViewById(R.id.webImage);
	}
	
	public void setMovie(Movie movie) {
		titleText.setText(movie.getTitle());
		criticText.setText(movie.getRatings().getCritics_score() + "%");
		audienceText.setText(movie.getRatings().getAudience_score() + "%");
		imageView.setImageUrl(movie.getPosters().getThumbnail());
		imageView.loadImage();
	}

}
