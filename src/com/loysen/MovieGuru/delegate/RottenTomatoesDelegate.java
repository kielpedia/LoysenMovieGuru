package com.loysen.MovieGuru.delegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RottenTomatoesDelegate {

	private static final String CURRENT_SHOWINGS = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json";
	private static final String MOVIE_BASE = "http://api.rottentomatoes.com/api/public/v1.0/movies/";
	
	private static final String RT_API_KEY = "e65q9n29mpqxsmq6dpj2qazc";
	
	public String getCurrentShowings() {
		String json = "";
		JSONObject jsonObject = null;
		
		HttpClient client = new DefaultHttpClient();
		
		String request = CURRENT_SHOWINGS + "?apiKey=" + RT_API_KEY;
		
		HttpGet httpGet = new HttpGet(request);
		
		try {
			HttpResponse response = client.execute(httpGet);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while((line = in.readLine()) != null){
				json += line;
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jsonObject = new JSONObject(json);
			json = jsonObject.getJSONArray("movies").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
	
	public String getMovieInfo(long id) {
		String json = "";
		
		HttpClient client = new DefaultHttpClient();
		
		String request = MOVIE_BASE + id + ".json?apiKey=" + RT_API_KEY;
		
		HttpGet httpGet = new HttpGet(request);
		
		try {
			HttpResponse response = client.execute(httpGet);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while((line = in.readLine()) != null){
				json += line;
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	
	public String getMovieReviews(long id) {
		String json = "";
		JSONObject jsonObject = null;
		
		HttpClient client = new DefaultHttpClient();
		
		String request = MOVIE_BASE + id + "/reviews.json?apiKey=" + RT_API_KEY;
		
		HttpGet httpGet = new HttpGet(request);
		
		try {
			HttpResponse response = client.execute(httpGet);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while((line = in.readLine()) != null){
				json += line;
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			jsonObject = new JSONObject(json);
			json = jsonObject.getJSONArray("reviews").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
}
