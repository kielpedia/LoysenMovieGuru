package com.loysen.MovieGuru.itemrenderer;

import com.loysen.MovieGuru.activity.R;
import com.loysen.MovieGuru.model.Cast;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CastItemView extends LinearLayout {

	
	private TextView castNameText;
	private TextView castCharacterText;
	
	public CastItemView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.cast_item_layout, this, true);
		
		
		castNameText = (TextView) this.findViewById(R.id.castNameText);
		castCharacterText = (TextView) this.findViewById(R.id.castCharacterText);
	}
	
	public void setCast(Cast cast) {
		castNameText.setText(cast.getName());
		
		String characterList = "";
		
		if(cast.getCharacters() != null) {
			for (String character : cast.getCharacters()) {
				
				if(!characterList.equals("")) 
					characterList += " | ";
				characterList += character;
			}
		}
		
		castCharacterText.setText(characterList);
	}
}
