package com.loysen.MovieGuru.adapter;

import java.util.List;

import com.loysen.MovieGuru.itemrenderer.MovieItemView;
import com.loysen.MovieGuru.itemrenderer.ReviewItemView;
import com.loysen.MovieGuru.model.Review;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;

public class ReviewArrayAdapter extends ArrayAdapter<Review> implements OnItemLongClickListener{

	private List<Review> reviewList;
	private int selectedPosition;
	private Review selectedReview;
	
	public ReviewArrayAdapter(Context context, int textViewResourceId,
			List<Review> objects) {
		super(context, textViewResourceId, objects);

		selectedPosition = Adapter.NO_SELECTION;
		selectedReview = null;
		reviewList = objects;
	}
	
	public int getSelectedPosition() {
		return selectedPosition;
	}
	
	public Review getSelectedReview() {
		return selectedReview;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ReviewItemView reviewItem = (ReviewItemView) convertView;
		
		if(reviewItem == null)
			reviewItem = new ReviewItemView(this.getContext());
		
		reviewItem.setReview(reviewList.get(position));
		
		return reviewItem;
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
		selectedReview = this.reviewList.get(position);
		return false;
	}

}
