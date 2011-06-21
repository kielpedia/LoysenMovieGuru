package com.loysen.MovieGuru.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loysen.MovieGuru.adapter.MovieArrayAdapter;
import com.loysen.MovieGuru.delegate.RottenTomatoesDelegate;
import com.loysen.MovieGuru.model.Movie;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class CurrentShowingsView extends ListActivity implements OnItemClickListener{

	private List<Movie> moviesList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		moviesList = new ArrayList<Movie>();
		
		MovieArrayAdapter movieAdapter = new MovieArrayAdapter(this, 0, moviesList);
		
		setListAdapter(movieAdapter);
		
		getListView().setOnItemClickListener(this);
		
		refresh();
	}
	
	private void refresh() {
		new GetCurrentShowingsTask().execute();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Movie movie = moviesList.get(position);
		Intent intent = new Intent(this, MovieDetailView.class);
		intent.putExtra("movie", movie);
		startActivity(intent);
		
	}
	
	
	private class GetCurrentShowingsTask extends AsyncTask<Long, Integer, List<Movie>> {
		RottenTomatoesDelegate delegate;
		
		
		@Override
		protected List<Movie> doInBackground(Long... arg0) {
			GsonBuilder builder = new GsonBuilder();
			
			Gson gson = builder.create();
			
			delegate = new RottenTomatoesDelegate();
			
			String json = delegate.getCurrentShowings();
			
			
			Type targetType = new TypeToken<Collection<Movie>>() {}.getType();
			Collection<Movie> objectList = gson.fromJson(json, targetType);
			
			
			return (List<Movie>) objectList;
		}
		
		@Override
		protected void onPostExecute(List<Movie> result) {
			
			moviesList.clear();
			moviesList.addAll(result);
			
			((MovieArrayAdapter) getListAdapter()).notifyDataSetChanged();
			
		}
		
		
	}




	

	
}
