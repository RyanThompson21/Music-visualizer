package prj5;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.15.18
 */
public class Input {

    /**
     * The main method for Project 5
     * 
     * @param args
     */
    public static void main(String[] args) {
        String songsList = "SongList2018S.csv";
        String surveyList = "MusicSurveyData2018S.csv";

        if (args.length == 2) {
            songsList = args[1];
            surveyList = args[0];
        }

        InputReader in = new InputReader(songsList, surveyList);
        GUIDisplay gui = new GUIDisplay();

        gui.addGlyphs(in.getSongs(), in.getStudents());

    }

}
