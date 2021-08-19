package prj5;

/**
 * @author David Barto (depbarto)
 * @author Tom Laier (toml362)
 * @author Ryan Thompson (ryanjt5)
 * @version 11.15.18
 */
public class Student {
    private String hobby;
    private String major;
    private String region;
    private String[] responses;


    /**
     * The default constructor for the student class, populating data fields
     * from the input parameter array
     * 
     * @param split
     *            the parameter array for the student
     */
    public Student(String... split) {
        hobby = processInput(split[4]);
        major = processInput(split[2]);
        region = processInput(split[3]);
        responses = new String[split.length - 5];
        System.arraycopy(split, 5, responses, 0, responses.length);
    }


    /**
     * The means by which the student class processes the string inputs into
     * enum-derived private data
     * 
     * @param inputString
     *            the hobby, major, or region being input
     * @return the processed version of the input
     */
    private String processInput(String inputString) {
        if (inputString != null) {
            switch (inputString) {
                case "reading":
                    return HobbyEnum.READ + "";
                case "music":
                    return HobbyEnum.MUSIC + "";
                case "sports":
                    return HobbyEnum.SPORTS + "";
                case "art":
                    return HobbyEnum.ART + "";
                case "Math or CMDA":
                    return MajorEnum.MATH_OR_CMDA + "";
                case "Computer Science":
                    return MajorEnum.CS + "";
                case "Other Engineering":
                    return MajorEnum.ENGINEERING + "";
                case "Other":
                    return MajorEnum.OTHER + "";
                case "Southeast":
                    return RegionEnum.SE + "";
                case "Northeast":
                    return RegionEnum.NE + "";
                case "United States (other than Southeast or Northwest)":
                    return RegionEnum.REST + "";
                case "Outside of United States":
                    return RegionEnum.OUTSIDE + "";
                default:
                    return "";
            }
        }
        else {
            return "";
        }
    }


    /**
     * The getter for the student's responses to the survey
     * 
     * @return the responses
     */
    public String[] getResponses() {
        return responses;
    }


    /**
     * The getter for the student's primary hobby
     * 
     * @return the hobby
     */
    public String getHobby() {
        return hobby;
    }


    /**
     * The getter for the major the student has
     * 
     * @return the major
     */
    public String getMajor() {
        return major;
    }


    /**
     * The getter for the region the student is from
     * 
     * @return the region
     */
    public String getRegion() {
        return region;
    }

}
