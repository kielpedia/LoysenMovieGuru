package com.loysen.MovieGuru.adapter;

import java.util.List;

import com.loysen.MovieGuru.model.Cast;

import android.content.Context;
import android.widget.ArrayAdapter;

public class CastArrayAdapter extends ArrayAdapter<Cast> {

	public CastArrayAdapter(Context context, int textViewResourceId,
			List<Cast> objects) {
		super(context, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
	}

}
