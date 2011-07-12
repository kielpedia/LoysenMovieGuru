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

public class NewDVDListView extends ListActivity implements OnItemClickListener{

	private List<Movie> dvdList;
	private TextView dvdListHeader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.movie_list);
		
		dvdListHeader = (TextView) this.findViewById(R.id.screenName);
		dvdListHeader.setText("New Release DVD's");
		
		getListView().setOnItemClickListener(this);
		
		dvdList = new ArrayList<Movie>();
		
		setListAdapter(new MovieArrayAdapter(this, 0, dvdList));
		
		refresh();
	}
	
	private void refresh() {
		new GetCurrentShowingsTask().execute();
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Movie movie =  dvdList.get(position);
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
			
			json = delegate.getNewDVDList();
			Type targetType = new TypeToken<List<Movie>>() {}.getType();
			objectList = gson.fromJson(json, targetType);
			
			
			return (List<Movie>) objectList;
		}
		
		@Override
		protected void onPostExecute(List<Movie> result) {
			
			dvdList.clear();
			dvdList.addAll(result);
			
			((MovieArrayAdapter)(getListAdapter())).notifyDataSetChanged();
		}
		
		
	}




	

	
}