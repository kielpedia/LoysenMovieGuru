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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_navigation);
		
		theatreButton = (Button) this.findViewById(R.id.theatreButton);
		openingButton = (Button) this.findViewById(R.id.openingButton);
		
		theatreButton.setOnClickListener(this);
		openingButton.setOnClickListener(this);
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

}
