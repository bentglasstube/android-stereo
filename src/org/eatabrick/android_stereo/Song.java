package org.eatabrick.android_stereo;

import java.io.File;

public class Song {
	public String title;
	public String artist;
	public String album;
	public File path;
	public int duration;
	
	public Song(String title, String artist, String album, int duration) {
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.duration = duration;
	}
}
