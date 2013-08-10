package com.codepath.yahooanswerclient;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class AnswerDetailAdapter extends ArrayAdapter<String> {

	public AnswerDetailAdapter(Context context, List<String> strings) {
		super(context, android.R.layout.simple_list_item_1,strings);
		// TODO Auto-generated constructor stub
	}

}
