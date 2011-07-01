package com.loysen.MovieGuru.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loysen.MovieGuru.adapter.MergeAdapter;
import com.loysen.MovieGuru.adapter.MovieArrayAdapter;
import com.loysen.MovieGuru.delegate.RottenTomatoesDelegate;
import com.loysen.MovieGuru.itemrenderer.MovieItemView;
import com.loysen.MovieGuru.model.Movie;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class OpeningListView extends ListActivity implements OnItemClickListener{

	private List<Movie> movieList;
	private TextView theatreListHeader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list);
		
		theatreListHeader = (TextView) this.findViewById(R.id.screenName);
		theatreListHeader.setText("Opening Soon");
		
		getListView().setOnItemClickListener(this);
		
		movieList = new ArrayList<Movie>();
		
		setListAdapter(new MovieArrayAdapter(this, 0, movieList));
		
		refresh();
	}
	
	private void refresh() {
		new GetCurrentShowingsTask().execute();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Movie movie =  movieList.get(position);
		Intent intent = new Intent(this, MovieDetailView.class);
		intent.putExtra("movie", movie);
		startActivity(intent);
		
	}
	
	
	private class GetCurrentShowingsTask extends AsyncTask<Long, Integer, List<Movie>> {
		RottenTomatoesDelegate delegate;
		
		
		@Override
		protected List<Movie> doInBackground(Long... arg0) {
			
			Collection<Movie> objectList;
			String json;
			GsonBuilder builder = new GsonBuilder();
			
			Gson gson = builder.create();
			
			delegate = new RottenTomatoesDelegate();
			
			json = delegate.getOpeningShowings();
			Type targetType = new TypeToken<List<Movie>>() {}.getType();
			objectList = gson.fromJson(json, targetType);
			
			
			return (List<Movie>) objectList;
		}
		
		@Override
		protected void onPostExecute(List<Movie> result) {
			
			movieList.clear();
			movieList.addAll(result);
			
			((MovieArrayAdapter)(getListAdapter())).notifyDataSetChanged();
		}
		
		
	}




	

	
}
