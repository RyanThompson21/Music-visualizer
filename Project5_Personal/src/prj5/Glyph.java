package prj5;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.15.18
 * 
 */
public class Glyph {
    private Song song;

    private int xPos;
    private int yPos;

    private int[] byHobby;
    private int[] byMajor;
    private int[] byRegion;

    private int[] currentDisplay;


    /**
     * The constructor, which initializes private
     * fields for the glyph instance
     * 
     * @param song
     *            the song the glyph is being constructed around
     */
    public Glyph(Song song) {
        this.song = song;

        this.byHobby = new int[6 * HobbyEnum.values().length];
        this.byMajor = new int[6 * MajorEnum.values().length];
        this.byRegion = new int[6 * RegionEnum.values().length];

        currentDisplay = byHobby;

        xPos = 0;
        yPos = 0;
    }


    /**
     * Increments either the yes-count, no-count, or blank-count
     * for a given student, for both the heard-of and liked counters
     * 
     * @param hobby
     *            the hobby of the student
     * @param major
     *            the major of the student
     * @param region
     *            the region of the student
     * @param heard
     *            the student's response to whether they heard the song
     * @param like
     *            the student's response to whether they like the song
     */
    public void incrementValue(
        String hobby,
        String major,
        String region,
        String heard,
        String like) {

        boolean hobbyInvalid = hobby.equals("") || hobby.equals(" ");
        boolean majorInvalid = major.equals("") || major.equals(" ");
        boolean regionInvalid = region.equals("") || region.equals(" ");

        int length = HobbyEnum.values().length;

        int hobbyPos = hobbyInvalid ? 0 : HobbyEnum.valueOf(hobby).ordinal();
        int majorPos = majorInvalid ? 0 : MajorEnum.valueOf(major).ordinal();
        int regionPos = regionInvalid
            ? 0
            : RegionEnum.valueOf(region).ordinal();

        if (heard.equalsIgnoreCase("Yes")) {
            byHobby[hobbyPos] += !hobbyInvalid ? 1 : 0;
            byMajor[majorPos] += !majorInvalid ? 1 : 0;
            byRegion[regionPos] += !regionInvalid ? 1 : 0;
        }
        else if (heard.equalsIgnoreCase("No")) {
            byHobby[hobbyPos + length] += !hobbyInvalid ? 1 : 0;
            byMajor[majorPos + length] += !majorInvalid ? 1 : 0;
            byRegion[regionPos + length] += !regionInvalid ? 1 : 0;
        }
        else {
            byHobby[hobbyPos + 2 * length]++;
            byMajor[majorPos + 2 * length]++;
            byRegion[regionPos + 2 * length]++;
        }

        if (like.equalsIgnoreCase("Yes")) {
            byHobby[hobbyPos + 3 * length] += !hobbyInvalid ? 1 : 0;
            byMajor[majorPos + 3 * length] += !majorInvalid ? 1 : 0;
            byRegion[regionPos + 3 * length] += !regionInvalid ? 1 : 0;
        }
        else if (like.equalsIgnoreCase("No")) {
            byHobby[hobbyPos + 4 * length] += !hobbyInvalid ? 1 : 0;
            byMajor[majorPos + 4 * length] += !majorInvalid ? 1 : 0;
            byRegion[regionPos + 4 * length] += !regionInvalid ? 1 : 0;
        }
        else {
            byHobby[hobbyPos + 5 * length]++;
            byMajor[majorPos + 5 * length]++;
            byRegion[regionPos + 5 * length]++;
        }

    }


    /**
     * The overridden toString method for this class, formatting
     * all its data in a useful format
     */
    @Override
    public String toString() {
        String toReturn = "Song Title: " + song.getTitle();
        toReturn += "\nSong Artist: " + song.getArtist();
        toReturn += "\nSong Genre: " + song.getGenre();
        toReturn += "\nSong Year: " + song.getReleaseDate();
        toReturn += "\nHeard";
        toReturn += "\nreading:" + percentOfTotal(0, 4);
        toReturn += " art:" + percentOfTotal(1, 5);
        toReturn += " sports:" + percentOfTotal(2, 6);
        toReturn += " music:" + percentOfTotal(3, 7);
        toReturn += "\nLikes";
        toReturn += "\nreading:" + percentOfTotal(12, 16);
        toReturn += " art:" + percentOfTotal(13, 17);
        toReturn += " sports:" + percentOfTotal(14, 18);
        toReturn += " music:" + percentOfTotal(15, 19) + "\n\n";
        return toReturn;
    }


    /**
     * This method determines what percent of the total either the "heard of" or
     * "liked" numbers represented
     * 
     * @param x
     *            the yes-count
     * @param y
     *            the no-count
     * @return the percentage of the total
     */
    private int percentOfTotal(int x, int y) {
        double numerator = 100 * byHobby[x];
        double denominator = Math.max(1, byHobby[x] + byHobby[y]);
        return (int)(numerator / denominator);
    }


    /**
     * The getter method for the song for this glyph instance
     * 
     * @return song the song corresponding to the glyph
     */
    public Song getSong() {
        return song;
    }


    /**
     * Either the hobby, major, or region counter array
     * 
     * @return currentDisplay the int array for the counter currently selected
     */
    public int[] getCurrentDisplay() {
        return currentDisplay;
    }


    /**
     * Sets the current counter selected to be either the hobby, major, or
     * region counter
     * 
     * @param which
     *            the student parameter by which to display the glyph
     */
    public void setCurrentDisplay(String which) {
        switch (which) {
            case "Hobby":
                currentDisplay = byHobby;
                return;
            case "Major":
                currentDisplay = byMajor;
                return;
            case "Region":
                currentDisplay = byRegion;
                return;
            default:
                return;
        }
    }


    /**
     * Set the graphical position of the glyph on the display
     * 
     * @param i
     *            the x position to set
     * @param j
     *            the y position to set
     */
    public void setPosition(int i, int j) {
        setX(i);
        setY(j);
    }


    /**
     * Gets the hobby counter, even if it isn't selected
     * 
     * @return byHobby the hobby counter
     */
    public int[] getByHobby() {
        return byHobby;
    }


    /**
     * Gets the major counter, even if it isn't selected
     * 
     * @return byMajor the major counter
     */
    public int[] getByMajor() {
        return byMajor;
    }


    /**
     * Gets the region counter, even if it isn't selected
     * 
     * @return byRegion the region counter
     */
    public int[] getByRegion() {
        return byRegion;
    }


    /**
     * @return the yPos
     */
    public int getY() {
        return yPos;
    }


    /**
     * @param y
     *            the y position to set
     */
    public void setY(int y) {
        this.yPos = y;
    }


    /**
     * @return the xPos
     */
    public int getX() {
        return xPos;
    }


    /**
     * @param x
     *            the x position to set
     */
    public void setX(int x) {
        this.xPos = x;
    }

}
