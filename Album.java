public class Album {
	// class behaviors
	private String albumName;
	private Song[] songs;
	private int totalAlbumLength;
	private int numSongs;

	// constructor
	public Album(String albumName) {
		setAlbumName(albumName);
		songs = new Song[20];
	}

	// getters
	public String getAlbumName() {
		return this.albumName;
	}

	public Song[] getAlbumSongs() {
		return this.songs;
	}

	public int getAlbumLength() {
		return this.totalAlbumLength;
	}

	public int getNumSongs() {
		return this.numSongs;
	}

	// setters
	public void setAlbumName(String albumName) {
		this.albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();
	}

	// special setters: adding a new song to album
	public void addSong(Song newSong) {

		this.songs[this.numSongs++] = newSong;
		this.totalAlbumLength += newSong.getSongLength();
	}

	// adding a new song to Album
	public void addSong(String songName, String artistName, int minutes, int seconds) {

		Song newSong = new Song(songName, artistName, minutes, seconds);
		this.songs[this.numSongs++] = newSong;
		this.totalAlbumLength += newSong.getSongLength();

	}

	// special Methods
	// checks weather a song exists or not
	public int isSongExist(String songName) {

		songName = songName.substring(0, 1).toUpperCase() + songName.substring(1).toLowerCase();
		int placeOfSong = -1;
		for (int i = 0; i < this.numSongs; i++) {
			if (this.songs[i].isSongEqaul(songName)) {
				placeOfSong = i;
				break;
			}
		}
		return placeOfSong;
	}

	// sorting the songs in the album by Artist name in ascending order and if two
	// songs have the same
	// name, the method sorts them in ascending order by their length
	public void sortByArtist() {

		String artistName1 = "";
		String artistName2 = "";
		int songLength1 = 0;
		int songLength2 = 0;
		for (int i = 0; i < this.numSongs; i++) {
			for (int j = i; j < this.numSongs; j++) {

				artistName1 = this.songs[i].getArtistName();
				artistName2 = this.songs[j].getArtistName();

				// compareTo method generates a number greater than 0 if the first string is
				// bigger in ASCII values than the second string
				if (artistName1.compareTo(artistName2) > 0) {
					Song temp = this.songs[i];
					this.songs[i] = this.songs[j];
					this.songs[j] = temp;

					// compareTo method generates 0 if the first string is the same as the second
					// string
				} else if (artistName1.compareTo(artistName2) == 0) {
					songLength1 = this.songs[i].getSongLength();
					songLength2 = this.songs[j].getSongLength();

					// sotrting by song length
					if (songLength1 > songLength2) {
						Song temp = this.songs[i];
						this.songs[i] = this.songs[j];
						this.songs[j] = temp;
					}
				}
			}
		}
	}

	// toString: a string representation of the object
	public String toString() {
		String str = "Album Name: " + this.albumName + ",Number of Songs: " + this.numSongs + ", Album Length: "
				+ this.totalAlbumLength + " (seconds)";
		for (int i = 0; i < this.numSongs; i++)
			str += "\r\n" + this.songs[i];
		return str;
	}

}