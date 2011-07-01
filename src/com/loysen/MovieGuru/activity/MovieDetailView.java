package com.loysen.MovieGuru.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.github.droidfu.widgets.WebImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loysen.MovieGuru.adapter.CastArrayAdapter;
import com.loysen.MovieGuru.adapter.MergeAdapter;
import com.loysen.MovieGuru.adapter.ReviewArrayAdapter;
import com.loysen.MovieGuru.components.SubListPod;
import com.loysen.MovieGuru.delegate.RottenTomatoesDelegate;
import com.loysen.MovieGuru.model.Cast;
import com.loysen.MovieGuru.model.Movie;
import com.loysen.MovieGuru.model.Review;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.database.MergeCursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

public class MovieDetailView extends ListActivity {
	
	private static final int PROGRESS_DIALOG_ID = 0;

	private MergeAdapter mergeAdapter;
	
	private WebImageView detailImage;
	
	private TextView synopsisDetailText;
	private TextView criticDetailText;
	private TextView audienceDetailText;
	private TextView genreDetailText;
	private TextView runtimeDetailText;
	private TextView movieDetailHeader;
	private TextView yearDetailText;
	
	private Movie movie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_detail_layout);
		
		initLayout();
		
		handleExtras();
		
	}
	
	private void initLayout() {
		
		View header = (View)getLayoutInflater().inflate(R.layout.movie_info_section, null);
		header.setEnabled(false);
		getListView().addHeaderView(header);
		
		detailImage = (WebImageView) this.findViewById(R.id.detailImage);
		
		synopsisDetailText = (TextView) this.findViewById(R.id.synopsisDetailText);
		criticDetailText = (TextView) this.findViewById(R.id.criticDetailText);
		audienceDetailText = (TextView) this.findViewById(R.id.audienceDetailText);
		genreDetailText = (TextView) this.findViewById(R.id.genreDetailText);
		runtimeDetailText = (TextView) this.findViewById(R.id.runtimeDetailText);
		yearDetailText = (TextView) this.findViewById(R.id.yearDetailText);
		
		movieDetailHeader = (TextView) this.findViewById(R.id.screenName);
		
		
		
	}
	
	private void handleExtras() {
		Bundle extras = this.getIntent().getExtras();
		
		if(extras == null)
			return;
		
		Movie movie = (Movie) extras.get("movie");
		
		movieDetailHeader.setText(movie.getTitle());
		
		new GetMovieInfoTask().execute(movie.getId());
	}
	
	private void setData(Movie movie) {
		this.movie = movie;
		detailImage.setImageUrl(movie.getPosters().getProfile());
		detailImage.loadImage();
		synopsisDetailText.setText(movie.getSynopsis());
		criticDetailText.setText(movie.getRatings().getCritics_score() + "%");
		audienceDetailText.setText(movie.getRatings().getAudience_score() + "%");
		runtimeDetailText.setText(parseRuntime(movie.getRuntime()));
		yearDetailText.setText(movie.getYear());
		
		mergeAdapter = new MergeAdapter();
		
		mergeAdapter.addView(createListDivider("Cast and Crew:"));
		
		
		mergeAdapter.addAdapter(new CastArrayAdapter(this, 0, movie.getAbridged_cast()));
		
		mergeAdapter.addView(createListDivider("Director(s):"));
		
		mergeAdapter.addAdapter(new CastArrayAdapter(this, 0, movie.getAbridged_directors()));
		
		mergeAdapter.addView(createListDivider("Reviews: "));
		
		mergeAdapter.addAdapter(new ReviewArrayAdapter(this, 0, movie.getReviews()));
		
		if(movie.getGenres() != null) {
			String genreLabel = "";
			for(String genre : movie.getGenres()) {
				if(!genreLabel.equals(""))
					genreLabel += " | ";
				genreLabel += genre;
			} 
			genreDetailText.setText(genreLabel);
		}
		
		this.setListAdapter(mergeAdapter);
		
	}
	
	private String parseRuntime(String runtime) {
		String formattedRuntime = "";
		
		double rawTime = Double.parseDouble(runtime);
		
		int hours = (int) (rawTime / 60);
		
		int minutes = (int) (rawTime % 60);
		
		formattedRuntime += hours + "h " + minutes + "m";
		
		return formattedRuntime;
	}
	
	private TextView createListDivider(String label) {
		TextView view = new TextView(this);
		
		view.setText(label);
		
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		
		view.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.divider));
		view.setTextColor(getResources().getColor(R.color.divider_text));
		
		return view;
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {

		switch(id) {
			case PROGRESS_DIALOG_ID:
				ProgressDialog dialog = new ProgressDialog(this);
				dialog.setMessage("Loading");
				dialog.setCancelable(false);
				return dialog;
			default:
				return null;
		}
	}
	
	private class GetMovieInfoTask extends AsyncTask<Long, Integer, Movie> {
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
//			showDialog(PROGRESS_DIALOG_ID);
			
			super.onPreExecute();
		}

		@Override
		protected Movie doInBackground(Long... params) {
			Movie movie = null;
			
			RottenTomatoesDelegate delegate = new RottenTomatoesDelegate();
			GsonBuilder builder = new GsonBuilder();
			
			builder.setDateFormat("yyyy-dd-MM");
			
			Gson gson = builder.create();
			
			if(params != null && params.length != 0) {
				String movieJson = delegate.getMovieInfo(params[0]);
				
				movie = gson.fromJson(movieJson, Movie.class);
				
				String reviewJson = delegate.getMovieReviews(params[0]);
				
				Type targetType = new TypeToken<Collection<Review>>() {}.getType();
				Collection<Review> objectList = gson.fromJson(reviewJson, targetType);
				
				movie.setReviews((List<Review>) objectList);
				
			} else {
				Log.e("MovieDetailView", "GetMovieInfoTask not provided with Movie ID");
			}
			
			
			return movie;
		}
		
		@Override
		protected void onPostExecute(Movie result) {
			// TODO Auto-generated method stub
		//	dismissDialog(PROGRESS_DIALOG_ID);
			setData(result);
			super.onPostExecute(result);
		}
		
	}
	
}
