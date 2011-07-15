package com.loysen.MovieGuru.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainNavigationView extends Activity implements OnClickListener{
	
	private Button theatreButton;
	private Button openingButton;
	private Button searchButton;
	private Button dvdButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_navigation);
		
		theatreButton = (Button) this.findViewById(R.id.theatreButton);
		openingButton = (Button) this.findViewById(R.id.openingButton);
		searchButton = (Button) this.findViewById(R.id.searchButton);
		dvdButton = (Button) this.findViewById(R.id.dvdReleaseButton);
		
		
		theatreButton.setOnClickListener(this);
		openingButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);
		dvdButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.theatreButton:
				startTheatreActivity();
				break;
			case R.id.openingButton:
				startOpeningActivity();
				break;
			case R.id.searchButton:
				startSearchActivity();
				break;
			case R.id.dvdReleaseButton:
				startDVDActivity();
				break;
		}
	}

	private void startOpeningActivity() {
		Intent intent = new Intent(this, OpeningListView.class);
		startActivity(intent);
	}

	private void startTheatreActivity() {
		Intent intent = new Intent(this, TheatreListView.class);
		startActivity(intent);
	}
	
	private void startSearchActivity() {
		Intent intent = new Intent(this, SearchView.class);
		startActivity(intent);
	}

	private void startDVDActivity() {
		Intent intent = new Intent(this, NewDVDListView.class);
		startActivity(intent);
	}

}
