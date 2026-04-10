package comp3025.assignment2.sounds;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * This is the code that's responsible for causing a sound to happen.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class SoundCode {

    /**
     * This field is needed for MediaPlayer.
     */
    private Context context;

    /**
     * This method starts a sound.
     */
    public void startSound(Sound sound){
        int number=sound.getNumber();
        MediaPlayer mediaPlayer = MediaPlayer.create(this.context, number);
        mediaPlayer.start();
    }
}
