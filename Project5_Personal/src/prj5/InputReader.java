package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.15.18
 * 
 */
public class InputReader {

    private ArrayList<Song> songs;
    private ArrayList<Student> students;


    public InputReader(String songsList, String surveyList) {
        try {
            songs = readInputFile(songsList, "Song");
            students = readInputFile(surveyList, "Student");
        }
        catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }


    @SuppressWarnings("unchecked")
    private <E> ArrayList<E> readInputFile(String filename, String type)
        throws FileNotFoundException {
        Scanner inFile = new Scanner(new File(filename));
        ArrayList<E> inputs = new ArrayList<>();
        inFile.nextLine();

        while (inFile.hasNextLine()) {
            String[] inputParams = inFile.nextLine().split(",");

            E inputValue;
            if (type.equals("Song")) {
                inputValue = (E)new Song(inputParams);
            }
            else {
                if (inputParams.length < 5) {
                    continue;
                }
                inputValue = (E)new Student(inputParams);
            }
            inputs.add(inputValue);
        }

        inFile.close();

        return inputs;
    }

    /**
     * getter for songs arraylist 
     * @return arraylist of songs
     */
    public ArrayList<Song> getSongs() {
        return songs;
    }


    /**
     * getter for students arraylist
     * @return arraylist of students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

}
