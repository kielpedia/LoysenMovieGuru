package com.loysen.MovieGuru.activity;

import java.util.ArrayList;
import java.util.List;

import com.github.droidfu.widgets.WebImageView;
import com.loysen.MovieGuru.components.CastPod;
import com.loysen.MovieGuru.model.Movie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MovieDetailView extends Activity {

	private WebImageView detailImage;
	private CastPod castSection;
	
	private TextView titleDetailText;
	private TextView synopsisDetailText;
	private TextView criticDetailText;
	private TextView audienceDetailText;
	
	private Movie movie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.movie_detail_layout);
		
		detailImage = (WebImageView) this.findViewById(R.id.detailImage);
		castSection = (CastPod) this.findViewById(R.id.castDetailPod);
		
		titleDetailText = (TextView) this.findViewById(R.id.titleDetailText);
		synopsisDetailText = (TextView) this.findViewById(R.id.synopsisDetailText);
		criticDetailText = (TextView) this.findViewById(R.id.criticDetailText);
		audienceDetailText = (TextView) this.findViewById(R.id.audienceDetailText);
		
		Bundle extras = this.getIntent().getExtras();
		
		if(extras == null)
			return;
		
		movie = (Movie) extras.get("movie");
		
		setData();
		
		List<String> castList = new ArrayList<String>();
		castList.add("Kiel Loysen");
		castList.add("Matt Gay");
		castList.add("Brett Timperman");
		castList.add("Josh Boyle");
		
		castSection.setCastList(castList);
		
	}
	
	private void setData() {
		detailImage.setImageUrl(movie.getPosters().getProfile());
		detailImage.loadImage();
		titleDetailText.setText(movie.getTitle());
		synopsisDetailText.setText(movie.getSynopsis());
		criticDetailText.setText(movie.getRatings().getCritics_score() + "%");
		audienceDetailText.setText(movie.getRatings().getAudience_score() + "%");
	}
}
