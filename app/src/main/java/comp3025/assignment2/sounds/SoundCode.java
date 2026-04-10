package comp3025.assignment2.sounds;

import android.media.MediaPlayer;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the code that's responsible for causing a sound to happen.
 * @author Harshit Gambhir
 * @author Yatri Devangbhai Padhiyar
 * @author Dawa Angchuk Sherpa
 * @author Hao Tian
 */
public class SoundCode {
    private AppCompatActivity whatThisWas;

    /**
     * This method starts a sound.
     */
    public void startSound(Sound sound){
        int number=sound.getNumber();
        MediaPlayer mediaPlayer = MediaPlayer.create(this.whatThisWas,number);
        mediaPlayer.start();
    }
}
