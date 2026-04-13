package comp3025.assignment2.sounds;

import comp3025.assignment2.R;

/**
 * The main sound happens when showing MainActivity.
 * This is the main screen of the weather retrieval system.
 * Write the name of a city, and select the desired city option that appears to retrieve weather information for that city.
 * If the desired city option doesn’t appear after writing the name of the city, select Retrieve.
 * Select LOGOFF when you have finished using the system.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class MainSound extends Sound {
    public MainSound() {
        super(R.raw.main);
    }
}
