package com.loysen.MovieGuru.itemrenderer;

import com.loysen.MovieGuru.activity.R;
import com.loysen.MovieGuru.model.Review;
import com.loysen.MovieGuru.util.DateUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ReviewItemView extends RelativeLayout {

	private TextView reviewerText;
	private TextView publicationText;
	private TextView dateText;
	private TextView reviewText;
	
	public ReviewItemView(Context context) {
		super(context);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.review_item, this, true);
		
		
		reviewerText = (TextView) this.findViewById(R.id.reviewerItem);
		publicationText = (TextView) this.findViewById(R.id.publicationItem);
		dateText = (TextView) this.findViewById(R.id.dateItem);
		reviewText = (TextView) this.findViewById(R.id.reviewItem);
	}
	
	public void setReview(Review review) {
		reviewerText.setText(review.getCritic());
		publicationText.setText(review.getPublication());
		dateText.setText(DateUtil.dateToString(review.getDate()));
		reviewText.setText(review.getQuote());
	}
}
