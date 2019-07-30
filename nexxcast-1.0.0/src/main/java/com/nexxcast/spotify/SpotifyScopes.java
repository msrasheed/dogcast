package com.nexxcast.spotify;

import java.util.ArrayList;

public class SpotifyScopes {
	class ListeningHistory {
		public static final String UserTopRead = "user_top_read";
		public static final String UserReadRecentlyPlayed = "user_read_recently_played";
	}
	
	class SpotifyConnect {
		public static final String UserReadPlaybackState = "user-read-playback-state";
		public static final String UserReadCurrentlyPlaying = "user-read-currently-playing";
		public static final String UserModifyPlaybackState = "user-modify-playback-state";
	}
	
	public class Library {
		public static final String UserLibraryModify = "user-library-modify";
		public static final String UserLibraryRead = "user-library-read";
	}
	
	public class Playback {
		public static final String Streaming = "streaming";
		public static final String AppRemoteControl = "app-remote-control";
	}
	
	public class Users {
		public static final String UserReadPrivate = "user-read-private";
		public static final String UserReadEmail = "user-read-email";
	}
	
	public class Follow {
		public static final String UserFollowModify = "user-follow-modify";
		public static final String UserFollowRead = "user-follow-read";
	}
	
	public class Playlists {
		public static final String PlaylistModifyPublic = "playlist-modify-public";
		public static final String PlaylistReadCollaborative = "playlist-read-collaborative";
		public static final String PlaylistReadPrivate = "playlist-read-private";
		public static final String PlaylistModifyPrivate = "playlist-modify-private";
	}
	
	public static String getHostScopes() {
		String retVal = String.join(" ", 
					SpotifyConnect.UserReadPlaybackState,
					SpotifyConnect.UserReadCurrentlyPlaying,
					SpotifyConnect.UserModifyPlaybackState,
					Playback.AppRemoteControl,
					Playlists.PlaylistModifyPublic,
					Playlists.PlaylistReadCollaborative,
					Playlists.PlaylistReadPrivate
				);
		return retVal;
	}
}
