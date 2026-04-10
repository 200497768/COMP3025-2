package comp3025.assignment2.sounds;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * This is the code that's responsible for causing a sound to happen.
 * We want sounds to happen multiple times in this assignment.
 * If we use SoundCode, we can avoid repeating that code, since it will be part of the startSound method.
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

    public SoundCode(Context context) {
        this.context = context;
    }

    /**
     * This method starts a sound.
     * We learned that sounds are possible through (Smyth, 2021, p. 603).
     * The example code from the book was complicated, so we didn't use it.
     * We retrieved the code for this method from (Tutorials Point, n.d.).
     */
    public void startSound(Sound sound){
        int number=sound.getNumber();
        MediaPlayer mediaPlayer = MediaPlayer.create(this.context, number);
        mediaPlayer.start();
    }
}
