package com.codepath.yahooanswerclient;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AnswerActivity extends Activity{

	EditText queryTextField;
	Button searchButton;
	ListView listView;
	
	ArrayList<AnswerResult> answerResults = new ArrayList<AnswerResult>();
	AnswerResultAdapter answerResultAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer);
		setUpViews();
		answerResultAdapter = new AnswerResultAdapter(this, answerResults);
		listView.setAdapter(answerResultAdapter);		
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getApplicationContext(), DetailActivity.class);
				AnswerResult answerResult = answerResults.get(position);
				i.putExtra("allAnswers", answerResult);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.answer, menu);
		return true;
	}

	private void setUpViews(){
		this.queryTextField = (EditText)findViewById(R.id.editQuery);
		this.searchButton = (Button)findViewById(R.id.searchButton);
		this.listView = (ListView)findViewById(R.id.listView);
	}
	
	public void onSearchButtonClick(View v){
		String query = this.queryTextField.getText().toString();
		Toast.makeText(this, "Searching for "+query, Toast.LENGTH_LONG).show();
		
		//get all the answer results asynchronously
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20answers.search%20"+
		"where%20query%3D%22"+Uri.encode(query)+"%22%20and%20category_id%3D2115500137%20and%20type%3D%22resolved%22&"+
				"format=json",
				new JsonHttpResponseHandler(){
					@Override
					public void onSuccess(JSONObject response){
						JSONArray answerJsonResults;
						try {
							answerJsonResults = response.getJSONObject("query").getJSONObject("results").getJSONArray("Question");
							answerResults.clear();
							for(int x=0;x<answerJsonResults.length();x++){
								answerResults.add(new AnswerResult(answerJsonResults.getJSONObject(x)));
							}
//							answerResults.clear();
							answerResultAdapter.addAll(answerResults);
							Log.d("DEBUG", answerResults.toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
		});
	}

}
