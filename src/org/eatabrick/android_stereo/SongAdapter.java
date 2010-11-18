package org.eatabrick.android_stereo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SongAdapter extends ArrayAdapter<Song> {
	private List <Song> items;
	private Context context;

	public SongAdapter(Context context, int textViewResourceId, List <Song> items) {
		super(context, textViewResourceId, items);
		this.context = context;
		this.items = items;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.library_entry, null);
		}
		
		Song item = items.get(position);
		if (item != null) {
			((TextView) view.findViewById(R.id.library_entry_title)).setText(item.title);
			((TextView) view.findViewById(R.id.library_entry_artist)).setText(item.artist);
			((TextView) view.findViewById(R.id.library_entry_album)).setText(item.album);
			
			int minutes = item.duration / 60000;
			int seconds = (item.duration / 1000) % 60;
			
			((TextView) view.findViewById(R.id.library_entry_duration)).setText(String.format("%d:%02d", minutes, seconds));
		}
		
		return view;
	}
}
