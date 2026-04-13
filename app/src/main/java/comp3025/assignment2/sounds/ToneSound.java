package comp3025.assignment2.sounds;

import comp3025.assignment2.R;

/**
 * The tone sound happens before any sound during the assignment.
 * This code cna be changed in order to choose the exact recorded tone sound to be used.
 * The options are tone1, tone2, and tone3.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class ToneSound extends Sound {
    public ToneSound() {
        super(R.raw.tone3);
    }
}
