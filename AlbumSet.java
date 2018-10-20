public class AlbumSet {

	// Behaviors
	private String owner;
	private Album[] albums;
	private int numAlbums;

	// Constructor
	public AlbumSet(String owner) {
		this.owner = owner.substring(0, 1).toUpperCase() + owner.substring(1).toLowerCase();
		albums = new Album[20];
	}

	// getters
	public String getOwner() {

		return this.owner;

	}

	public int getNumAlbums() {

		return this.numAlbums;

	}

	public Album getOneAlbumByIndex(int index) {

		return this.albums[index];

	}

	// special setter: adding song to album
	public void addSongToAlbum(String albumName, String songName, String artistName, int minutes, int seconds) {

		if (isAlbumExists(albumName)) {
			this.albums[getAlbumIndex(albumName)].addSong(songName, artistName, minutes, seconds);
		} else {
			Album newAlbum = new Album(albumName);
			newAlbum.addSong(songName, artistName, minutes, seconds);
			this.albums[this.numAlbums++] = newAlbum;
		}
	}

	// special Methods
	// checking weather album exists
	public boolean isAlbumExists(String albumName) {

		albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();
		for (int i = 0; i < this.numAlbums; i++) {
			if (this.albums[i].getAlbumName().equals(albumName)) {
				return true;
			}
		}
		return false;
	}

	// getting Album index
	public int getAlbumIndex(String albumName) {

		int albumIndex = -1;
		albumName = albumName.substring(0, 1).toUpperCase() + albumName.substring(1).toLowerCase();
		for (int i = 0; i < this.numAlbums; i++) {
			if (this.albums[i].getAlbumName().equals(albumName)) {
				albumIndex = i;
				break;
			}
		}
		return albumIndex;
	}

	// sorting by Album name in ascending order
	public void sortByAlbumsName() {

		String artistName1 = "";
		String artistName2 = "";
		Album temp;
		for (int i = 0; i < this.numAlbums; i++) {
			for (int j = i; j < this.numAlbums; j++) {

				artistName1 = this.albums[i].getAlbumName();
				artistName2 = this.albums[j].getAlbumName();

				// compareTo method generates a number greater than 0 if the first string is
				// bigger in ASCII values than the second string
				if (artistName1.compareTo(artistName2) > 0) {
					temp = this.albums[i];
					this.albums[i] = this.albums[j];
					this.albums[j] = temp;

				}
			}
		}
	}

	// a string representation of the object
	public String toString() {

		String str = "Total Number Of Albums: " + this.numAlbums;
		for (int i = 0; i < this.numAlbums; i++) {
			str += "\r\n\r\n" + this.albums[i];
		}

		return str;
	}

}