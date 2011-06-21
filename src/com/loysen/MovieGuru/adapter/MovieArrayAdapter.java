package com.loysen.MovieGuru.adapter;

import java.util.List;

import com.loysen.MovieGuru.itemrenderer.MovieItemView;
import com.loysen.MovieGuru.model.Movie;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;

public class MovieArrayAdapter extends ArrayAdapter<Movie>  implements OnItemLongClickListener{
	
	private List<Movie> movieList;
	private int selectedPosition;
	private Movie selectedMovie;
	
	public MovieArrayAdapter(Context context, int textViewResourceId,
			List<Movie> objects) {
		super(context, textViewResourceId, objects);
		
		this.movieList = objects;
		this.selectedPosition = Adapter.NO_SELECTION;
		this.selectedMovie = null;
	}
	
	public int getSelectedPostion() {
		return this.selectedPosition;
	}
	
	public Movie getSelectedMovie() {
		return this.selectedMovie;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MovieItemView movieItem = (MovieItemView) convertView;
		
		if(movieItem == null)
			movieItem = new MovieItemView(this.getContext());
		
		movieItem.setMovie(movieList.get(position));
		
		return movieItem;
	}

	/*
	 * Updates the selectedPosition and selectedMovie so the 
	 * ListView can be aware which Movie has been long-pressed
	 * 
	 * (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemLongClickListener#onItemLongClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		selectedPosition = position;
		selectedMovie = this.movieList.get(position);
		return false;
	}


}
