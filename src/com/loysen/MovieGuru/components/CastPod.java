package com.loysen.MovieGuru.components;

import java.util.List;

import com.loysen.MovieGuru.activity.R;
import com.loysen.MovieGuru.adapter.CastArrayAdapter;
import com.loysen.MovieGuru.model.Cast;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;


public class CastPod extends LinearLayout {
	
	private ListView castListView;
	private ArrayAdapter<String> castAdapter;
	private List<String> castList;
	

	public CastPod(Context context, AttributeSet attrSet) {
		super(context, attrSet);
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.cast_pod, this, true);
		
		castListView = (ListView) this.findViewById(R.id.castPodList);
		
	}
	
	public List<String> getCastList() {
		return castList;
	}

	public void setCastList(List<String> castList) {
		this.castList = castList;
		
		castAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, castList);
		
		castListView.setAdapter(castAdapter);
		
		
		LayoutParams params = null;
		if(castList.size() < 3) 
			params = new LayoutParams(LayoutParams.WRAP_CONTENT, 3*50);
		else
			params = new LayoutParams(LayoutParams.WRAP_CONTENT, castList.size() * 50);
			
		castListView.setLayoutParams(params);
	}

	
}
