package com.codepath.yahooanswerclient;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AnswerResultAdapter extends ArrayAdapter<AnswerResult> {

	Context mContext;
	LayoutInflater inflater ;
	List<AnswerResult> mAnswerResults = null;
	
	private int[] colors = new int[] { 0x30FF0000, 0x300000FF };
	
	public AnswerResultAdapter(Context context,List<AnswerResult> answerResults) {
		//super(context, answerResults);
		super(context, android.R.layout.simple_list_item_1, answerResults);
		mContext = context;
		mAnswerResults = answerResults;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView;
		inflater = ((Activity)mContext).getLayoutInflater();
		if (null == convertView) {
			rowView = inflater.inflate(android.R.layout.simple_list_item_1, null, true);
			} else {
				rowView = convertView;
			}

		TextView tv = (TextView)rowView.findViewById(android.R.id.text1);
		tv.setText(mAnswerResults.get(position).getQuestion());
		
//		rowView.setBackgroundColor(Color.BLUE);
		int colorPos = position % colors.length;
		rowView.setBackgroundColor(colors[colorPos]);
		return rowView;
		
	}
}
