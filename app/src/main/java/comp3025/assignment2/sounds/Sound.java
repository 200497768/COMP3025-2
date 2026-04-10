package comp3025.assignment2.sounds;

/**
 * This class is a sound that the assignment is capable of.
 * Every sound in this assignment has been recorded by students in our group.
 *
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class Sound {

    /**
     * This field is the number that allows this sound to be accessed.
     * The sound must be added to the code before it can be accessed.
     * Sounds need to be added to raw.
     */
    private int number;

    public Sound(int number) {
        this.number = number;
    }

    /**
     * This method returns the number that's used when accessing this number.
     * This is the R.raw.sound number.
     */
    public int getNumber() {
        return number;
    }

}
