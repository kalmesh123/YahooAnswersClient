package com.codepath.yahooanswerclient;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;

public class AnswerResult implements Serializable{
	
	private String question;
	private String chosenAnswer;
	private String questionId;
	
	public AnswerResult(JSONObject jsonObject){
		try {
			this.setQuestion(jsonObject.getString("Subject"));
			this.chosenAnswer = jsonObject.getString("ChosenAnswer");
			this.setQuestionId(jsonObject.getString("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String toString() {
		return this.getQuestion();
		
	}


	public String getQuestionId() {
		return questionId;
	}


	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}


	public String getQuestion() {
		return question;
	}


	private void setQuestion(String question) {
		this.question = question;
	}
}
