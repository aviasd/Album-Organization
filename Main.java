import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		AlbumSet myAlbums = new AlbumSet("Aviasd");
		// phase A
		// -------
		// getting file name and opening it

		
		String inputFilePath = JOptionPane.showInputDialog("Please insert the path to the file");
		Scanner fileIn = new Scanner(new File(inputFilePath));
		
		// reading input file and creating the albums
		String[] oneLine;
		String albumName = "";
		String songName = "";
		String artistName = "";
		String[] time;
		int minutes = 0;
		int seconds = 0;
		
		// a loop that reads the input and converts it to the asked values
		while (fileIn.hasNext()) {
			oneLine = fileIn.nextLine().replaceAll("\\s+", " ").split(";");
			albumName = oneLine[3].trim();
			songName = oneLine[0].trim();
			artistName = oneLine[1].trim();
			time = oneLine[2].split(":");
			minutes = Integer.parseInt(time[0].trim());
			seconds = Integer.parseInt(time[1].trim());
			myAlbums.addSongToAlbum(albumName, songName, artistName, minutes, seconds);
		}
		fileIn.close();
		
		// phase B
		// -------
		// album and song ordering

		myAlbums.sortByAlbumsName();
		for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
			myAlbums.getOneAlbumByIndex(i).sortByArtist();
		}

		// phase C
		// -------
		// statistical information

		String albumSetStr = "Number Of Albums: " + myAlbums.getNumAlbums();
		for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
			albumSetStr += "\nAlbum Name: " + myAlbums.getOneAlbumByIndex(i).getAlbumName() + ", Number of Songs: "
					+ myAlbums.getOneAlbumByIndex(i).getNumSongs() + ", Total Songs Length: "
					+ myAlbums.getOneAlbumByIndex(i).getAlbumLength() + " (seconds)";
		}
		System.out.println(albumSetStr + "\n");

		// phase D
		// -------
		// searching for a song

		int answer = JOptionPane.showConfirmDialog(null, "Do you want to search a song?");
		if (answer == 0) {
			Boolean isFound = false;
			String askedSong = JOptionPane.showInputDialog("Please enter the Song name for search");
			int songPlace = 0;
			for (int i = 0; i < myAlbums.getNumAlbums(); i++) {
				songPlace = myAlbums.getOneAlbumByIndex(i).isSongExist(askedSong) + 1;
				if (songPlace > 0) {
					System.out.println("Song: " + askedSong + " exists at Album: "
							+ myAlbums.getOneAlbumByIndex(i).getAlbumName() + " as song number: " + songPlace);
					isFound = true;
				}
			}
			if (isFound == false) {
				System.out.println("Song: " + askedSong + " does not exist at your albums!");
			}
		}

		// phase E
		// ----------
		// getting output file name and saving...

		String outputFilePath = JOptionPane.showInputDialog("Please enter output file name");
		PrintWriter fileOut = new PrintWriter(outputFilePath);
		fileOut.println(myAlbums);
		fileOut.close();
	}

}
