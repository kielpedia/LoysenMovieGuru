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
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchView extends ListActivity implements OnItemClickListener, OnClickListener{

	private List<Movie> movieList;
	private TextView searchHeader;
	private EditText searchBar;
	private Button searchButton;
	private View noResult;
	private View loading;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
		
		searchHeader = (TextView) this.findViewById(R.id.screenName);
		searchBar = (EditText) this.findViewById(R.id.searchText);
		searchButton = (Button) this.findViewById(R.id.searchViewButton);
		noResult = (View) getLayoutInflater().inflate(R.layout.empty_search, null);
		loading = (View) getLayoutInflater().inflate(R.layout.loading_layout, null);
		
		searchHeader.setText("Movie Search");
		
		getListView().setOnItemClickListener(this);
		searchButton.setOnClickListener(this);
		
		movieList = new ArrayList<Movie>();
		
		setListAdapter(new MovieArrayAdapter(this, 0, movieList));
		
	}
	
	private void search() {
		new SearchTask().execute(searchBar.getText().toString());
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Movie movie =  movieList.get(position);
		Intent intent = new Intent(this, MovieDetailView.class);
		intent.putExtra("movie", movie);
		startActivity(intent);
		
	}
	
	@Override
	public void onClick(View v) {
		search();
		
	}
	
	
	private class SearchTask extends AsyncTask<String, Integer, List<Movie>> {
		RottenTomatoesDelegate delegate;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			getListView().setEmptyView(loading);
		}
		
		
		@Override
		protected List<Movie> doInBackground(String... arg0) {
			
			Collection<Movie> objectList;
			String json;
			GsonBuilder builder = new GsonBuilder();
			
			Gson gson = builder.create();
			
			delegate = new RottenTomatoesDelegate();
			
			json = delegate.search(arg0[0]);
			Type targetType = new TypeToken<List<Movie>>() {}.getType();
			objectList = gson.fromJson(json, targetType);
			
			
			return (List<Movie>) objectList;
		}
		
		@Override
		protected void onPostExecute(List<Movie> result) {
			
			movieList.clear();
			movieList.addAll(result);
			
			((MovieArrayAdapter)(getListAdapter())).notifyDataSetChanged();
			
			getListView().setEmptyView(noResult);
		}
		
		
	}

	


	




	

	
}
