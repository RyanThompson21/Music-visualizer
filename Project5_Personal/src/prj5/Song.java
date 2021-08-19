package prj5;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.15.18
 *
 */
public class Song {
    private String title;
    private String artist;
    private String year;
    private String genre;


    /**
     * The default constructor for the song class
     * 
     * @param split
     *            the set of input parameters for a song instance
     */
    public Song(String... split) {
        title = split[0];
        artist = split[1];
        year = split[2];
        if (split[3].equals(split[3].toUpperCase())) {
            genre = split[3];
        }
        else {
            genre = split[3].toLowerCase();
        }
    }


    /**
     * The getter for the artist
     * 
     * @return artist the artist that made the song
     */
    public String getArtist() {
        return artist;
    }


    /**
     * The getter for the title
     * 
     * @return title the title of the song
     */
    public String getTitle() {
        return title;
    }


    /**
     * The getter for the release date of the song
     * 
     * @return year the release date
     */
    public String getReleaseDate() {
        return year;
    }


    /**
     * The getter for the genre of the song
     * 
     * @return genre the genre of the song
     */
    public String getGenre() {
        return genre;
    }

}
