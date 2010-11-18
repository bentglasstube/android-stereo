package org.eatabrick.android_stereo;

import java.util.List;

public class Playlist {
	private List <Song> songs;
	private int position;
	
	public static int PLAYLIST_MODE_NORMAL = 0;
	public static int PLAYLIST_MODE_SHUFFLE = 1;
	
	public Playlist(List <Song> songs) {
		this.songs = songs;
		this.position = 0;
	}
	
	public Song getNext(int mode) {
		if (mode == PLAYLIST_MODE_NORMAL) {
			position++;
			if (position >= songs.size()) {
				position = 0;
			}
			
			return songs.get(position);
		} else if (mode == PLAYLIST_MODE_SHUFFLE) {
			int i = (int) Math.random() * songs.size();
			return songs.get(i);
		} else {
			return null;
		}
	}
}
