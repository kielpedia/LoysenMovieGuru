package com.loysen.MovieGuru.adapter;

import java.util.List;

import com.loysen.MovieGuru.itemrenderer.CastItemView;
import com.loysen.MovieGuru.model.Cast;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CastArrayAdapter extends ArrayAdapter<Cast> {

	private List<Cast> castList;
	private Cast selectedCast;
	
	public CastArrayAdapter(Context context, int textViewResourceId,
			List<Cast> objects) {
		super(context, textViewResourceId, objects);
		
		this.castList = objects;
		this.selectedCast = null;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CastItemView castItem = (CastItemView) convertView;
		
		if(castItem == null)
			castItem = new CastItemView(this.getContext());
		
		castItem.setCast(castList.get(position));
		
		return castItem;
	}

}
