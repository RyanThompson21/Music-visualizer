package prj5;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.15.18
 */
public class GUIDisplay {

    private Window window;
    // private ArrayList<Glyph> glyphs;
    private LinkedList<Glyph> glyphs;
    private int currentPage;
    private String currentDisplay;
    private String sortedBy;
    public static final int MAX_DISPLAYED_ITEMS = 9;
    public static final int AXIS_WIDTH = 10;
    public static final int AXIS_HEIGHT = 60;
    public static final int BAR_WIDTH = AXIS_HEIGHT / 4;
    public static final int TEXT_HEIGHT = 20;

    public static final Color[] BAR_COLORS = new Color[] { Color.MAGENTA,
        Color.BLUE, Color.YELLOW, Color.GREEN };


    /**
     * The constructor for GUIDisplay, the workhorse GUI class. This constructor
     * initializes the user controls, including buttons, and displays the glyphs
     * and legend, beginning with the songs sorted according to artist and
     * displayed by hobby
     */
    public GUIDisplay() {
        window = new Window("Project 5: Music Visualization");

        window.setSize(1000, 600);

        makeButton("<- Prev", "clickedPrevButton", WindowSide.NORTH);
        makeButton("Sort by Artist Name", "clickedArtistSort",
            WindowSide.NORTH);
        makeButton("Sort by Song Title", "clickedTitleSort", WindowSide.NORTH);
        makeButton("Sort by Release Year", "clickedReleaseSort",
            WindowSide.NORTH);
        makeButton("Sort by Genre", "clickedGenreSort", WindowSide.NORTH);
        makeButton("Next ->", "clickedNextButton", WindowSide.NORTH);

        makeButton("Represent Hobby", "clickedHobbyRep", WindowSide.SOUTH);
        makeButton("Represent Major", "clickedMajorRep", WindowSide.SOUTH);
        makeButton("Represent Region", "clickedRegionRep", WindowSide.SOUTH);
        makeButton("Quit", "clickedQuitButton", WindowSide.SOUTH);

        currentPage = 0;
        currentDisplay = "Hobby";
        sortedBy = "Artist";

        glyphs = new LinkedList<>();


    }


    /**
     * 
     * this method summarizes the glyphs in textual format
     * 
     * @return outString the text form of the glyph display
     */
    public String textOutput() {
        String outString = "";
        try {
            this.clickedGenreSort(new Button());
        }
        catch (Exception e) {
            // Hi
        }
        for (Glyph g : glyphs) {
            outString += g.toString();
        }
        try {
            this.clickedTitleSort(new Button());
        }
        catch (Exception e) {
            // Bye
        }
        for (Glyph g : glyphs) {
            outString += g.toString();
        }
        return outString;
    }


    /**
     * This method sets up the glyphs to be displayed and processes the
     * responses from the students into something approaching a reasonable
     * format
     * 
     * @param songs
     *            the songs to display as glyphs
     * @param students
     *            the students to get responses from with regards to each song
     */
    public void addGlyphs(ArrayList<Song> songs, ArrayList<Student> students) {

        for (int x = 0; x < songs.size(); x++) {
            Song song = songs.get(x);
            glyphs.add(new Glyph(song));
        }

        for (int student = 0; student < students.size(); student++) {
            Student currentStudent = students.get(student);
            String hobbyValue = currentStudent.getHobby();
            String majorValue = currentStudent.getMajor();
            String regionValue = currentStudent.getRegion();

            for (int song = 0; song < 2 * songs.size(); song += 2) {
                String[] response = currentStudent.getResponses();

                boolean hasEvenEndingBlank = response.length <= song;
                boolean hasOddEndingBlank = response.length <= song + 1;

                String heard = hasEvenEndingBlank ? "" : response[song];
                String liked = hasOddEndingBlank ? "" : response[song + 1];

                Glyph glyph = glyphs.get(song / 2);
                glyph.incrementValue(hobbyValue, majorValue, regionValue, heard,
                    liked);
            }
        }

        this.update();

    }


    /**
     * This method changes the legend to correspond to which thing, hobby,
     * major, or region, is the currently selected view
     */
    private void updateLegend() {
        int xPos = window.getWidth() * 3 / 4;
        int yPos = window.getHeight() * 1 / 4;
        int width = window.getWidth() * 1 / 4;
        int height = window.getHeight() * 3 / 4;

        String[] legendValues;

        switch (this.currentDisplay) {
            case "Hobby":
                legendValues = new String[] { "Reading", "Art", "Sports",
                    "Music" };
                break;
            case "Major":
                legendValues = new String[] { "Math or CMDA",
                    "Computer Science", "Other Engineering", "Other" };
                break;
            case "Region":
                legendValues = new String[] { "Southeast", "Northeast",
                    "Other United States", "Outside of United States" };
                break;
            default:
                legendValues = new String[] { "", "", "", "" };
        }

        Shape outerBox = new Shape(xPos, yPos, width, height, Color.BLACK);
        Shape innerBox = new Shape(xPos + 2, yPos + 2, width - 4, height - 4,
            Color.WHITE);
        TextShape legendTitle = new TextShape(xPos + width * 1 / 4, yPos + 20,
            currentDisplay + " Legend");
        TextShape magentaTitle = new TextShape(xPos + width * 1 / 4, yPos + 40,
            legendValues[0]);
        TextShape blueTitle = new TextShape(xPos + width * 1 / 4, yPos + 60,
            legendValues[1]);
        TextShape yellowTitle = new TextShape(xPos + width * 1 / 4, yPos + 80,
            legendValues[2]);
        TextShape greenTitle = new TextShape(xPos + width * 1 / 4, yPos + 100,
            legendValues[3]);

        TextShape heardTitle = new TextShape(xPos + width * 1 / 4, yPos + 200,
            "Heard");
        TextShape likeTitle = new TextShape(xPos + width * 3 / 5, yPos + 200,
            "Liked");

        Glyph legendGlyph = new Glyph(new Song("Song title", "Artist", "", ""));
        legendGlyph.setPosition(xPos + width * 1 / 2, yPos + 175);

        Shape[] glyphShapes = getShapes(legendGlyph);

        for (Shape s : glyphShapes) {
            window.addShape(s);
        }

        legendTitle.setBackgroundColor(Color.WHITE);
        magentaTitle.setBackgroundColor(Color.WHITE);
        blueTitle.setBackgroundColor(Color.WHITE);
        yellowTitle.setBackgroundColor(Color.WHITE);
        greenTitle.setBackgroundColor(Color.WHITE);

        heardTitle.setBackgroundColor(Color.WHITE);
        likeTitle.setBackgroundColor(Color.WHITE);

        magentaTitle.setForegroundColor(Color.MAGENTA);
        blueTitle.setForegroundColor(Color.BLUE);
        yellowTitle.setForegroundColor(Color.ORANGE);
        greenTitle.setForegroundColor(Color.GREEN);

        window.addShape(legendTitle);
        window.addShape(magentaTitle);
        window.addShape(blueTitle);
        window.addShape(yellowTitle);
        window.addShape(greenTitle);
        window.addShape(heardTitle);
        window.addShape(likeTitle);
        window.addShape(innerBox);
        window.addShape(outerBox);
    }


    /**
     * A helper method to get all the shapes making up a given glyph
     * 
     * @param glyphToShapes
     *            the glyph to make shapes for
     * @return the array of shapes making up a glyph
     */
    private Shape[] getShapes(Glyph glyphToShapes) {
        Shape[] glyphShapes = new Shape[HobbyEnum.values().length + 3];

        glyphShapes[0] = new Shape(glyphToShapes.getX(), glyphToShapes.getY(),
            AXIS_WIDTH, AXIS_HEIGHT, Color.BLACK);
        glyphShapes[1] = getBar(0, glyphToShapes);
        glyphShapes[2] = getBar(1, glyphToShapes);
        glyphShapes[3] = getBar(2, glyphToShapes);
        glyphShapes[4] = getBar(3, glyphToShapes);

        Song glyphSong = glyphToShapes.getSong();
        String subtitleText = "";

        switch (this.sortedBy) {
            case "Title":
                subtitleText = glyphSong.getTitle();
                break;
            case "Genre":
                subtitleText = glyphSong.getGenre();
                break;
            case "Artist":
                subtitleText = glyphSong.getArtist();
                break;
            case "Year":
                subtitleText = glyphSong.getReleaseDate();
                break;
            default:
                break;
        }

        subtitleText = this.sortedBy + ": " + subtitleText;
        int titleX = glyphToShapes.getX() - 7 * glyphSong.getTitle().length()
            / 2;
        int titleY = glyphToShapes.getY() - 2 * TEXT_HEIGHT;

        int subtitleX = glyphToShapes.getX() - 7 * subtitleText.length() / 2;
        int subtitleY = glyphToShapes.getY() - TEXT_HEIGHT;

        titleX = titleX < 0 ? 0 : titleX;
        titleY = titleY < 0 ? 0 : titleY;
        subtitleX = subtitleX < 0 ? 0 : subtitleX;
        subtitleY = subtitleY < 0 ? 0 : subtitleY;

        glyphShapes[5] = new TextShape(titleX, titleY, glyphSong.getTitle());
        glyphShapes[6] = new TextShape(subtitleX, subtitleY, subtitleText);

        glyphShapes[5].setBackgroundColor(Color.WHITE);
        glyphShapes[6].setBackgroundColor(Color.WHITE);

        return glyphShapes;
    }


    /**
     * A helper method for the helper method, this method gets the bar shapes
     * and positions them appropriately for each glyph's display
     * 
     * @param which
     *            the index of the bar to generate
     * @param glyphToShapes
     *            the glyph for which the bars are being generated
     * @return the bar corresponding to the index
     */
    private Shape getBar(int which, Glyph glyphToShapes) {
        int leftBarLength = glyphToShapes.getCurrentDisplay()[which] / 2;
        int rightBarLength = glyphToShapes.getCurrentDisplay()[which + 3
            * HobbyEnum.values().length] / 2;
        int barXPos = glyphToShapes.getX() - leftBarLength;
        int barYPos = glyphToShapes.getY() + BAR_WIDTH * which;
        int barLength = leftBarLength + rightBarLength + AXIS_WIDTH;
        return new Shape(barXPos, barYPos, barLength, BAR_WIDTH,
            BAR_COLORS[which]);
    }


    /**
     * Yet another helper method, this one puts all the other helper methods
     * together and works to update and display the changed views of the glyphs
     * as needed, such as after every button press
     */
    private void update() {
        int startIndex = currentPage * MAX_DISPLAYED_ITEMS;
        int endIndex = (currentPage + 1) * MAX_DISPLAYED_ITEMS;

        window.removeAllShapes();

        updateLegend();

        for (int i = startIndex; i < endIndex && i < glyphs.size(); i++) {
            Glyph currentGlyph = glyphs.get(i);
            int[] xyPosition = getXYPosition(i % MAX_DISPLAYED_ITEMS);
            currentGlyph.setPosition(xyPosition[0], xyPosition[1]);
            currentGlyph.setCurrentDisplay(currentDisplay);
            Shape[] glyphShapes = getShapes(currentGlyph);
            for (Shape shape : glyphShapes) {
                window.addShape(shape);
            }
        }
    }


    /**
     * Another helper method, to get the positioning of each glyph in the window
     * 
     * @param i
     *            the ordinal position of the glyph, increasing first by row and
     *            then by column
     * @return the (x, y) position of the glyph in the window
     */
    private int[] getXYPosition(int i) {
        int height = window.getHeight();
        int width = window.getWidth();

        int x = i % 3;
        int y = i / 3;

        x *= width / 4;
        y *= height / 4;

        x += width / 9;
        y += height / 6;

        return new int[] { x, y };
    }


    /**
     * Another helper method, this one just makes the buttons
     * 
     * @param name
     *            the name of the button
     * @param action
     *            which method this button triggers
     * @param position
     *            the position of the button, either north or south of the glyph
     *            display
     */
    private void makeButton(String name, String action, WindowSide position) {
        Button tempButton = new Button(name);
        tempButton.onClick(this, action);
        window.addButton(tempButton, position);
    }


    /**
     * The previous page method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedPrevButton(Button b) {
        currentPage += currentPage == 0 ? 0 : -1;
        this.update();
    }


    /**
     * The artist sort method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedArtistSort(Button b) {
        glyphs.sort(new Comparator<Glyph>() {
            public int compare(Glyph m, Glyph n) {
                if (m.getSong() == null || n.getSong() == null) {
                    return 0;
                }
                String artistM = m.getSong().getArtist();
                String artistN = n.getSong().getArtist();
                return artistM.compareToIgnoreCase(artistN);
            }
        });
        this.sortedBy = "Artist";
        this.update();
    }


    /**
     * The title sort method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedTitleSort(Button b) {
        glyphs.sort(new Comparator<Glyph>() {
            public int compare(Glyph m, Glyph n) {
                if (m.getSong() == null || n.getSong() == null) {
                    return 0;
                }
                String titleM = m.getSong().getTitle();
                String titleN = n.getSong().getTitle();
                return titleM.compareToIgnoreCase(titleN);
            }
        });
        this.sortedBy = "Title";
        this.update();
    }


    /**
     * The release date sort method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedReleaseSort(Button b) {
        glyphs.sort(new Comparator<Glyph>() {
            public int compare(Glyph m, Glyph n) {
                if (m.getSong() == null || n.getSong() == null) {
                    return 0;
                }
                String releaseM = m.getSong().getReleaseDate();
                String releaseN = n.getSong().getReleaseDate();
                return releaseM.compareToIgnoreCase(releaseN);
            }
        });
        this.sortedBy = "Year";
        this.update();
    }


    /**
     * The genre sort method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedGenreSort(Button b) {
        glyphs.sort(new Comparator<Glyph>() {
            public int compare(Glyph m, Glyph n) {
                if (m.getSong() == null || n.getSong() == null) {
                    return 0;
                }
                String genreM = m.getSong().getGenre();
                String genreN = n.getSong().getGenre();
                return genreM.compareTo(genreN);
            }
        });
        this.sortedBy = "Genre";
        this.update();
    }


    /**
     * The next page method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedNextButton(Button b) {
        int maxPage = glyphs.size() / MAX_DISPLAYED_ITEMS;
        currentPage += currentPage + 1 == maxPage ? 0 : 1;
        this.update();
    }


    /**
     * The method to cause display by hobby
     * 
     * @param b
     *            the button clicked
     */
    public void clickedHobbyRep(Button b) {
        currentDisplay = "Hobby";
        this.update();
    }


    /**
     * The method to cause display by major
     * 
     * @param b
     *            the button clicked
     */
    public void clickedMajorRep(Button b) {
        currentDisplay = "Major";
        this.update();
    }


    /**
     * The method to cause display by region
     * 
     * @param b
     *            the button clicked
     */
    public void clickedRegionRep(Button b) {
        currentDisplay = "Region";
        this.update();
    }


    /**
     * The quit method
     * 
     * @param b
     *            the button clicked
     */
    public void clickedQuitButton(Button b) {
        System.exit(0);
    }
}
