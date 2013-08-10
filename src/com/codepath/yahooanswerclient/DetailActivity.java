package com.codepath.yahooanswerclient;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends Activity {

	ArrayList<String> answers = new ArrayList<String>();
	AnswerDetailAdapter answerDetailAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		answerDetailAdapter = new AnswerDetailAdapter(this, answers);
		ListView answerListView = (ListView)findViewById(R.id.answerListView);
		answerListView.setAdapter(answerDetailAdapter);
		
		AnswerResult answerResult = (AnswerResult) getIntent().getSerializableExtra("allAnswers");
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://query.yahooapis.com/v1/public/yql?"+
		"q=select%20*%20from%20answers.getquestion%20where%20question_id%3D%22"+answerResult.getQuestionId()+"%22&"+
				"format=json", 
				new JsonHttpResponseHandler(){
					@Override
					public void onSuccess(JSONObject response){
						JSONArray jsonAnswerArray;
						try {
							jsonAnswerArray = response.getJSONObject("query").
									getJSONObject("results").getJSONObject("Question").getJSONObject("Answers").
									getJSONArray("Answer");
											
							for(int i=0;i<jsonAnswerArray.length();i++){
								JSONObject jsonObject = jsonAnswerArray.getJSONObject(i);
								answerDetailAdapter.add(jsonObject.getString("Content"));
								
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

}
