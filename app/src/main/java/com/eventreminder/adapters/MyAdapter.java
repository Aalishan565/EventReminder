package com.eventreminder.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.eventreminder.R;
import com.eventreminder.dtos.EventDto;


public class MyAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater inflater;
	private List<EventDto> eventList = null;
	private ArrayList<EventDto> arraylist;

	public MyAdapter(Context context, List<EventDto> bdayList) {
		this.mContext = context;
		this.eventList = bdayList;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<EventDto>();
		this.arraylist.addAll(bdayList);
	}

	public class ViewHolder {
		TextView name;
		TextView note;
		TextView date;
		TextView event;

	}

	@Override
	public int getCount() {
		return eventList.size();

	}

	@Override
	public Object getItem(int position) {
		return eventList.get(position);

	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item, null);

			holder.name = (TextView) view.findViewById(R.id.tv_name1);
			holder.note = (TextView) view.findViewById(R.id.tv_note1);
			holder.date = (TextView) view.findViewById(R.id.tv_date1);
			holder.event = (TextView) view.findViewById(R.id.tv_event1);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		// Set the results into TextViews
		holder.name.setText(eventList.get(position).getName());
		holder.note.setText(eventList.get(position).getNote());
		holder.date.setText(eventList.get(position).getDate());
		holder.event.setText(eventList.get(position).getEvent());

		return view;
	}
}


