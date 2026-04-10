package comp3025.assignment2.sounds;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

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
     * This field is needed by the sound code in order to retrieve MediaPlayer.
     */
    private Context context;

    /**
     * This field isn't needed, but if it's provided, the sound code will ensure that the sound can start.
     * This field will be used to retrieve AudioManager.
     */
    private AppCompatActivity activity;

    public SoundCode(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    /**
     * This method starts a sound.
     * We learned that sounds are possible through (Smyth, 2021, p. 603).
     * The example code from the book was complicated, so we didn't use it.
     * We retrieved the code for this method from (Tutorials Point, n.d.).
     */
    public void startSound(Sound sound){
        //Ensure that the sound can start, if possible.
        this.ensureCanStart();

        //Retrieve the number from the sound, and start it.
        int number=sound.getNumber();
        MediaPlayer mediaPlayer = MediaPlayer.create(this.context, number);
        mediaPlayer.start();
    }

    /**
     * This method ensures that the sound can start, if possible.
     * This is only possible if the activity field was provided to the sound code.
     * If the field isn't available, this method won't change whether the sound can start.
     */
    private void ensureCanStart() {
        if (this.activity == null) {
            //This method can't ensure whether the sound can start.
        } else {
            for (int number = 0; number < 1000; number = number + 1) {
                AudioManager audioManager = (AudioManager) this.activity.getSystemService(Context.AUDIO_SERVICE);
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND);
            }
        }
    }
}
