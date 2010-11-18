package org.eatabrick.android_stereo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

public class LibraryActivity extends ListActivity {
	private List<Song> songs = new ArrayList<Song>();
	private MediaPlayer mp = new MediaPlayer();
	private int currentPosition = 0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);

        songs.clear();
    	
    	String[] projection = new String[] {
    			MediaStore.Audio.Media.TITLE,
    			MediaStore.Audio.Media.ARTIST,
    			MediaStore.Audio.Media.ALBUM,
    			MediaStore.Audio.Media.DURATION
    	};
    	
    	Cursor c = managedQuery(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
    	c.moveToFirst();
    	
    	while (c.isAfterLast() == false) {
   			songs.add(new Song(c.getString(0), c.getString(1), c.getString(2), c.getInt(3)));
   			c.moveToNext();
    	}
    	
    	SongAdapter songList = new SongAdapter(this, R.layout.library_entry, songs);
    	setListAdapter(songList);
    }

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		currentPosition = position;
		CheckBox cb = ((CheckBox)v.findViewById(R.id.library_entry_check));
		cb.toggle();
	}

	private void playSong(String songPath) {
		try {
			mp.reset();
			mp.setDataSource(songPath);
			mp.prepare();
			mp.start();
			
			mp.setOnCompletionListener(	new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					nextSong();
				}
			});
		} catch (IOException e) {
			Log.v(getString(R.string.app_name), e.getMessage());
		}
	}
    
	private void nextSong() {
		if (++currentPosition >= songs.size()) {
			currentPosition = 0;
		} else {
			// playSong(MEDIA_PATH + songs.get(currentPosition));
		}
	}
}