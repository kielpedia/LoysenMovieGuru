package com.loysen.MovieGuru.activity;

import java.util.ArrayList;
import java.util.List;

import com.github.droidfu.widgets.WebImageView;
import com.loysen.MovieGuru.adapter.CastArrayAdapter;
import com.loysen.MovieGuru.components.SubListPod;
import com.loysen.MovieGuru.model.Cast;
import com.loysen.MovieGuru.model.Movie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MovieDetailView extends Activity {

	private WebImageView detailImage;
	private SubListPod castSection;
	private SubListPod directorSection;
	
	private TextView titleDetailText;
	private TextView synopsisDetailText;
	private TextView criticDetailText;
	private TextView audienceDetailText;
	private TextView genreDetailText;
	
	private Movie movie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.movie_detail_layout);
		
		initLayout();
		
		handleExtras();
		
		setData();
		
	}
	
	private void initLayout() {
		detailImage = (WebImageView) this.findViewById(R.id.detailImage);
		castSection = (SubListPod) this.findViewById(R.id.castDetailPod);
		directorSection = (SubListPod) this.findViewById(R.id.directorDetailPod);
		
		titleDetailText = (TextView) this.findViewById(R.id.titleDetailText);
		synopsisDetailText = (TextView) this.findViewById(R.id.synopsisDetailText);
		criticDetailText = (TextView) this.findViewById(R.id.criticDetailText);
		audienceDetailText = (TextView) this.findViewById(R.id.audienceDetailText);
		genreDetailText = (TextView) this.findViewById(R.id.genreDetailText);
		
	}
	
	private void handleExtras() {
		Bundle extras = this.getIntent().getExtras();
		
		if(extras == null)
			return;
		
		movie = (Movie) extras.get("movie");
	}
	
	private void setData() {
		detailImage.setImageUrl(movie.getPosters().getProfile());
		detailImage.loadImage();
		titleDetailText.setText(movie.getTitle());
		synopsisDetailText.setText(movie.getSynopsis());
		criticDetailText.setText(movie.getRatings().getCritics_score() + "%");
		audienceDetailText.setText(movie.getRatings().getAudience_score() + "%");
		
		if(movie.getAbridged_cast() == null)
			movie.setAbridged_cast(new ArrayList<Cast>());
		castSection.setCastAdapter(new CastArrayAdapter(this, 0, movie.getAbridged_cast()));
		
		if(movie.getAbridged_directors() == null)
			movie.setAbridged_directors(new ArrayList<Cast>());
		directorSection.setCastAdapter(new CastArrayAdapter(this, 0, movie.getAbridged_directors()));
		
		if(movie.getGenres() != null) {
			String genreLabel = "";
			for(String genre : movie.getGenres()) {
				if(!genreLabel.equals(""))
					genreLabel += " | ";
				genreLabel += genre;
			} 
			genreDetailText.setText(genreLabel);
		}
	}
	
}
