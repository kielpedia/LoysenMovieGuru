package com.loysen.MovieGuru.components;

import java.util.List;

import com.loysen.MovieGuru.activity.R;
import com.loysen.MovieGuru.adapter.CastArrayAdapter;
import com.loysen.MovieGuru.model.Cast;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class SubListPod extends LinearLayout {
	
	private ListView castListView;
	private TextView headerLabel;
	private ArrayAdapter castAdapter;
	
	

	public SubListPod(Context context) {
		super(context);
		
		create();
	}
	
	public SubListPod(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		
		create();
		
		handleAttributes(attrSet);
		
	}
	
	private void create() {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.cast_pod, this, true);
		
		castListView = (ListView) this.findViewById(R.id.castPodList);
		headerLabel = (TextView) this.findViewById(R.id.castPodLabel);
	}
	
	private void handleAttributes(AttributeSet attrSet) {
		if(attrSet != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrSet, R.styleable.SubListPod);
			
			headerLabel.setText(a.getString(R.styleable.SubListPod_header_text));
			
			a.recycle();
		}
	}
	
	public ArrayAdapter getCastAdapter() {
		return castAdapter;
	}

	public void setCastAdapter(ArrayAdapter castAdapter) {
		this.castAdapter = castAdapter;
		
		castListView.setAdapter(castAdapter);
		
		LayoutParams params = null;
//		if(castAdapter.getCount() >= 3) 
//			params = new LayoutParams(LayoutParams.WRAP_CONTENT, 3*65);
//		else
			//params = new LayoutParams(LayoutParams.MATCH_PARENT, castAdapter.getCount() * 45);
			
		//castListView.setLayoutParams(params);
	}

	
}
